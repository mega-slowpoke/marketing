package org.example.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private IRedisService iRedisService;

    @Test
    public void clearRedis() {
        iRedisService.clearAllKeys();
    }
}
