package org.example.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.types.common.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoTest {


    @Resource
    private IStrategyRepo iStrategyRepo;

    @Test
    public void queryStrategyTest() {
        log.info(iStrategyRepo.queryStrategyById(100001L).toString());
    }

    @Test
    public void queryStrategyRuleTest() {
        log.info(iStrategyRepo.queryStrategyRuleByIdAndName(100001L, "rule_weight").toString());
    }

    @Test
    public void queryAwardRuleTest() {
        log.info(iStrategyRepo.queryAwardRuleByIdAndName(100001L, 107, Constants.RuleName.RULE_LOCK).toString());
    }
}
