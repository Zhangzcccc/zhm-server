package com.ic.agile.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.ic.agile.common.utils.Result;
import com.ic.agile.common.validator.ValidatorUtils;
import com.ic.agile.entity.UserEntity;
import com.ic.agile.service.UserService;
import com.ic.agile.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册接口
 *
 * @author zhangzc
 */
@RestController
@RequestMapping("/api")
@Api(tags = "注册接口")
@AllArgsConstructor
public class ApiRegisterController {
    private final UserService userService;

    @PostMapping("register")
    @ApiOperation("注册")
    public Result register(@RequestBody RegisterDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);

        UserEntity user = new UserEntity();
        user.setMobile(dto.getMobile());
        user.setUsername(dto.getMobile());
        user.setPassword(DigestUtil.sha256Hex(dto.getPassword()));
        user.setCreateDate(new Date());
        userService.insert(user);

        return new Result();
    }
}