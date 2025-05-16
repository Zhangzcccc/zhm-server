package com.ic.agile.modules.log.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.log.entity.SysLogErrorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 异常日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@Mapper
public interface SysLogErrorDao extends BaseDao<SysLogErrorEntity> {
	
}
