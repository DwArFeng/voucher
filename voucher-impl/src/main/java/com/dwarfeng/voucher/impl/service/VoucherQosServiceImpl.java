package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.stack.bean.dto.*;
import com.dwarfeng.voucher.stack.handler.VoucherHandler;
import com.dwarfeng.voucher.stack.service.VoucherQosService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class VoucherQosServiceImpl implements VoucherQosService {

    private final VoucherHandler voucherHandler;

    private final ServiceExceptionMapper sem;

    public VoucherQosServiceImpl(VoucherHandler voucherHandler, ServiceExceptionMapper sem) {
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

    @Override
    public void invalid(VoucherInvalidInfo invalidInfo) throws ServiceException {
        try {
            voucherHandler.invalid(invalidInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("作废凭证时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(VoucherRemoveInfo removeInfo) throws ServiceException {
        try {
            voucherHandler.remove(removeInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除凭证时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public CompletableFuture<Void> checkValid() throws ServiceException {
        try {
            return voucherHandler.checkValid();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("对服务中所有有效的凭证执行检查时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public CompletableFuture<Void> removeInvalid() throws ServiceException {
        try {
            return voucherHandler.removeInvalid();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除服务中所有作废的凭证时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
