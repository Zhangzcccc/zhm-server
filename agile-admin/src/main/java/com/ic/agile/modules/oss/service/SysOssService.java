package com.ic.agile.modules.oss.service;

import com.ic.agile.common.page.PageData;
import com.ic.agile.common.service.BaseService;
import com.ic.agile.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 * 
 * @author zhangzc
 */
public interface SysOssService extends BaseService<SysOssEntity> {

	PageData<SysOssEntity> page(Map<String, Object> params);
}
