package org.example.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.service.IStrategyInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {


    @Resource
    private IStrategyInitializer iStrategyInitializer;



    @Test
    public void TestStrategyAward() {
        iStrategyInitializer.initializeStrategy(100001L);

    }
}
