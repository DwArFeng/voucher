package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效凭证异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InvalidVoucherException extends HandlerException {

    private static final long serialVersionUID = -2703112628120335472L;

    private final LongIdKey key;

    public InvalidVoucherException(LongIdKey key) {
        this.key = key;
    }

    public InvalidVoucherException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "无效的凭证: " + key;
    }
}
