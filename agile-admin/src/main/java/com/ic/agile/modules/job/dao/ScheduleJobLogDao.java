package com.ic.agile.modules.job.dao;

import com.ic.agile.common.dao.BaseDao;
import com.ic.agile.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author zhangzc
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
