package com.ic.agile.modules.sys.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.sys.entity.DictType;
import com.ic.agile.modules.sys.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型
 *
 * @author zhangzc
 */
@Mapper
public interface SysDictTypeDao extends BaseDao<SysDictTypeEntity> {

    /**
     * 字典类型列表
     */
    List<DictType> getDictTypeList();

}
