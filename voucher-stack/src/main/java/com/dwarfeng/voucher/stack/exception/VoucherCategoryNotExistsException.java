package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 凭证类型不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryNotExistsException extends HandlerException {

    private static final long serialVersionUID = 7141094846593326253L;
    
    private final StringIdKey key;

    public VoucherCategoryNotExistsException(StringIdKey key) {
        this.key = key;
    }

    public VoucherCategoryNotExistsException(Throwable cause, StringIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "凭证类型 " + key + " 不存在";
    }
}
