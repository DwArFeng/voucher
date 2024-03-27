package com.dwarfeng.voucher.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 检查器异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CheckerException extends HandlerException {

    private static final long serialVersionUID = -8007906112063965488L;

    public CheckerException() {
    }

    public CheckerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckerException(String message) {
        super(message);
    }

    public CheckerException(Throwable cause) {
        super(cause);
    }
}
