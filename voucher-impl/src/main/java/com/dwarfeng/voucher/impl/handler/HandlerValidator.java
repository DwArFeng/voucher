package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.exception.InvalidVoucherException;
import com.dwarfeng.voucher.stack.exception.VoucherAlreadyExistsException;
import com.dwarfeng.voucher.stack.exception.VoucherCategoryNotExistsException;
import com.dwarfeng.voucher.stack.exception.VoucherNotExistsException;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class HandlerValidator {

    private final VoucherCategoryMaintainService voucherCategoryMaintainService;
    private final VoucherMaintainService voucherMaintainService;

    public HandlerValidator(
            VoucherCategoryMaintainService voucherCategoryMaintainService,
            VoucherMaintainService voucherMaintainService
    ) {
        this.voucherCategoryMaintainService = voucherCategoryMaintainService;
        this.voucherMaintainService = voucherMaintainService;
    }

    public void makeSureVoucherCategoryExists(StringIdKey voucherCategoryKey) throws HandlerException {
        try {
            if (Objects.isNull(voucherCategoryKey) || !voucherCategoryMaintainService.exists(voucherCategoryKey)) {
                throw new VoucherCategoryNotExistsException(voucherCategoryKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureVoucherExists(LongIdKey voucherKey) throws HandlerException {
        try {
            if (Objects.isNull(voucherKey) || !voucherMaintainService.exists(voucherKey)) {
                throw new VoucherNotExistsException(voucherKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureVoucherNotExists(LongIdKey voucherKey) throws HandlerException {
        try {
            if (Objects.nonNull(voucherKey) && voucherMaintainService.exists(voucherKey)) {
                throw new VoucherAlreadyExistsException(voucherKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureVoucherValid(LongIdKey voucherKey) throws HandlerException {
        try {
            if (Objects.isNull(voucherKey)) {
                throw new VoucherNotExistsException(null);
            }
            Voucher voucher = voucherMaintainService.getIfExists(voucherKey);
            if (Objects.isNull(voucher)) {
                throw new VoucherNotExistsException(voucherKey);
            }
            if (!voucher.isValid()) {
                throw new InvalidVoucherException(voucherKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
