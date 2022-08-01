package com.roxy.blog;

import com.alibaba.fastjson.JSON;
import com.roxy.blog.config.ExecutorConfig;
import com.roxy.blog.constant.ConstantPool;
import com.roxy.blog.dto.DetailedBlog;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.utils.BeanUtils;
import com.roxy.blog.utils.CompressUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@SpringBootTest
class RoxyBlogApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(RoxyBlogApplicationTests.class);
    @Autowired
    private BlogService blogService;
    @Autowired
    @Qualifier(value = "template")
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void compressTest(){
        SimpleDateFormat matter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss:SSS");
        String start, end;
        for(int i = 0; i < 10; i++){
            start = matter.format(new Date());
            DetailedBlog detailedBlog = blogService.getDetailedBlog(3L);
            end = matter.format(new Date());
            logger.info("第{}次测试", i);
            logger.info("开始时间:{}   结束时间:{}", start, end);
        }
    }
}
