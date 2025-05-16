package com.ic.agile.modules.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ic.agile.modules.security.dao.SysUserTokenDao;
import com.ic.agile.modules.security.entity.SysUserTokenEntity;
import com.ic.agile.modules.security.service.ShiroService;
import com.ic.agile.modules.security.user.UserDetail;
import com.ic.agile.modules.sys.dao.SysMenuDao;
import com.ic.agile.modules.sys.dao.SysRoleDataScopeDao;
import com.ic.agile.modules.sys.dao.SysUserDao;
import com.ic.agile.modules.sys.entity.SysUserEntity;
import com.ic.agile.modules.sys.enums.SuperAdminEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShiroServiceImpl implements ShiroService {
    private final SysMenuDao sysMenuDao;
    private final SysUserDao sysUserDao;
    private final SysUserTokenDao sysUserTokenDao;
    private final SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuDao.getPermissionsList();
        } else {
            permissionsList = sysMenuDao.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StrUtil.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserTokenEntity getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeDao.getDataScopeList(userId);
    }
}