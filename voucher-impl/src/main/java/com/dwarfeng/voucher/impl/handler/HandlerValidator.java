package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.exception.*;
import com.dwarfeng.voucher.stack.service.CheckerInfoMaintainService;
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
    private final CheckerInfoMaintainService checkerInfoMaintainService;

    public HandlerValidator(
            VoucherCategoryMaintainService voucherCategoryMaintainService,
            VoucherMaintainService voucherMaintainService,
            CheckerInfoMaintainService checkerInfoMaintainService
    ) {
        this.voucherCategoryMaintainService = voucherCategoryMaintainService;
        this.voucherMaintainService = voucherMaintainService;
        this.checkerInfoMaintainService = checkerInfoMaintainService;
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

    public void makeSureVoucherCategoryEnabled(StringIdKey voucherCategoryKey) throws HandlerException {
        try {
            if (Objects.isNull(voucherCategoryKey)) {
                throw new VoucherCategoryNotExistsException(null);
            }
            VoucherCategory voucherCategory = voucherCategoryMaintainService.getIfExists(voucherCategoryKey);
            if (Objects.isNull(voucherCategory)) {
                throw new VoucherCategoryNotExistsException(voucherCategoryKey);
            }
            if (!voucherCategory.isEnabled()) {
                throw new VoucherCategoryDisabledException(voucherCategoryKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureCheckerInfoExists(StringIdKey checkerInfoKey) throws HandlerException {
        try {
            if (Objects.isNull(checkerInfoKey) || !checkerInfoMaintainService.exists(checkerInfoKey)) {
                throw new CheckerInfoNotExistsException(checkerInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
