package com.dwarfeng.voucher.stack.exception;

/**
 * 检查器构造异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CheckerMakeException extends CheckerException {

    private static final long serialVersionUID = -3663069788727798713L;

    public CheckerMakeException() {
    }

    public CheckerMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckerMakeException(String message) {
        super(message);
    }

    public CheckerMakeException(Throwable cause) {
        super(cause);
    }
}
