package org.example.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.service.filter.beforeFilter.IBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;
import org.example.domain.strategy.service.filter.beforeFilter.impl.DefaultLotteryFilter;
import org.example.domain.strategy.service.filter.beforeFilter.impl.RuleWeightBeforeFilter;
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
public class FilterChainTest {

    @Resource
    private RuleWeightBeforeFilter ruleWeightLogicChain;
    @Resource
    private BeforeFilterFactory beforeFilterFactory;

    @Test
    public void test_LogicChain_rule_blacklist() {
        IBeforeFilter logicChain = beforeFilterFactory.createFilterChain(100003L);
        Integer awardId = logicChain.processBeforeFilterAndGetAwardId("black02", 100003L);
        log.info("测试结果：{}", awardId);
    }

    @Test
    public void test_LogicChain_rule_weight() {
        // 通过反射 mock 规则中的值
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4900);

        IBeforeFilter logicChain = beforeFilterFactory.createFilterChain(100001L);
        Integer awardId = logicChain.processBeforeFilterAndGetAwardId("xiaofuge", 100001L);
        log.info("测试结果：{}", awardId);
    }

    @Test
    public void test_LogicChain_rule_default() {
        IBeforeFilter logicChain = beforeFilterFactory.createFilterChain(100001L);
        Integer awardId = logicChain.processBeforeFilterAndGetAwardId("xiaofuge", 100001L);
        log.info("测试结果：{}", awardId);
    }

}

