package org.example.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.infrastructure.persistent.dao.IAwardDAO;
import org.example.infrastructure.persistent.dao.IStrategyAwardDAO;
import org.example.infrastructure.persistent.dao.IStrategyDAO;
import org.example.infrastructure.persistent.dao.IStrategyRuleDAO;
import org.example.infrastructure.persistent.po.Award;
import org.example.infrastructure.persistent.po.Strategy;
import org.example.infrastructure.persistent.po.StrategyAward;
import org.example.infrastructure.persistent.po.StrategyRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOTest {
    @Resource
    private IAwardDAO iAwardDAO;
    @Resource
    private IStrategyDAO iStrategyDAO;
    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;
    @Resource
    private IStrategyRuleDAO iStrategyRuleDAO;

    @Test
    public void testIStrategyDAO() {
        List<Strategy> strategyList = iStrategyDAO.queryStrategyList();
        for (Strategy s : strategyList) {
            System.out.println(s);
        }
    }

    @Test
    public void testIAwardDAO() {
        List<Award> awardList = iAwardDAO.queryAwardList();
        for (Award award : awardList) {
            System.out.println(award);
        }
    }

    @Test
    public void testIStrategyAwardDAO() {
        List<StrategyAward> strategyAwardList = iStrategyAwardDAO.queryStrategyAwardList();
        for (StrategyAward strategyAward: strategyAwardList) {
            System.out.println(strategyAward);
        }
    }

    @Test
    public void testIStrategyRuleDAO() {
        List<StrategyRule> strategyRules = iStrategyRuleDAO.queryStrategyRuleList();
        for (StrategyRule sr: strategyRules) {
            System.out.println(sr);
        }
    }

    @Test
    public void queryStrategyByIdTest() {
        Strategy strategy = iStrategyDAO.queryStrategyById(100001L);
        System.out.println(strategy);
    }

    @Test
    public void queryStrategyRuleTest() {
        StrategyRule strategy = iStrategyRuleDAO.queryStrategyRuleByIdAndName(100001L, "rule_weight");
        log.info(strategy.toString());
    }
}
