package org.example.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.service.executor.ILotteryExecutor;
import org.example.domain.strategy.service.initializer.IStrategyInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {


    @Resource
    private IStrategyInitializer iStrategyInitializer;

    @Resource
    private ILotteryExecutor iLotteryExecutor;


    @Test
    public void TestInitializeStrategy() {
        iStrategyInitializer.initializeStrategy(100001L);
        iStrategyInitializer.initializeStrategy(100002L);
        iStrategyInitializer.initializeStrategy(100003L);
        iStrategyInitializer.initializeStrategy(100004L);
        iStrategyInitializer.initializeStrategy(100005L);
        iStrategyInitializer.initializeStrategy(100006L);
    }

    @Test
    public void LotteryTest100001() {
        log.info("lottery1: get awardId" + iLotteryExecutor.doLottery(100001L));
        log.info("lottery2: get awardId" + iLotteryExecutor.doLottery(100001L));
        log.info("lottery3: get awardId" + iLotteryExecutor.doLottery(100001L));
    }

    @Test
    public void LotteryTest100001Weight() {
        log.info("lottery1: get awardId" + iLotteryExecutor.doLottery(100001L, "4000"));
        log.info("lottery2: get awardId" + iLotteryExecutor.doLottery(100001L, "4000"));
        log.info("lottery3: get awardId" + iLotteryExecutor.doLottery(100001L, "4000"));

        log.info("-------------------------------------------------------------------");

        log.info("lottery1: get awardId" + iLotteryExecutor.doLottery(100001L, "5000"));
        log.info("lottery2: get awardId" + iLotteryExecutor.doLottery(100001L, "5000"));
        log.info("lottery3: get awardId" + iLotteryExecutor.doLottery(100001L, "5000"));

        log.info("-------------------------------------------------------------------");

        log.info("lottery1: get awardId" + iLotteryExecutor.doLottery(100001L, "6000"));
        log.info("lottery2: get awardId" + iLotteryExecutor.doLottery(100001L, "6000"));
        log.info("lottery3: get awardId" + iLotteryExecutor.doLottery(100001L, "6000"));
    }




}
