package com.ic.agile.modules.log.service;

import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.log.dto.SysLogLoginDTO;
import com.ic.agile.modules.log.entity.SysLogLoginEntity;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageData<SysLogLoginDTO> page(Map<String, Object> params);

    List<SysLogLoginDTO> list(Map<String, Object> params);

    void save(SysLogLoginEntity entity);
}