package com.roxy.blog.service.Impl;

import com.roxy.blog.dao.TypeMapper;
import com.roxy.blog.entity.Type;
import com.roxy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Type> getAdminType() {
        return typeMapper.getAdminType();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Type> findTypeListBefore(Integer size){
        return  typeMapper.findTypeListBefore(size);
    }
}
