package com.roxy.blog;

import com.qiniu.common.QiniuException;
import com.roxy.blog.entity.Blog;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.service.QiNiuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
public class QiNiuTest {

    @Autowired
    private QiNiuService qiNiuService;

    @Test
    void upload(){
        Blog blog = new Blog();
        blog.setContent("snadhasdijasljkdnsaljkdaskljdhjioae发布诺i大声反驳那是的话牛角扣俩手机的i欧里卡尔手机打开拉萨电话建立杀害多久哦吉拉数据的卡拉省的距离喀什阿什顿h'd'j'o建立撒谎的距离喀什的距离喀什加快了的哈设计看了还多久啊螺丝扣和距离喀什大家坷拉是卡拉士大夫dioae恩加拉快递费和较快拉升的距离喀什啊是的你h'n'd'j'k就卡省的就卡省的发j动画风j苦涩u阿娇让峰会上划分等级考试妇女健康的萨芬了就卡省的n'j'la's'd'n'd'h'f距离喀什电话你距离喀什的距离喀什发动机康拉德回复决定和j'liasjdieurfdjaskldncklfd9o0-=9i0-90kl.,./m,;l/l[polp['kl;,k");
        blog.setTitle("[LeetCode]10.正则表达式匹配");
        System.out.println(qiNiuService.uploadBlog(blog));
    }

    @Test
    void download(){
        Blog blog = new Blog();
        blog.setTitle("[LeetCode]10.正则表达式匹配");
        blog.setContent("http://cdn.roxysaikou.top/blogs/44f0521fc380f2cdbf9f415133c6f3ef");
        qiNiuService.downloadBlog(blog);
        System.out.println(blog.getContent());
    }

    @Test
    void delete(){
        System.out.println(qiNiuService.deleteBlog("[LeetCode]10.正则表达式匹配"));
    }

}
