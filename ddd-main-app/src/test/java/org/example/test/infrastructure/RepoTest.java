package org.example.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.repository.IStrategyRepo;
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
}
