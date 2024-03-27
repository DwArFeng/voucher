package com.dwarfeng.voucher.stack.struct;

import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.handler.Checker;

/**
 * 检查信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class CheckInfo {

    private final VoucherCategory voucherCategory;
    private final CheckerInfo checkerInfo;
    private final Checker checker;

    public CheckInfo(VoucherCategory voucherCategory, CheckerInfo checkerInfo, Checker checker) {
        this.voucherCategory = voucherCategory;
        this.checkerInfo = checkerInfo;
        this.checker = checker;
    }

    public VoucherCategory getVoucherCategory() {
        return voucherCategory;
    }

    public CheckerInfo getCheckerInfo() {
        return checkerInfo;
    }

    public Checker getChecker() {
        return checker;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "voucherCategory=" + voucherCategory +
                ", checkerInfo=" + checkerInfo +
                ", checker=" + checker +
                '}';
    }
}
