package com.ic.agile.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ic.agile.common.constant.Constant;
import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.impl.BaseServiceImpl;
import com.ic.agile.common.utils.ConvertUtils;
import com.ic.agile.modules.log.dao.SysLogLoginDao;
import com.ic.agile.modules.log.dto.SysLogLoginDTO;
import com.ic.agile.modules.log.entity.SysLogLoginEntity;
import com.ic.agile.modules.log.service.SysLogLoginService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements SysLogLoginService {

    @Override
    public PageData<SysLogLoginDTO> page(Map<String, Object> params) {
        IPage<SysLogLoginEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogLoginDTO.class);
    }

    @Override
    public List<SysLogLoginDTO> list(Map<String, Object> params) {
        List<SysLogLoginEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogLoginDTO.class);
    }

    private QueryWrapper<SysLogLoginEntity> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");
        String creatorName = (String) params.get("creatorName");
        String createStartDate = (String) params.get("createStartDate");
        String createEndDate = (String) params.get("createEndDate");

        QueryWrapper<SysLogLoginEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(status), "status", status);
        wrapper.like(StrUtil.isNotBlank(creatorName), "creator_name", creatorName);
        if (StrUtil.isNotBlank(createStartDate) && StrUtil.isNotBlank(createEndDate)) {
            wrapper.between("create_date", createStartDate, createEndDate);
        }

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogLoginEntity entity) {
        insert(entity);
    }

}