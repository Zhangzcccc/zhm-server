package com.ic.agile.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ic.agile.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_log_error")
public class SysLogErrorEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 请求URI
	 */
	private String requestUri;
	/**
	 * 请求方式
	 */
	private String requestMethod;
	/**
	 * 请求参数
	 */
	private String requestParams;
	/**
	 * 用户代理
	 */
	private String userAgent;
	/**
	 * 操作IP
	 */
	private String ip;
	/**
	 * 异常信息
	 */
	private String errorInfo;

}