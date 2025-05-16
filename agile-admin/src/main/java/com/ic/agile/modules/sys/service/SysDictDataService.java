package com.ic.agile.modules.sys.service;

import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.sys.entity.SysDictDataEntity;
import com.ic.agile.modules.sys.dto.SysDictDataDTO;

import java.util.Map;

/**
 * 数据字典
 *
 * @author zhangzc
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}