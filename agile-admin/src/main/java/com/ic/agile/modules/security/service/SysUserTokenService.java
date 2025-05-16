package com.ic.agile.modules.security.service;

import com.ic.agile.common.service.BaseService;
import com.ic.agile.common.utils.Result;
import com.ic.agile.modules.security.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author zhangzc
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}