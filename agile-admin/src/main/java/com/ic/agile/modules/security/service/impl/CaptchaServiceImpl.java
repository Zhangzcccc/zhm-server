package com.ic.agile.modules.security.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.ic.agile.common.redis.RedisKeys;
import com.ic.agile.common.redis.RedisUtils;
import com.ic.agile.modules.security.service.CaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 *
 * @author zhangzc
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Resource
    private RedisUtils redisUtils;
    @Value("${custom.redis.open: false}")
    private boolean open;

    @Value("${custom.need-captcha: true}")
    private boolean needCaptcha;
    /**
     * Local Cache  5分钟过期
     */
    Cache<String, String> localCache = CacheUtil.newLRUCache(1000, 1000 * 60 * 5);

    @Override
    public void create(HttpServletResponse response, String uuid) throws IOException {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        captcha.out(response.getOutputStream());

        //保存到缓存
        setCache(uuid, captcha.text());
    }

    @Override
    public boolean validate(String uuid, String code) {
        if (!needCaptcha) {
            return true;
        }
        //获取验证码
        String captcha = getCache(uuid);
        //效验成功
        return code.equalsIgnoreCase(captcha);
    }

    private void setCache(String key, String value) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 300);
        } else {
            localCache.put(key, value);
        }
    }

    private String getCache(String key) {
        if (open) {
            key = RedisKeys.getCaptchaKey(key);
            String captcha = (String) redisUtils.get(key);
            //删除验证码
            if (captcha != null) {
                redisUtils.delete(key);
            }

            return captcha;
        }

        String captcha = localCache.get(key);
        //删除验证码
        if (captcha != null) {
            localCache.remove(key);
        }
        return captcha;
    }
}