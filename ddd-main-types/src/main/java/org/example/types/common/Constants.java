package org.example.types.common;

public class Constants {

    public final static String COMMA = ",";

    public final static String SEMICOLON = ";";

    public final static String COLON = ":";


    public static class RedisKey {
        public static String STRATEGY_KEY = "strategy_key_";
        public static String STRATEGY_AWARD_KEY = "strategy_award_key_";
        public static String AWARD_DISTRIBUTION_KEY = "strategy_award_distribution_key_";
        public static String STRATEGY_TOTAL_BUCKET_KEY = "strategy_total_bucket_key_";
    }


    public static class RuleName {
        // "【抽奖前规则】根据抽奖权重返回可抽奖范围KEY"
        public static String RULE_WEIGHT = "rule_weight";
        // 【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回"
        public static String RULE_BLACKLIST = "rule_blacklist";
        // 【抽奖中规则】需要最小抽奖次数"
        public static String RULE_LOCK = "rule_lock";
        // 【抽奖后规则】发送兜底奖励"
        public static String RULE_LUCK_AWARD = "rule_luck_award";

        public static String DEFAULT_RULE = "default_rule";
    }

    public static class RuleType {
        public static String BEFORE_RULE = "before";

        public static String DURING_RULE = "during";
        public static String AFTER_RULE = "after";
    }
}
