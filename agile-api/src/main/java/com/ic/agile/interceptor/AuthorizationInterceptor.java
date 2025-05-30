package com.ic.agile.interceptor;

import cn.hutool.core.util.StrUtil;
import com.ic.agile.annotation.Login;
import com.ic.agile.common.exception.ErrorCode;
import com.ic.agile.common.exception.RenException;
import com.ic.agile.service.TokenService;
import com.ic.agile.entity.TokenEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author zhangzc
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private TokenService tokenService;

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StrUtil.isBlank(token)) {
            throw new RenException(ErrorCode.TOKEN_NOT_EMPTY);
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.getByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
            throw new RenException(ErrorCode.TOKEN_INVALID);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
