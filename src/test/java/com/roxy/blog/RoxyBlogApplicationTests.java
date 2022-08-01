package com.roxy.blog;

import com.alibaba.fastjson.JSON;
import com.roxy.blog.constant.ConstantPool;
import com.roxy.blog.dto.DetailedBlog;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.utils.BeanUtils;
import com.roxy.blog.utils.CompressUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@SpringBootTest
class RoxyBlogApplicationTests {
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
            System.out.println("第" + i + "次测试");
            start = matter.format(new Date());
            DetailedBlog detailedBlog = blogService.getDetailedBlog(3L);
            end = matter.format(new Date());
            System.out.println("开始时间:" + start + "   结束时间:" + end);
        }
    }
}
