package org.example.infrastructure.persistent.po;

import java.util.Date;

public class Strategy {
    private Long id;
    private Long strategyId;
    private String strategyDesc;

    private String ruleModels;
    private Date createTime;
    private Date updateTime;

    public Strategy() {
    }

    public Strategy(Long id, Long strategyId, String strategyDesc, String ruleModels, Date createTime, Date updateTime) {
        this.id = id;
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.ruleModels = ruleModels;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getRuleModels() {
        return ruleModels;
    }

    public void setRuleModels(String ruleModels) {
        this.ruleModels = ruleModels;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "Strategy{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", strategyDesc='" + strategyDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
