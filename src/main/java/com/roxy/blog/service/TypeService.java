package com.roxy.blog.service;

import com.roxy.blog.entity.Type;

import java.util.List;

public interface TypeService {
    /**
     * 保存标签
     * @param type 分类
     * @return 是否成功
     */
    int saveType(Type type);

    /**
     * 根据 id 查询分类
     * @param id 分类 id
     * @return 分类
     */
    Type getTypeById(Long id);

    /**
     * 根据名称查询分类
     * @param name 分类名字
     * @return 分类
     */
    Type getTypeByName(String name);

    /**
     * 查询所有分类（访客查看的，只能查询有博客的分类）
     * @return 所有有博客的分类
     */
    List<Type> getAllType();

    /**
     * 查询所有分类
     * @return 所有分类
     */
    List<Type> getAdminType();

    /**
     * 删除分类
     * @param id 待删除的分类 id
     * @return 是否成功
     */
    int deleteType(Long id);

    /**
     * 更新分类
     * @param type 更新后的分类
     * @return 是否成功
     */
    int updateType(Type type);

    /**
     * 首页的显示分类
     * @param size 显示数量
     * @return 指定数量的分类
     */
    List<Type> findTypeListBefore(Integer size);
}
