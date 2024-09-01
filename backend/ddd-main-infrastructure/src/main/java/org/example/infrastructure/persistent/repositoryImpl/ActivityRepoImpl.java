package org.example.infrastructure.persistent.repositoryImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.ActivityCountEntity;
import org.example.domain.activity.model.entity.ActivityEntity;
import org.example.domain.activity.model.entity.RaffleSkuEntity;
import org.example.domain.activity.model.valobj.ActivityConstants;
import org.example.domain.activity.repository.IRaffleOrderRepo;
import org.example.infrastructure.persistent.dao.*;
import org.example.infrastructure.persistent.po.RaffleActivity;
import org.example.infrastructure.persistent.po.RaffleActivityCount;
import org.example.infrastructure.persistent.po.RaffleActivitySku;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.example.types.common.Constants;
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

    @Resource
    private IRaffleActivitySkuDao iRaffleActivitySkuDao;

    @Resource
    private IRedisService redisService;

    @Override
    public RaffleSkuEntity querySkuEntityById(Long skuId) {
        RaffleActivitySku raffleActivitySku = iRaffleActivitySkuDao.queryActivitySku(skuId);
        return RaffleSkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
    }

    @Override
    public ActivityEntity queryActivityEntityById(Long activityId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if (null != activityEntity) return activityEntity;
        // 从库中获取数据
        RaffleActivity raffleActivity = iRaffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityConstants.State.COMPLETE)
                .build();
        redisService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryActivityCountEntityById(Long activityCountId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if (null != activityCountEntity) return activityCountEntity;
        // 从库中获取数据
        RaffleActivityCount raffleActivityCount = iRaffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        redisService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }
}
