package com.roxy.blog.service;

import com.roxy.blog.entity.Tag;

import java.util.List;

/**
 * 标签相关操作
 */
public interface TagService {

    /**
     * 保存标签
     * @param tag 待保存的标签
     * @return 是否成功
     */
    int saveTag(Tag tag);

    /**
     * 删除标签
     * @param id 待删除的标签 id
     * @return 是否成功
     */
    int deleteTag(Long id);

    /**
     * 更新标签
     * @param tag 更新后的标签
     * @return 是否成功
     */
    int updateTag(Tag tag);

    /**
     * 根据 id 查询标签
     * @param id 标签 id
     * @return 标签信息
     */
    Tag getTagById(Long id);

    /**
     * 根据标签名查询表亲
     * @param name 标签名
     * @return 满足条件的标签
     */
    Tag getTagByName(String name);

    /**
     * 查询所有标签（访客查询，只能查询到有博客的标签）
     * @return 所有有博客的标签
     */
    List<Tag> getAllTag();

    /**
     * 博主后台管理查询所有标签（能查询到所有标签）
     * @return 所有标签
     */
    List<Tag> getAdminTag();

    /**
     * 根据内容获取标签
     * @param text 内容
     * @return 标签
     */
    List<Tag> getTagByString(String text);
}
