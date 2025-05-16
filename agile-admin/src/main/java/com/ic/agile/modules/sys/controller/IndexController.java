package com.ic.agile.modules.sys.controller;

import com.ic.agile.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页提示
 *
 * @author zhangzc
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Result<String> index(){
        String tips = "你好，后端已启动，请启动前端，才能访问页面！";
        return new Result<String>().ok(tips);
    }
}
