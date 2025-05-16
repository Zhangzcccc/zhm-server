package com.ic.agile.modules.security.controller;

import com.alibaba.fastjson.JSON;
import com.ic.agile.common.utils.HttpUtils;
import com.ic.agile.common.utils.Result;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhangZc
 * @date: 2025/5/15 18:16
 * @description: 微信小程序登录相关
 */
@RestController
@Api(tags = "微信小程序登录管理")
@AllArgsConstructor
@Slf4j
public class wechatLoginController {
    @GetMapping("/wxLogin/{code}")
    public Result doWxLogin(@PathVariable String code) {
        String appId = "wx391bce13343d272e"; // 替换为你的小程序AppID
        String appSecret = "bf01b2de9eac5e4c4669e980637d1edf"; // 替换为你的小程序AppSecret
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";

        String result = HttpUtils.doGet(url, null, null, null);
        log.info("微信登录返回结果：{}", result);
        return new Result().ok(JSON.parseObject(result));
    }
}
