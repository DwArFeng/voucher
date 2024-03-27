package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 凭证类别已被禁用异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VoucherCategoryDisabledException extends HandlerException {

    private static final long serialVersionUID = 6277830476136386592L;
    
    private final StringIdKey key;

    public VoucherCategoryDisabledException(StringIdKey key) {
        this.key = key;
    }

    public VoucherCategoryDisabledException(Throwable cause, StringIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "凭证类别 " + key + " 已被禁用";
    }
}
