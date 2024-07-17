package org.example.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.service.ILotteryExecutor;
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
    public void TestStrategyAward() {
        iStrategyInitializer.initializeStrategy(100001L);
    }

    @Test
    public void LotteryTest1() {
        log.info("lottery1: get awardId" + iLotteryExecutor.doLottery(100001L));
        log.info("lottery2: get awardId" + iLotteryExecutor.doLottery(100001L));
        log.info("lottery3: get awardId" + iLotteryExecutor.doLottery(100001L));
    }

    @Test
    public void LotteryTest() {
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
