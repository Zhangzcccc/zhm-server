package com.ic.agile.modules.dream.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ic.agile.common.service.impl.CrudServiceImpl;
import com.ic.agile.modules.dream.dao.DreamsDao;
import com.ic.agile.modules.dream.dto.DreamsDTO;
import com.ic.agile.modules.dream.entity.DreamsEntity;
import com.ic.agile.modules.dream.service.DreamsService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户梦境
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Service
public class DreamsServiceImpl extends CrudServiceImpl<DreamsDao, DreamsEntity, DreamsDTO> implements DreamsService {

    @Override
    public QueryWrapper<DreamsEntity> getWrapper(Map<String, Object> params){
        String userId = (String)params.get("userId");

        QueryWrapper<DreamsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(userId), "user_id", userId);

        return wrapper;
    }
}