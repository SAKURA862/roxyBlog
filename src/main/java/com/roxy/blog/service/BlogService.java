package com.roxy.blog.service;

import com.github.pagehelper.PageInfo;
import com.roxy.blog.dto.*;
import com.roxy.blog.entity.Blog;

import java.util.List;

/**
 * 博客相关操作
 */
public interface BlogService {

    /**
     * 根据博客标题模糊查询，根据分类、是否推荐查询
     * @param blog 查询的标题、分类、是否推荐
     * @return 满足条件的博客
     */
    PageInfo<Blog> findBlogByLike(SearchBlog blog);

    /**
     * 根据 id 查询博客
     * @param id 查询的 id
     * @return 指定的博客
     */
    ShowBlog getBlogById(Long id);

    /**
     * 查询所有博客
     * @return 所有博客
     */
    List<BlogQuery> getAllBlogQuery();

    /**
     * 保存博客
     * @param blog 新增的博客
     * @return 新增的博客 id
     */
    long saveBlog(Blog blog);

    /**
     * 更新博客
     * @param showBlog 修改后的博客
     * @return 是否更新成功
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 删除博客
     * @param id 待删除博客 id
     * @return 是否删除成功
     */
    int deleteBlog(Long id);

    /**
     * 根据博客标题模糊查询，根据分类、是否推荐查询
     * @param blog 查询的标题、分类、是否推荐
     * @return 满足条件的博客
     */
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    /**
     * 修改recommend,因为recommend从前台接收只能接收字符串，但数据库中的Integer类型，所以转一下
     * @param searchBlog 待转换的博客
     */
    void transformRecommend(SearchBlog searchBlog);

    /**
     * 获取第一页的博客
     * @return 第一页的博客
     */
    List<FirstPageBlog> getAllFirstPageBlog();

    /**
     * 获取所有推荐的博客
     * @return 所有推荐阿博客
     */
    List<RecommendBlog> getRecommendedBlog();

    /**
     * 根据输入模糊查询标题和内容包含输入的博客
     * @param query 查询的字符串
     * @return 满足条件的所有博客
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 根据 id 获取博客详细信息
     * @param id 博客 id
     * @return 博客的详细信息
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 根据TypeId获取博客列表，在分类页进行的操作
     * @param typeId 指定的分类 id
     * @return 该分类的博客
     */
    List<FirstPageBlog> getByTypeId(Long typeId);

    /**
     * 根据 tagId 获取博客列表，在标签页进行的操作
     * @param tagId 指定的标签 id
     * @return 该标签的博客
     */
    List<FirstPageBlog> getByTagId(Long tagId);


}
