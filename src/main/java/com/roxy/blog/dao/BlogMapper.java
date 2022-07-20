package com.roxy.blog.dao;

import com.roxy.blog.dto.*;
import com.roxy.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客持久层操作类
 * @author roxy
 */
@Mapper
@Repository
public interface BlogMapper {

    /**
     * 根据 id 查询博客
     * @param id 博客 id
     * @return 查询到的博客
     */
    ShowBlog getBlogById(Long id);

    /**
     * 查询所有博客
     * @return 所有博客
     */
    List<BlogQuery> getAllBlogQuery();

    /**
     * 保存博客
     * @param blog 待插入的博客
     * @return 插入后的博客 id
     */
    long saveBlog(Blog blog);

    /**
     * 删除博客
     * @param id 待删除的博客 id
     * @return 是否成功
     */
    int deleteBlog(Long id);

    /**
     * 更新博客
     * @param showBlog 新的博客
     * @return 是否成功
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 插入 BlogAndTag 表数据
     * @param blogAndTag 博客和标签映射信息
     * @return 是否成功
     */
    int saveBlogAndTag(BlogAndTag blogAndTag);

    /**
     * 删除 BlogAndTag 表数据
     * @param blogId 待删除的博客和标签映射信息 id
     * @return 是否成功
     */
    int deleteBlogAndTag(Long blogId);

    /**
     * 根据标题模糊查询，根据分类、是否推荐查询
     * @param searchBlog 待查询的博客信息
     * @return 满足条件的博客
     */
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    /**
     * 获取第一页的博客
     * @return 第一页的博客
     */
    List<FirstPageBlog> getAllFirstPageBlog();

    /**
     * 获取所有推荐的博客
     * @return 所有推荐的博客
     */
    List<RecommendBlog> getAllRecommendBlog();

    /**
     * 根据标题和内容模糊查询
     * @param query 待查询字符串
     * @return 满足条件的博客
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 获取博客详细信息
     * @param id 博客 id
     * @return 详细信息
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 根据分类 id 查询博客
     * @param typeId 分类 id
     * @return 指定分类的所有博客
     */
    List<FirstPageBlog> getByTypeId(Long typeId);

    /**
     * 根据标签 id 查询博客
     * @param tagId 标签 id
     * @return 指定标签的所有博客
     */
    List<FirstPageBlog> getByTagId(Long tagId);

    /**
     * 根据标题模糊查询，根据分类、是否推荐查询
     * @param title 模糊查询的标题
     * @param recommend 是否推荐
     * @param type_id 分类 id
     * @return 满足条件的博客
     */
    List<Blog> findByLikeBlog(String title, boolean recommend, Long type_id);

    /**
     * 更新观看量（观看量 + 1）
     * @param id 博客 id
     * @return 是否成功
     */
    int updateViews(Long id);

}
