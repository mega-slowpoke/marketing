package org.example.infrastructure.persistent.repositoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.model.valobj.*;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.infrastructure.persistent.dao.*;
import org.example.infrastructure.persistent.po.*;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.example.types.common.Constants;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class StrategyRepo implements IStrategyRepo {

    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;

    @Resource
    private IStrategyDAO iStrategyDAO;

    @Resource
    private IStrategyRuleDAO iStrategyRuleDAO;

    @Resource
    private IAwardRuleDAO iAwardRuleDAO;

    @Resource
    private IRuleTreeDAO iRuleTreeDAO;

    @Resource
    private IRuleTreeNodeDAO iRuleTreeNodeDAO;

    @Resource
    private IRuleTreeNodeLineDAO iRuleTreeNodeLineDAO;

    @Resource
    private IRedisService iRedisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId) {
        String key = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = iRedisService.getValue(key);

        // query redis first
        if (strategyAwardEntityList != null && !strategyAwardEntityList.isEmpty()) return strategyAwardEntityList;

        // if redis not exists, query database
        List<StrategyAward> strategyAwardList = iStrategyAwardDAO.queryStrategyAwardListById(strategyId);
        List<StrategyAwardEntity> res = new ArrayList<>();
        for (StrategyAward strategyAward : strategyAwardList) {
            StrategyAwardEntity strategyAwardEntity = new StrategyAwardEntity();
            strategyAwardEntity.setId(strategyAward.getId());
            strategyAwardEntity.setStrategyId(strategyAward.getStrategyId());
            strategyAwardEntity.setAwardId(strategyAward.getAwardId());
            strategyAwardEntity.setAwardTotal(strategyAward.getAwardTotal());
            strategyAwardEntity.setAwardRemaining(strategyAward.getAwardRemaining());
            strategyAwardEntity.setAwardRate(strategyAward.getAwardRate());
            res.add(strategyAwardEntity);
        }

        // and update redis
        iRedisService.setValue(key, res);
        return res;
    }

    @Override
    public void putAwardDistributionToRedis(String strategyKey, Integer totalBucket, Map<Integer, Integer> awardDistributionMap) {
        String bucketKey = Constants.RedisKey.STRATEGY_TOTAL_BUCKET_KEY + strategyKey;
        String mapKey = Constants.RedisKey.AWARD_DISTRIBUTION_KEY + strategyKey;

        // 存储rateRange和奖品分布
        iRedisService.setValue(bucketKey, totalBucket);
        RMap<Integer, Integer> cachedMap = iRedisService.getMap(mapKey);
        cachedMap.putAll(awardDistributionMap);
    }

    @Override
    public Integer getRange(String bucketNumberKey) {
        return iRedisService.getValue(bucketNumberKey);
    }

    @Override
    public Integer getAwardIdFromDistributionMap(String awardDistributionKey, Integer randomIdx) {
        return iRedisService.getFromMap(awardDistributionKey, randomIdx);
    }


    @Override
    public StrategyEntity queryStrategyById(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = iRedisService.getValue(cacheKey);
        if (strategyEntity != null) return strategyEntity;
        Strategy strategy = iStrategyDAO.queryStrategyById(strategyId);
        strategyEntity = new StrategyEntity();
        strategyEntity.setStrategyId(strategy.getStrategyId());
        strategyEntity.setStrategyDesc(strategy.getStrategyDesc());
        strategyEntity.setRuleModels(strategy.getRuleModels());
        iRedisService.setValue(cacheKey, strategyEntity);
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRuleByIdAndName(Long strategyId, String ruleName) {
        StrategyRule strategyRule = iStrategyRuleDAO.queryStrategyRuleByIdAndName(strategyId, ruleName);
        StrategyRuleEntity strategyRuleEntity = new StrategyRuleEntity();
        strategyRuleEntity.setStrategyId(strategyRule.getStrategyId());
        strategyRuleEntity.setRuleModel(strategyRule.getRuleModel());
        strategyRuleEntity.setRuleValue(strategyRule.getRuleValue());
        strategyRuleEntity.setRuleDesc(strategyRule.getRuleDesc());
        return strategyRuleEntity;
    }


    @Override
    public StrategyAwardRuleModelVO queryStrategyAwardRuleModels(Long strategyId, Integer awardId) {
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(strategyId);
        strategyAward.setAwardId(awardId);
        String ruleModels = iStrategyAwardDAO.queryStrategyAwardRuleModels(strategyAward);
        return new StrategyAwardRuleModelVO(ruleModels);
    }

    @Override
    public AwardRuleEntity queryAwardRuleByIdAndName(Long strategyId, Integer awardId, String ruleName) {
        AwardRule awardRule = iAwardRuleDAO.queryAwardRuleByIdAndName(strategyId, awardId, ruleName);
        AwardRuleEntity awardRuleEntity = new AwardRuleEntity();
        awardRuleEntity.setStrategyId(awardRule.getStrategyId());
        awardRuleEntity.setAwardId(awardRule.getAwardId());
        awardRuleEntity.setRuleModel(awardRule.getRuleModel());
        awardRuleEntity.setRuleValue(awardRule.getRuleValue());
        awardRuleEntity.setRuleDesc(awardRule.getRuleDesc());
        return awardRuleEntity;
    }

    @Override
    public RuleTreeVO queryRuleTreeVOByTreeId(String treeId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.RULE_TREE_VO_KEY + treeId;
        RuleTreeVO ruleTreeVOCache = iRedisService.getValue(cacheKey);
        if (null != ruleTreeVOCache) return ruleTreeVOCache;

        // 从数据库获取
        RuleTree ruleTree = iRuleTreeDAO.queryRuleTreeByTreeId(treeId);
        List<RuleTreeNode> ruleTreeNodes = iRuleTreeNodeDAO.queryRuleTreeNodeListByTreeId(treeId);
        List<RuleTreeNodeLine> ruleTreeNodeLines = iRuleTreeNodeLineDAO.queryRuleTreeNodeLineListByTreeId(treeId);

        // 1. tree node line 转换Map结构
        Map<String, List<RuleTreeNodeLineVO>> ruleTreeNodeLineMap = new HashMap<>();
        for (RuleTreeNodeLine ruleTreeNodeLine : ruleTreeNodeLines) {
            RuleTreeNodeLineVO ruleTreeNodeLineVO = RuleTreeNodeLineVO.builder()
                    .treeId(ruleTreeNodeLine.getTreeId())
                    .ruleNodeFrom(ruleTreeNodeLine.getRuleNodeFrom())
                    .ruleNodeTo(ruleTreeNodeLine.getRuleNodeTo())
                    .ruleLimitType(RuleLimitTypeVO.valueOf(ruleTreeNodeLine.getRuleLimitType()))
                    .ruleLimitValue(RuleLogicCheckTypeVO.valueOf(ruleTreeNodeLine.getRuleLimitValue()))
                    .build();

            List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList = ruleTreeNodeLineMap.computeIfAbsent(ruleTreeNodeLine.getRuleNodeFrom(), k -> new ArrayList<>());
            ruleTreeNodeLineVOList.add(ruleTreeNodeLineVO);
        }

        // 2. tree node 转换为Map结构
        Map<String, RuleTreeNodeVO> treeNodeMap = new HashMap<>();
        for (RuleTreeNode ruleTreeNode : ruleTreeNodes) {
            RuleTreeNodeVO ruleTreeNodeVO = RuleTreeNodeVO.builder()
                    .treeId(ruleTreeNode.getTreeId())
                    .ruleKey(ruleTreeNode.getRuleKey())
                    .ruleDesc(ruleTreeNode.getRuleDesc())
                    .ruleValue(ruleTreeNode.getRuleValue())
                    .treeNodeLineVOList(ruleTreeNodeLineMap.get(ruleTreeNode.getRuleKey()))
                    .build();
            treeNodeMap.put(ruleTreeNode.getRuleKey(), ruleTreeNodeVO);
        }

        // 3. 构建 Rule Tree
        RuleTreeVO ruleTreeVODB = RuleTreeVO.builder()
                .treeId(ruleTree.getTreeId())
                .treeName(ruleTree.getTreeName())
                .treeDesc(ruleTree.getTreeDesc())
                .treeRootRuleNode(ruleTree.getTreeRootRuleKey())
                .treeNodeMap(treeNodeMap)
                .build();

        iRedisService.setValue(cacheKey, ruleTreeVODB);
        return ruleTreeVODB;
    }

    @Override
    public Boolean decrRedisAwardCountByOne(String cacheKey) {
        long remaining = iRedisService.decr(cacheKey);
        // 如果扣减为0.扣减失败
        if (remaining < 0) {
            iRedisService.setValue(cacheKey, 0);
            return false;
        }

        // 1. 按照cacheKey decr 后的值，如 99、98、97 和 key 组成为库存锁的key进行使用。
        // 2. 加锁为了兜底，如果后续有恢复库存，手动处理等，也不会超卖。因为所有的可用库存key，都被加锁了。
        String lockKey = cacheKey + Constants.UNDERLINE + remaining;
        Boolean lock = iRedisService.setNx(lockKey);
        if (!lock) {
            log.info("策略奖品库存加锁失败 {}", lockKey);
        }
        return lock;

    }


    @Override
    public void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_REMAINING_KEY;
        RBlockingQueue<StrategyAwardStockKeyVO> blockingQueue = iRedisService.getBlockingQueue(cacheKey);
        RDelayedQueue<StrategyAwardStockKeyVO> delayedQueue = iRedisService.getDelayedQueue(blockingQueue);
        delayedQueue.offer(strategyAwardStockKeyVO, 3, TimeUnit.SECONDS);
    }

    @Override
    public void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount) {
        String totalAwardKey = Constants.RedisKey.STRATEGY_AWARD_TOTAL_KEY + strategyId + Constants.UNDERLINE + awardId;
        // 已经设置过对应strategy下的award总数
        if (iRedisService.isExists(totalAwardKey)) return;
        iRedisService.setAtomicLong(totalAwardKey, awardCount);
    }

    @Override
    public StrategyAwardStockKeyVO getNextConsumedAwardFromQueue() {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_REMAINING_KEY;
        RBlockingQueue<StrategyAwardStockKeyVO> blockingQueue = iRedisService.getBlockingQueue(cacheKey);
        return blockingQueue.poll();
    }

    @Override
    public void decrDBAwardCountByOne(Long strategyId, Integer awardId) {
        iStrategyAwardDAO.decrAwardCount(strategyId, awardId, 1);
    }


}
