package com.ic.agile.modules.sys.controller;

import com.ic.agile.common.annotation.LogOperation;
import com.ic.agile.common.utils.Result;
import com.ic.agile.common.validator.AssertUtils;
import com.ic.agile.common.validator.ValidatorUtils;
import com.ic.agile.common.validator.group.AddGroup;
import com.ic.agile.common.validator.group.DefaultGroup;
import com.ic.agile.common.validator.group.UpdateGroup;
import com.ic.agile.modules.sys.dto.SysDeptDTO;
import com.ic.agile.modules.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 * @author zhangzc
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门管理")
@AllArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("list")
    @ApiOperation("列表")
    @RequiresPermissions("sys:dept:list")
    public Result<List<SysDeptDTO>> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));

        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:dept:info")
    public Result<SysDeptDTO> get(@PathVariable("id") Long id) {
        SysDeptDTO data = sysDeptService.get(id);

        return new Result<SysDeptDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dept:save")
    public Result save(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysDeptService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dept:update")
    public Result update(@RequestBody SysDeptDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDeptService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dept:delete")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");

        sysDeptService.delete(id);

        return new Result();
    }

}