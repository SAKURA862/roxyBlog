package com.roxy.blog.service;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.Api;
import com.roxy.blog.entity.Blog;

import java.io.IOException;

public interface QiNiuService {

    Boolean uploadBlog(Blog blog);

    Boolean deleteBlog(String title);

    Boolean downloadBlog(Blog blog);
}
