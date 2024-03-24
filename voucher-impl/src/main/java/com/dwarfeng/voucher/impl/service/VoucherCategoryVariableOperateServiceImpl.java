package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableInspectInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariablePutInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherCategoryVariableRemoveInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.handler.VoucherCategoryVariableOperateHandler;
import com.dwarfeng.voucher.stack.service.VoucherCategoryVariableOperateService;
import org.springframework.stereotype.Service;

@Service
public class VoucherCategoryVariableOperateServiceImpl implements VoucherCategoryVariableOperateService {

    private final VoucherCategoryVariableOperateHandler voucherCategoryVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public VoucherCategoryVariableOperateServiceImpl(
            VoucherCategoryVariableOperateHandler voucherCategoryVariableOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.voucherCategoryVariableOperateHandler = voucherCategoryVariableOperateHandler;
        this.sem = sem;
    }

    @Override
    public VoucherCategoryVariable inspect(VoucherCategoryVariableInspectInfo inspectInfo) throws ServiceException {
        try {
            return voucherCategoryVariableOperateHandler.inspect(inspectInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看凭证类型变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void put(VoucherCategoryVariablePutInfo putInfo) throws ServiceException {
        try {
            voucherCategoryVariableOperateHandler.put(putInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("设置凭证类型变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(VoucherCategoryVariableRemoveInfo removeInfo) throws ServiceException {
        try {
            voucherCategoryVariableOperateHandler.remove(removeInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除凭证类型变量时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
