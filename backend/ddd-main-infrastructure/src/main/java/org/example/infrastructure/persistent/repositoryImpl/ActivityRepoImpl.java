package org.example.infrastructure.persistent.repositoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.ActivityCountEntity;
import org.example.domain.activity.model.entity.ActivityEntity;
import org.example.domain.activity.model.entity.RaffleSkuEntity;
import org.example.domain.activity.repository.IRaffleOrderRepo;
import org.example.infrastructure.persistent.dao.IRaffleActivityAccountDao;
import org.example.infrastructure.persistent.dao.IRaffleActivityCountDao;
import org.example.infrastructure.persistent.dao.IRaffleActivityDao;
import org.example.infrastructure.persistent.dao.IRaffleActivityOrderDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class ActivityRepoImpl implements IRaffleOrderRepo {


    @Resource
    private IRaffleActivityDao iRaffleActivityDao;

    @Resource
    private IRaffleActivityAccountDao iRaffleActivityAccountDao;

    @Resource
    private IRaffleActivityCountDao iRaffleActivityCountDao;

    @Resource
    private IRaffleActivityOrderDao iRaffleActivityOrderDao;

    @Override
    public RaffleSkuEntity querySkuEntityById(Long skuId) {
        return null;
    }

    @Override
    public ActivityEntity queryActivityEntityById(Long activityId) {
        return null;
    }

    @Override
    public ActivityCountEntity queryActivityCountEntityById(Long activityCountId) {
        return null;
    }
}
