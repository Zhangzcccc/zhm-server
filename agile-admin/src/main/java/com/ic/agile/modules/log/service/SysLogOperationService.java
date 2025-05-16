package com.ic.agile.modules.log.service;

import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.log.dto.SysLogOperationDTO;
import com.ic.agile.modules.log.entity.SysLogOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}