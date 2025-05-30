package com.ic.agile.modules.sys.enums;

/**
 * 用户状态
 *
 * @author zhangzc
 * @since 1.0.0
 */
public enum UserStatusEnum {
    DISABLE(0),
    ENABLED(1);

    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
