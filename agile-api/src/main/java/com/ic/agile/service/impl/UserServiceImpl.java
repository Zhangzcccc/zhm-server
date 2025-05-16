package com.ic.agile.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.ic.agile.common.exception.ErrorCode;
import com.ic.agile.common.exception.RenException;
import com.ic.agile.common.service.impl.BaseServiceImpl;
import com.ic.agile.common.validator.AssertUtils;
import com.ic.agile.entity.UserEntity;
import com.ic.agile.service.TokenService;
import com.ic.agile.service.UserService;
import com.ic.agile.dao.UserDao;
import com.ic.agile.dto.LoginDTO;
import com.ic.agile.entity.TokenEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements UserService {
    private final TokenService tokenService;

    @Override
    public UserEntity getByMobile(String mobile) {
        return baseDao.getUserByMobile(mobile);
    }

    @Override
    public UserEntity getUserByUserId(Long userId) {
        return baseDao.getUserByUserId(userId);
    }

    @Override
    public Map<String, Object> login(LoginDTO dto) {
        UserEntity user = getByMobile(dto.getMobile());
        AssertUtils.isNull(user, ErrorCode.ACCOUNT_PASSWORD_ERROR);

        //密码错误
        if (!user.getPassword().equals(DigestUtil.sha256Hex(dto.getPassword()))) {
            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireDate().getTime() - System.currentTimeMillis());

        return map;
    }

}