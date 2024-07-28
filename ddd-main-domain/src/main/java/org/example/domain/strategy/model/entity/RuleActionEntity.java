package org.example.domain.strategy.model.entity;


import lombok.*;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;

/**
 * 这是一个抽奖动作的实体
 *
 * */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;


    // 用内部类的话，就可以access到code, info, ruleModel和data对象
    static public class RaffleEntity {

    }

    // 抽奖之前
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity {
        /**
         * 策略ID
         */
        private Long strategyId;

        /**
         * 权重值，用于权重抽奖时的权重。
         */
        private String weightValue;

        /**
         * 如果是黑名单，设置默认黑名单奖品ID；
         */
        private Integer blackListAwardId;
    }

    // 抽奖之中
    static public class RaffleCenterEntity extends RaffleEntity {

    }

    // 抽奖之后
    static public class RaffleAfterEntity extends RaffleEntity {

    }

}
