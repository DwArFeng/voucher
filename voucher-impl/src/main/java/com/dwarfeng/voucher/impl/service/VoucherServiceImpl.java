package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCreateInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherInspectResult;
import com.dwarfeng.voucher.stack.handler.VoucherHandler;
import com.dwarfeng.voucher.stack.service.VoucherService;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherHandler voucherHandler;

    private final ServiceExceptionMapper sem;

    public VoucherServiceImpl(VoucherHandler voucherHandler, ServiceExceptionMapper sem) {
        this.voucherHandler = voucherHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey create(VoucherCreateInfo createInfo) throws ServiceException {
        try {
            return voucherHandler.create(createInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建凭证时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VoucherInspectResult inspect(VoucherInspectInfo inspectInfo) throws ServiceException {
        try {
            return voucherHandler.inspect(inspectInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看凭证时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
