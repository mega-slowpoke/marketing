package org.example.domain.activity.repository;

import org.example.domain.activity.model.entity.ActivityCountEntity;
import org.example.domain.activity.model.entity.ActivityEntity;
import org.example.domain.activity.model.entity.RaffleSkuEntity;

public interface IRaffleOrderRepo {

    RaffleSkuEntity querySkuEntityById(Long skuId);

    ActivityEntity queryActivityEntityById(Long activityId);

    ActivityCountEntity queryActivityCountEntityById(Long activityCountId);
}
