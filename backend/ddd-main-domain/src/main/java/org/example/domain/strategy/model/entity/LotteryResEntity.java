package org.example.domain.strategy.model.entity;

public class LotteryResEntity {

    /** 奖品ID */
    private Integer awardId;

    /** 奖品配置信息 */
    private String awardConfig;

    private Integer sortOrder;

    public LotteryResEntity() {
    }

    public LotteryResEntity(Integer awardId, String awardConfig, Integer sortOrder) {
        this.awardId = awardId;
        this.awardConfig = awardConfig;
        this.sortOrder = sortOrder;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getAwardConfig() {
        return awardConfig;
    }

    public void setAwardConfig(String awardConfig) {
        this.awardConfig = awardConfig;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
