package com.ic.agile.modules.log.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.log.entity.SysLogOperationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@Mapper
public interface SysLogOperationDao extends BaseDao<SysLogOperationEntity> {
	
}
