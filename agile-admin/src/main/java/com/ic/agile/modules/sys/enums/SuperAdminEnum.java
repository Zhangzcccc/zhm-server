package com.ic.agile.modules.sys.enums;

/**
 * 超级管理员枚举
 *
 * @author zhangzc
 * @since 1.0.0
 */
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}