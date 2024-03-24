package com.dwarfeng.voucher.impl.configuration;

import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.voucher.stack.exception.InvalidVoucherException;
import com.dwarfeng.voucher.stack.exception.VoucherAlreadyExistsException;
import com.dwarfeng.voucher.stack.exception.VoucherCategoryNotExistsException;
import com.dwarfeng.voucher.stack.exception.VoucherNotExistsException;
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
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
