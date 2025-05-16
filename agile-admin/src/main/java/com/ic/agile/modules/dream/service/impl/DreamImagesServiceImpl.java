package com.ic.agile.modules.dream.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ic.agile.common.service.impl.CrudServiceImpl;
import com.ic.agile.modules.dream.dao.DreamImagesDao;
import com.ic.agile.modules.dream.dto.DreamImagesDTO;
import com.ic.agile.modules.dream.entity.DreamImagesEntity;
import com.ic.agile.modules.dream.service.DreamImagesService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 梦境图片
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Service
public class DreamImagesServiceImpl extends CrudServiceImpl<DreamImagesDao, DreamImagesEntity, DreamImagesDTO> implements DreamImagesService {

    @Override
    public QueryWrapper<DreamImagesEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<DreamImagesEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}