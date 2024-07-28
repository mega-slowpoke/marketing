package org.example.domain.strategy.model.entity;

public class LotteryResEntity {

    /** 策略ID */
    private Long strategyId;
    /** 奖品ID */
    private Integer awardId;
    /** 奖品对接标识 - 每一个都是一个对应的发奖策略 */
    private String awardKey;
    /** 奖品配置信息 */
    private String awardConfig;
    /** 奖品内容描述 */
    private String awardDesc;

    public LotteryResEntity() {
    }

    public LotteryResEntity(Long strategyId, Integer awardId, String awardKey, String awardConfig, String awardDesc) {
        this.strategyId = strategyId;
        this.awardId = awardId;
        this.awardKey = awardKey;
        this.awardConfig = awardConfig;
        this.awardDesc = awardDesc;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public String getAwardKey() {
        return awardKey;
    }

    public String getAwardConfig() {
        return awardConfig;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public void setAwardKey(String awardKey) {
        this.awardKey = awardKey;
    }

    public void setAwardConfig(String awardConfig) {
        this.awardConfig = awardConfig;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
    }
}
