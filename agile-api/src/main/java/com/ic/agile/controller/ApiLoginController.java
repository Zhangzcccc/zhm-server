package com.ic.agile.controller;


import com.ic.agile.annotation.Login;
import com.ic.agile.common.utils.Result;
import com.ic.agile.common.validator.ValidatorUtils;
import com.ic.agile.service.TokenService;
import com.ic.agile.service.UserService;
import com.ic.agile.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 登录接口
 *
 * @author zhangzc
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录接口")
@AllArgsConstructor
public class ApiLoginController {
    private final UserService userService;
    private final TokenService tokenService;


    @PostMapping("login")
    @ApiOperation("登录")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO dto) {
        //表单校验
        ValidatorUtils.validateEntity(dto);

        //用户登录
        Map<String, Object> map = userService.login(dto);

        return new Result().ok(map);
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public Result logout(@ApiIgnore @RequestAttribute("userId") Long userId) {
        tokenService.expireToken(userId);
        return new Result();
    }

}