package com.dwarfeng.voucher.node.configuration;

import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.voucher.stack.exception.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(VoucherCategoryNotExistsException.class, ServiceExceptionCodes.VOUCHER_CATEGORY_NOT_EXISTS);
        destination.put(VoucherNotExistsException.class, ServiceExceptionCodes.VOUCHER_NOT_EXISTS);
        destination.put(VoucherAlreadyExistsException.class, ServiceExceptionCodes.VOUCHER_ALREADY_EXISTS);
        destination.put(InvalidVoucherException.class, ServiceExceptionCodes.INVALID_VOUCHER);
        destination.put(CheckerException.class, ServiceExceptionCodes.CHECKER_FAILED);
        destination.put(CheckerMakeException.class, ServiceExceptionCodes.CHECKER_MAKE_FAILED);
        destination.put(CheckerExecutionException.class, ServiceExceptionCodes.CHECKER_EXECUTION_FAILED);
        destination.put(UnsupportedCheckerTypeException.class, ServiceExceptionCodes.CHECKER_TYPE_UNSUPPORTED);
        destination.put(CheckerInfoNotExistsException.class, ServiceExceptionCodes.CHECKER_INFO_NOT_EXISTS);
        destination.put(VoucherCategoryDisabledException.class, ServiceExceptionCodes.VOUCHER_CATEGORY_DISABLED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
