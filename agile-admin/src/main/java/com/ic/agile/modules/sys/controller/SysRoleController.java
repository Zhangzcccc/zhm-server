package com.ic.agile.modules.sys.controller;

import com.ic.agile.common.annotation.LogOperation;
import com.ic.agile.common.constant.Constant;
import com.ic.agile.common.page.PageData;
import com.ic.agile.common.utils.Result;
import com.ic.agile.common.validator.AssertUtils;
import com.ic.agile.common.validator.ValidatorUtils;
import com.ic.agile.common.validator.group.AddGroup;
import com.ic.agile.common.validator.group.DefaultGroup;
import com.ic.agile.common.validator.group.UpdateGroup;
import com.ic.agile.modules.sys.service.SysRoleDataScopeService;
import com.ic.agile.modules.sys.service.SysRoleMenuService;
import com.ic.agile.modules.sys.service.SysRoleService;
import com.ic.agile.modules.sys.dto.SysRoleDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author zhangzc
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "角色管理")
@AllArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "角色名", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("sys:role:page")
    public Result<PageData<SysRoleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<SysRoleDTO> page = sysRoleService.page(params);

        return new Result<PageData<SysRoleDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    @RequiresPermissions("sys:role:list")
    public Result<List<SysRoleDTO>> list() {
        List<SysRoleDTO> data = sysRoleService.list(new HashMap<>(1));

        return new Result<List<SysRoleDTO>>().ok(data);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:role:info")
    public Result<SysRoleDTO> get(@PathVariable("id") Long id) {
        SysRoleDTO data = sysRoleService.get(id);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
        data.setMenuIdList(menuIdList);

        //查询角色对应的数据权限
        List<Long> deptIdList = sysRoleDataScopeService.getDeptIdList(id);
        data.setDeptIdList(deptIdList);

        return new Result<SysRoleDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRoleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysRoleService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRoleDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysRoleService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysRoleService.delete(ids);

        return new Result();
    }
}