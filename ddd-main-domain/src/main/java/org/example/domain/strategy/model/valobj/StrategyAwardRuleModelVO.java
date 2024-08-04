package org.example.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.service.filter.factory.FilterFactory;
import org.example.types.common.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖策略规则规则值对象；值对象，没有唯一ID，仅限于从数据库查询对象
 * @create 2024-01-13 09:30
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String[] duringRuleModels = null;

    private String[] afterRuleModels = null;


    public StrategyAwardRuleModelVO(String ruleModels) {
        if (ruleModels == null) {
            return;
        }

        List<String> duringRuleModelList = new ArrayList<>();
        List<String> afterRuleModelList = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.COMMA);
        for (String ruleModelValue : ruleModelValues) {
            if (FilterFactory.isDuringRule(ruleModelValue)) {
                duringRuleModelList.add(ruleModelValue);
            } else if (FilterFactory.isAfterRule(ruleModelValue)) {
                afterRuleModelList.add(ruleModelValue);
            }
        }
        duringRuleModels = duringRuleModelList.toArray(new String[0]);
        afterRuleModels = afterRuleModelList.toArray(new String[0]);
    }

    /**
     * 获取抽奖中规则；或者使用 lambda 表达式
     * <p>
     * List<String> ruleModelList = Arrays.stream(ruleModels.split(Constants.SPLIT))
     * .filter(DefaultLogicFactory.LogicModel::isCenter)
     * .collect(Collectors.toList());
     * return ruleModelList;
     * <p>
     * List<String> collect = Arrays.stream(ruleModelValues).filter(DefaultLogicFactory.LogicModel::isCenter).collect(Collectors.toList());
     */
    public String[] duringRuleModels() {
        return duringRuleModels;
    }

    public String[] afterRuleModels() {
        return afterRuleModels;
    }

}