package com.dwarfeng.voucher.stack.exception;

/**
 * 检查器执行异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CheckerExecutionException extends CheckerException {

    private static final long serialVersionUID = 3663621760706849677L;

    public CheckerExecutionException() {
    }

    public CheckerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckerExecutionException(String message) {
        super(message);
    }

    public CheckerExecutionException(Throwable cause) {
        super(cause);
    }
}
