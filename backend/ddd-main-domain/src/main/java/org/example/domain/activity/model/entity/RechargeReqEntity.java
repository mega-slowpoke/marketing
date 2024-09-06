package org.example.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechargeReqEntity {
    public String userId;

    public Long skuId;

    // used for duplicate detection
    public String outBusinessNo;
}
