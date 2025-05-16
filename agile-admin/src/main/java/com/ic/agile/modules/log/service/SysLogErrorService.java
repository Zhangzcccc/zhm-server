package com.ic.agile.modules.log.service;


import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.log.dto.SysLogErrorDTO;
import com.ic.agile.modules.log.entity.SysLogErrorEntity;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}