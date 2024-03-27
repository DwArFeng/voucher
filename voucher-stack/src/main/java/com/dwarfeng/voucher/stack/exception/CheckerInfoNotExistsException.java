package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 检查器信息不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CheckerInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -4323392365169738915L;

    private final StringIdKey key;

    public CheckerInfoNotExistsException(StringIdKey key) {
        this.key = key;
    }

    public CheckerInfoNotExistsException(Throwable cause, StringIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "检查器信息 " + key + " 不存在";
    }
}
