package com.roxy.blog.service.Impl;

import com.google.common.io.ByteStreams;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.roxy.blog.entity.Blog;
import com.roxy.blog.exception.DeleteException;
import com.roxy.blog.exception.UploadException;
import com.roxy.blog.service.QiNiuService;
import com.roxy.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class QiNiuServiceImpl implements QiNiuService {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.domain}")
    private String domain;

    @Value("${qiniu.path}")
    private String path;

    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;

    @Override
    public Boolean uploadBlog(Blog blog){
        byte[] content = blog.getContent().getBytes();
        String fileName = path + MD5Utils.code(blog.getTitle());
        try {
            Response response = this.uploadManager.put(content, fileName, getUploadToken());
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(content, fileName, getUploadToken());
                retry++;
            }
            if (response.statusCode == 200) {
                blog.setContent("http://" + domain + "/" + fileName);
                return true;
            }
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Boolean downloadBlog(Blog blog){
        String url = blog.getContent();
        try {
            InputStream inputStream = new URL(url).openStream();
            blog.setContent(new String(ByteStreams.toByteArray(inputStream)));
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean deleteBlog(String title) {
        String fileName = path + MD5Utils.code(title);
        try {
            Response response = bucketManager.delete(bucket, fileName);
            int retry = 0;
            while (response.needRetry() && retry++ < 3) {
                response = bucketManager.delete(bucket, fileName);
            }
            if(response.statusCode == 200){
                return true;
            }
        } catch (QiniuException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 获取上传凭证
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
