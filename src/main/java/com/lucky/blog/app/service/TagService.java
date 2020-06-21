package com.lucky.blog.app.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lucky.blog.app.entity.TagEntity;
import com.lucky.blog.app.vo.TagVo;
import com.lucky.blog.common.utils.PageUtils;

public interface TagService extends IService<TagEntity> {
    PageUtils queryPage(Map<String, Object> map);

    /**
     * 获取标签详情
     * @param tagIds
     * @return
     */
    List<TagEntity> queryHotTagDetails(Integer[] tagIds);

    /**
     * 查询标签详情
     * @return
     */
    List<TagVo> queryTagDetails();

    TagVo queryOneTagDetail(Integer tagId);
}