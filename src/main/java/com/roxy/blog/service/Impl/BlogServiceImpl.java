package com.roxy.blog.service.Impl;

import com.github.pagehelper.PageInfo;
import com.roxy.blog.constant.ConstantPool;
import com.roxy.blog.dao.BlogMapper;
import com.roxy.blog.dto.*;
import com.roxy.blog.entity.Blog;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.exception.NotFountException;
import com.roxy.blog.service.BlogService;
import com.roxy.blog.utils.BeanUtils;
import com.roxy.blog.utils.CompressUtils;
import com.roxy.blog.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    @Qualifier(value = "template")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<Blog> findBlogByLike(SearchBlog blog) {
        List<Blog> list = blogMapper.findByLikeBlog(blog.getTitle(), blog.isRecommend(), blog.getTypeId());
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BlogQuery> getAllBlogQuery() {
        return blogMapper.getAllBlogQuery();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        Long res = blogMapper.saveBlog(blog);
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        int res = blogMapper.updateBlog(showBlog);
        redisTemplate.delete(ConstantPool.REDIS_DETAIL_BLOG_PREFIX + showBlog.getId());
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBlog(Long id) {
        blogMapper.deleteBlogAndTag(id);
        blogMapper.deleteBlog(id);
        redisTemplate.delete(ConstantPool.REDIS_DETAIL_BLOG_PREFIX + id);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog) {
        return blogMapper.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transformRecommend(SearchBlog searchBlog) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogMapper.getAllFirstPageBlog();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            if (recommendBlog.isRecommend()) {
                allRecommendedBlog.add(recommendBlog);
            }
        }
        return allRecommendedBlog;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog;
        if(Boolean.TRUE.equals(redisTemplate.hasKey(ConstantPool.REDIS_DETAIL_BLOG_PREFIX + id))){
            Map<?, Object> value = redisTemplate.opsForHash().entries(ConstantPool.REDIS_DETAIL_BLOG_PREFIX + id);
            detailedBlog = (DetailedBlog) BeanUtils.mapToObject((Map<String, Object>) value, DetailedBlog.class);
            detailedBlog.setContent(CompressUtils.deflateUncompress(detailedBlog.getContent()));
        }
        else{
            detailedBlog = blogMapper.getDetailedBlog(id);
            if (detailedBlog == null) {
                throw new NotFountException("该博客不存在");
            }
            Map<String, Object> tmp = (Map<String, Object>) BeanUtils.objectToMap(detailedBlog);
            tmp.put("content", CompressUtils.deflateCompress((String) tmp.get("content")));
            redisTemplate.opsForHash().putAll(ConstantPool.REDIS_DETAIL_BLOG_PREFIX + id, tmp);
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogMapper.updateViews(id);
        return detailedBlog;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }
}
