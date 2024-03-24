package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 凭证不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherNotExistsException extends HandlerException {

    private static final long serialVersionUID = 3382525263712397487L;
    
    private final LongIdKey key;

    public VoucherNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public VoucherNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "凭证 " + key + " 不存在";
    }
}
