package com.ic.agile.service;

import com.ic.agile.common.service.BaseService;
import com.ic.agile.entity.UserEntity;
import com.ic.agile.dto.LoginDTO;

import java.util.Map;

/**
 * 用户
 * 
 * @author zhangzc
 */
public interface UserService extends BaseService<UserEntity> {

	UserEntity getByMobile(String mobile);

	UserEntity getUserByUserId(Long userId);

	/**
	 * 用户登录
	 * @param dto    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(LoginDTO dto);
}
