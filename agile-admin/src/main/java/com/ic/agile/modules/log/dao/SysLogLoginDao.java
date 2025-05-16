package com.ic.agile.modules.log.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.log.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {
	
}
