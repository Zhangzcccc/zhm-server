package com.ic.agile.modules.sys.service;

import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.sys.entity.DictType;
import com.ic.agile.modules.sys.entity.SysDictTypeEntity;
import com.ic.agile.modules.sys.dto.SysDictTypeDTO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author zhangzc
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageData<SysDictTypeDTO> page(Map<String, Object> params);

    SysDictTypeDTO get(Long id);

    void save(SysDictTypeDTO dto);

    void update(SysDictTypeDTO dto);

    void delete(Long[] ids);

    /**
     * 获取所有字典
     */
    List<DictType> getAllList();

}