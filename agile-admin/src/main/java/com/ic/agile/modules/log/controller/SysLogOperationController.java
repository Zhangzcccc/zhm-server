package com.ic.agile.modules.log.controller;

import com.ic.agile.common.annotation.LogOperation;
import com.ic.agile.common.constant.Constant;
import com.ic.agile.common.page.PageData;
import com.ic.agile.common.utils.ExcelUtils;
import com.ic.agile.common.utils.Result;
import com.ic.agile.modules.log.excel.SysLogOperationExcel;
import com.ic.agile.modules.log.service.SysLogOperationService;
import com.ic.agile.modules.log.dto.SysLogOperationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 操作日志
 *
 * @author zhangzc
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/log/operation")
@Api(tags = "操作日志")
@AllArgsConstructor
public class SysLogOperationController {
    private final SysLogOperationService sysLogOperationService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态  0：失败    1：成功", paramType = "query", dataType = "int")
    })
    @RequiresPermissions("sys:log:operation")
    public Result<PageData<SysLogOperationDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysLogOperationDTO> page = sysLogOperationService.page(params);

        return new Result<PageData<SysLogOperationDTO>>().ok(page);
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:log:operation")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogOperationDTO> list = sysLogOperationService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "操作日志", list, SysLogOperationExcel.class);
    }

}