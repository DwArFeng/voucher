package com.dwarfeng.voucher.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 40000;

    public static final ServiceException.Code VOUCHER_CATEGORY_NOT_EXISTS =
            new ServiceException.Code(offset(0), "voucher category not exists");
    public static final ServiceException.Code VOUCHER_NOT_EXISTS =
            new ServiceException.Code(offset(10), "voucher not exists");
    public static final ServiceException.Code VOUCHER_ALREADY_EXISTS =
            new ServiceException.Code(offset(20), "voucher already exists");
    public static final ServiceException.Code INVALID_VOUCHER =
            new ServiceException.Code(offset(30), "invalid voucher");

    @SuppressWarnings("unused")
    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        VOUCHER_CATEGORY_NOT_EXISTS.setCode(offset(0));
        VOUCHER_NOT_EXISTS.setCode(offset(10));
        VOUCHER_ALREADY_EXISTS.setCode(offset(20));
        INVALID_VOUCHER.setCode(offset(30));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
