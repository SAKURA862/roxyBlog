package com.roxy.blog.service.Impl;

import com.roxy.blog.dao.TagMapper;
import com.roxy.blog.entity.Tag;
import com.roxy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> getAdminTag() {
        return tagMapper.getAdminTag();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagMapper.getTagById(aLong));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
