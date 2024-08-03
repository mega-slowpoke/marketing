package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.service.filter.IFilter;
import org.example.domain.strategy.service.filter.factory.FilterFactory;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Slf4j
@Service
public class DefaultLotteryService extends AbstractLotteryService {

    @Resource
    private FilterFactory filterFactory;

    @Override
    protected RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> beforeLotteryFilter(LotteryReqEntity lotteryReq, String... ruleModels) {
        Long strategyId = lotteryReq.getStrategyId();
        String userId = lotteryReq.getUserId();

        // 优先过滤黑名单
        // 检查是否存在黑名单规则
        boolean hasBlackListRule = Arrays.stream(ruleModels).anyMatch(ruleModel -> ruleModel.equals(Constants.RuleName.RULE_BLACKLIST));

        RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> ruleActionEntity = null;
        if (hasBlackListRule) {
            IFilter<?> blackListFilter = filterFactory.getFilter(Constants.RuleName.RULE_BLACKLIST);
            FilterConditionEntity filterConditionEntity = new FilterConditionEntity();
            filterConditionEntity.setStrategyId(strategyId);
            filterConditionEntity.setUserId(userId);
            ruleActionEntity = (RuleActionEntity<RuleActionEntity.BeforeLotteryEntity>) blackListFilter.filter(filterConditionEntity);
            // 如果有blackList规则，并且用户属于黑名单用户，则直接返回接管规则
            if (ruleActionEntity.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
                return ruleActionEntity;
            }
            // 但是如果用户不属于黑名单用户，则继续往后过滤
        }

        // 过滤其他规则，各个规则互斥，只要有一个接管，就立刻返回
        for (String rule : ruleModels) {
            if (rule.equals(Constants.RuleName.RULE_BLACKLIST)) continue;
            IFilter<?> curFilter = filterFactory.getFilter(rule);
            FilterConditionEntity filterConditionEntity = new FilterConditionEntity();
            filterConditionEntity.setStrategyId(strategyId);
            filterConditionEntity.setUserId(userId);
            ruleActionEntity = (RuleActionEntity<RuleActionEntity.BeforeLotteryEntity>) curFilter.filter(filterConditionEntity);
            // 非放行结果则顺序过滤
            if (ruleActionEntity.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
                log.info("抽奖前规则过滤 userId: {} ruleModel: {} code: {} info: {}", lotteryReq .getUserId(), rule, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
                return ruleActionEntity;
            }
        }

        return ruleActionEntity;
    }

    @Override
    protected RuleActionEntity<RuleActionEntity.DuringLotteryEntity> duringLotteryFilter(LotteryResEntity lotteryRes, String... ruleModels) {
        for (String rule : ruleModels) {
            IFilter<?> curFilter = filterFactory.getFilter(rule);
            FilterConditionEntity filterConditionEntity = new FilterConditionEntity();
            filterConditionEntity.setStrategyId(lotteryRes.getStrategyId());
            filterConditionEntity.setUserId(lotteryRes.getUserId());
            RuleActionEntity<RuleActionEntity.DuringLotteryEntity> action = (RuleActionEntity<RuleActionEntity.DuringLotteryEntity>) curFilter.filter(filterConditionEntity);
            if (action.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) return action;
        }
        return null;
    }
}
