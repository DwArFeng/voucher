package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 凭证已经存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherAlreadyExistsException extends HandlerException {

    private static final long serialVersionUID = -8110124408215739L;

    private final LongIdKey key;

    public VoucherAlreadyExistsException(LongIdKey key) {
        this.key = key;
    }

    public VoucherAlreadyExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "凭证 " + key + " 已经存在";
    }
}
