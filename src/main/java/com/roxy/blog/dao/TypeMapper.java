package com.roxy.blog.dao;

import com.roxy.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {

    /**
     * 保存分类
     * @param type 待保存的分类
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
     * @param name 分类名称
     * @return 分类
     */
    Type getTypeByName(String name);

    /**
     * 查询所有有博客的分类
     * @return 有博客的分类
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
     * @param type 新的分类
     * @return 是否成功
     */
    int updateType(Type type);

    /**
     * 查询之前的分类列表
     * @param size 查询数量
     * @return 指定数量的分类
     */
    List<Type> findTypeListBefore(Integer size);
}
