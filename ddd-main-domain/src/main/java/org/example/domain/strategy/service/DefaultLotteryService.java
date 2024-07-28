package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
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
    protected RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> checkRaffleBeforeLogic(LotteryReqEntity lotteryReq, String... ruleModels) {
        Long strategyId = lotteryReq.getStrategyId();
        String userId = lotteryReq.getUserId();

        // 优先过滤黑名单
        // 检查是否存在黑名单规则
        boolean hasBlackListRule = Arrays.stream(ruleModels).anyMatch(ruleModel -> ruleModel.equals(Constants.RuleName.RULE_BLACKLIST));

        if (hasBlackListRule) {
            IFilter<?> blackListFilter = filterFactory.createLFilter(Constants.RuleName.RULE_BLACKLIST);
            FilterConditionEntity filterConditionEntity = new FilterConditionEntity();
            filterConditionEntity.setStrategyId(strategyId);
            filterConditionEntity.setUserId(userId);
            return (RuleActionEntity<RuleActionEntity.RaffleBeforeEntity>) blackListFilter.filter(filterConditionEntity);
        }

        // 过滤其他规则
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = null;
        for (String rule : ruleModels) {
            if (rule.equals(Constants.RuleName.RULE_BLACKLIST)) continue;
            IFilter<?> curFilter = filterFactory.createLFilter(rule);
            FilterConditionEntity filterConditionEntity = new FilterConditionEntity();
            filterConditionEntity.setStrategyId(strategyId);
            filterConditionEntity.setUserId(userId);
            ruleActionEntity = (RuleActionEntity<RuleActionEntity.RaffleBeforeEntity>) curFilter.filter(filterConditionEntity);
            // 非放行结果则顺序过滤
            log.info("抽奖前规则过滤 userId: {} ruleModel: {} code: {} info: {}", lotteryReq .getUserId(), rule, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
        }

        return ruleActionEntity;
    }




}
