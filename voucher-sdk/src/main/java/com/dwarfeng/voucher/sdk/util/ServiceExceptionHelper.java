package com.dwarfeng.voucher.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.stack.exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常的帮助工具类。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 voucher 默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速的搭建目标映射。
     *
     * @param map 指定的映射，允许为 <code>null</code>。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, ServiceException.Code> putDefaultDestination(
            Map<Class<? extends Exception>, ServiceException.Code> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(VoucherCategoryNotExistsException.class, ServiceExceptionCodes.VOUCHER_CATEGORY_NOT_EXISTS);
        map.put(VoucherNotExistsException.class, ServiceExceptionCodes.VOUCHER_NOT_EXISTS);
        map.put(VoucherAlreadyExistsException.class, ServiceExceptionCodes.VOUCHER_ALREADY_EXISTS);
        map.put(InvalidVoucherException.class, ServiceExceptionCodes.INVALID_VOUCHER);
        map.put(CheckerException.class, ServiceExceptionCodes.CHECKER_FAILED);
        map.put(CheckerMakeException.class, ServiceExceptionCodes.CHECKER_MAKE_FAILED);
        map.put(CheckerExecutionException.class, ServiceExceptionCodes.CHECKER_EXECUTION_FAILED);
        map.put(UnsupportedCheckerTypeException.class, ServiceExceptionCodes.CHECKER_TYPE_UNSUPPORTED);
        map.put(CheckerInfoNotExistsException.class, ServiceExceptionCodes.CHECKER_INFO_NOT_EXISTS);
        map.put(VoucherCategoryDisabledException.class, ServiceExceptionCodes.VOUCHER_CATEGORY_DISABLED);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}
