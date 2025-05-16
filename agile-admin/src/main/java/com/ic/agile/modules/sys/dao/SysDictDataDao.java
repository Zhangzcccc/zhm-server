package com.ic.agile.modules.sys.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.sys.entity.DictData;
import com.ic.agile.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据
 *
 * @author zhangzc
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList();
}
