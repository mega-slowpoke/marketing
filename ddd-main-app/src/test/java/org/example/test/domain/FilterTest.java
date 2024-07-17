package org.example.test.domain;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterTest {

    @Resource
    private IBeforeFilter iBeforeFilter;

    @Test
    public void filterTest() {
        iBeforeFilter.filter();
        doLottery();
    }


}
