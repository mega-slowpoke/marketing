package org.example.test.domain;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.service.ILotteryService;
import org.example.domain.strategy.service.filter.IFilter;
import org.example.domain.strategy.service.filter.impl.WeightFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterTest {

    @Resource
    private ILotteryService iLotteryService;


    @Resource
    private WeightFilter ruleWeightLogicFilter;


    @Test
    public void test_performRaffle() {
        LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
        raffleFactorEntity.setStrategyId(100001L);
        raffleFactorEntity.setUserId("xiaofuge");
        LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

//    @Test
//    public void test_performRaffle_blacklist() {
//        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
//                .userId("user003")  // 黑名单用户 user001,user002,user003
//                .strategyId(100001L)
//                .build();
//
//        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
//
//        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
//        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
//    }

}
