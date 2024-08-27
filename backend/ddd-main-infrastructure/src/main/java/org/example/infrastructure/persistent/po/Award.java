package org.example.infrastructure.persistent.po;

import org.springframework.context.annotation.Bean;

import java.util.Date;

public class Award {

    private Long id;
    private Integer awardId;
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
    private Date createTime;
    private Date updateTime;

    public Award() {
    }

    public Award(Long id, Integer awardId, String awardKey, String awardConfig, String awardDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.awardId = awardId;
        this.awardKey = awardKey;
        this.awardConfig = awardConfig;
        this.awardDesc = awardDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getAwardKey() {
        return awardKey;
    }

    public void setAwardKey(String awardKey) {
        this.awardKey = awardKey;
    }

    public String getAwardConfig() {
        return awardConfig;
    }

    public void setAwardConfig(String awardConfig) {
        this.awardConfig = awardConfig;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
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
        return "Award{" +
                "id=" + id +
                ", awardId=" + awardId +
                ", awardKey='" + awardKey + '\'' +
                ", awardConfig='" + awardConfig + '\'' +
                ", awardDesc='" + awardDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
