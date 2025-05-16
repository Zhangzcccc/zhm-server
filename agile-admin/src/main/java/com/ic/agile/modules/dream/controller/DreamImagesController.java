package com.ic.agile.modules.dream.controller;

import com.ic.agile.common.annotation.LogOperation;
import com.ic.agile.common.constant.Constant;
import com.ic.agile.common.page.PageData;
import com.ic.agile.common.utils.ExcelUtils;
import com.ic.agile.common.utils.Result;
import com.ic.agile.common.validator.AssertUtils;
import com.ic.agile.common.validator.ValidatorUtils;
import com.ic.agile.common.validator.group.AddGroup;
import com.ic.agile.common.validator.group.DefaultGroup;
import com.ic.agile.common.validator.group.UpdateGroup;
import com.ic.agile.modules.dream.dto.DreamImagesDTO;
import com.ic.agile.modules.dream.excel.DreamImagesExcel;
import com.ic.agile.modules.dream.service.DreamImagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 梦境图片
 *
 * @author zhangzc xxx
 * @since 1.0.0 2025-04-28
 */
@RestController
@RequestMapping("dream/dreamimages")
@Api(tags="梦境图片")
public class DreamImagesController {
    @Autowired
    private DreamImagesService dreamImagesService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("dream:dreamimages:page")
    public Result<PageData<DreamImagesDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<DreamImagesDTO> page = dreamImagesService.page(params);

        return new Result<PageData<DreamImagesDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("dream:dreamimages:info")
    public Result<DreamImagesDTO> get(@PathVariable("id") Long id){
        DreamImagesDTO data = dreamImagesService.get(id);

        return new Result<DreamImagesDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("dream:dreamimages:save")
    public Result save(@RequestBody DreamImagesDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        dreamImagesService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("dream:dreamimages:update")
    public Result update(@RequestBody DreamImagesDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        dreamImagesService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("dream:dreamimages:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        dreamImagesService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("dream:dreamimages:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DreamImagesDTO> list = dreamImagesService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "梦境图片", list, DreamImagesExcel.class);
    }

}