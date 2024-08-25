package org.example.test.domain;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.service.ILotteryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryServiceTest {

    @Resource
    private ILotteryService iLotteryService;

    @Test
    public void test_performRaffle() {
        LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
        raffleFactorEntity.setStrategyId(100001L);
        raffleFactorEntity.setUserId("xiaofuge");
        LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_performRaffle_blacklist() {
        LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
        raffleFactorEntity.setStrategyId(100001L);
        raffleFactorEntity.setUserId("black01");
        LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);

        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void test_raffle_center_rule_lock(){
        LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
        raffleFactorEntity.setStrategyId(100003L);
        raffleFactorEntity.setUserId("xiaofuge");
        LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);
        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void TestLottery100006L() {
        LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
        raffleFactorEntity.setStrategyId(100006L);
        raffleFactorEntity.setUserId("xiaofuge");

        LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);
        log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
        log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
    }


    @Test
    public void test_LotteryWithStockSystem() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            log.info("第{}次抽奖 -----------------------------------------------------------", i);

            LotteryReqEntity raffleFactorEntity = new LotteryReqEntity();
            raffleFactorEntity.setStrategyId(100006L);
            raffleFactorEntity.setUserId("xiaofuge");

            LotteryResEntity raffleAwardEntity = iLotteryService.performLottery(raffleFactorEntity);
            log.info("请求参数：{}", JSON.toJSONString(raffleFactorEntity));
            log.info("测试结果：{}", JSON.toJSONString(raffleAwardEntity));
        }
        // 等待 UpdateAwardStockJob 消费队列
        new CountDownLatch(1).await();
    }
}
