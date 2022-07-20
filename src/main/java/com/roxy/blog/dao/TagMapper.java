package com.roxy.blog.dao;

import com.roxy.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签持久层类
 * @author roxy
 */
@Mapper
@Repository
public interface TagMapper {
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
     * @param tag 新的标签
     * @return 是否成功
     */
    int updateTag(Tag tag);

    /**
     * 根据 id 查询标签
     * @param id 标签 id
     * @return 标签
     */
    Tag getTagById(Long id);

    /**
     * 根据标签名查询标签
     * @param name 标签名
     * @return 标签
     */
    Tag getTagByName(String name);

    /**
     * 查询所有标签（访客看到的，有博客的标签）
     * @return 所有有博客的标签
     */
    List<Tag> getAllTag();

    /**
     * 查询所有标签
     * @return 所有标签
     */
    List<Tag> getAdminTag();
}
