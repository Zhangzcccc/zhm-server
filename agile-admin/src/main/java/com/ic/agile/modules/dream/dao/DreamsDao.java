package com.ic.agile.modules.dream.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.dream.entity.DreamsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户梦境
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@Mapper
public interface DreamsDao extends BaseDao<DreamsEntity> {
	
}