package org.example.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.strategy.service.filter.afterFilter.factory.AfterFilterFactory;
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

    private String ruleModels;

    public StrategyAwardRuleModelVO(String ruleModels) {
        this.ruleModels = ruleModels;
    }

    public String getRuleModels() {
        return ruleModels;
    }

    public void setRuleModels(String ruleModels) {
        this.ruleModels = ruleModels;
    }
}