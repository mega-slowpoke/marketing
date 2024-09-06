package org.example.test.domain.activity;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.RechargeReqEntity;
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

    @Test
    public void test_createRaffleOrder() {
        RechargeReqEntity rechargeReqEntity = new RechargeReqEntity();
        rechargeReqEntity.setUserId("xiaofuge");
        rechargeReqEntity.setSkuId(9011L);
        // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
        rechargeReqEntity.setOutBusinessNo("700091009111");
        String orderId = iRaffleOrderService.rechargeUserBalance(rechargeReqEntity);
        log.info("测试结果：{}", orderId);
    }
}
