package com.zyq.HitBytebin;

import com.zyq.HitBytebin.mapper.ArticleMapper;
import com.zyq.HitBytebin.serivce.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.FileNotFoundException;

@SpringBootTest
class BloggyApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    RedisService redisService;


    @Test
    void contextLoads() throws FileNotFoundException {

    }

}
