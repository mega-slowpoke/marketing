package org.example.test.domain.activity;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.RaffleOrderReqEntity;
import org.example.domain.activity.service.IRaffleOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

    @Resource
    private IRaffleOrderService iRaffleOrderService;


}
