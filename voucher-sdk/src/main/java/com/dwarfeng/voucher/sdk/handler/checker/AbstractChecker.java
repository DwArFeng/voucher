package com.dwarfeng.voucher.sdk.handler.checker;

import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInitializeInfo;
import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInspectFailedInfo;
import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInspectSucceedInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherValidCheckInfo;
import com.dwarfeng.voucher.stack.exception.CheckerException;
import com.dwarfeng.voucher.stack.exception.CheckerExecutionException;
import com.dwarfeng.voucher.stack.handler.Checker;

/**
 * 检查器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public abstract class AbstractChecker implements Checker {

    protected Context context;

    public AbstractChecker() {
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void afterVoucherInitialize(AfterVoucherInitializeInfo info) throws CheckerException {
        try {
            doAfterVoucherInitialize(info);
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throw new CheckerExecutionException(e);
        }
    }

    /**
     * 凭证初始化后回调方法的具体实现。
     *
     * @param info 凭证初始化后回调信息。
     * @throws Exception 方法执行时抛出的任何异常。
     * @see Checker#afterVoucherInitialize(AfterVoucherInitializeInfo)
     */
    protected abstract void doAfterVoucherInitialize(AfterVoucherInitializeInfo info) throws Exception;

    @Override
    public void voucherValidCheck(VoucherValidCheckInfo info) throws CheckerException {
        try {
            doVoucherValidCheck(info);
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throw new CheckerExecutionException(e);
        }
    }

    /**
     * 检查凭证是否有效的具体实现。
     *
     * @param info 凭证有效性检查信息。
     * @throws Exception 方法执行时抛出的任何异常。
     * @see Checker#voucherValidCheck(VoucherValidCheckInfo)
     */
    protected abstract void doVoucherValidCheck(VoucherValidCheckInfo info) throws Exception;

    @Override
    public void afterVoucherInspectSucceed(AfterVoucherInspectSucceedInfo info) throws CheckerException {
        try {
            doAfterVoucherInspectSucceed(info);
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throw new CheckerExecutionException(e);
        }
    }

    /**
     * 凭证查看成功后回调方法的具体实现。
     *
     * @param info 凭证查看成功后回调信息。
     * @throws Exception 方法执行时抛出的任何异常。
     * @see Checker#afterVoucherInspectSucceed(AfterVoucherInspectSucceedInfo)
     */
    protected abstract void doAfterVoucherInspectSucceed(AfterVoucherInspectSucceedInfo info) throws Exception;

    @Override
    public void afterVoucherInspectFailed(AfterVoucherInspectFailedInfo info) throws CheckerException {
        try {
            doAfterVoucherInspectFailed(info);
        } catch (CheckerException e) {
            throw e;
        } catch (Exception e) {
            throw new CheckerExecutionException(e);
        }
    }

    /**
     * 凭证查看失败后回调方法的具体实现。
     *
     * @param info 凭证查看失败后回调信息。
     * @throws Exception 方法执行时抛出的任何异常。
     * @see Checker#afterVoucherInspectFailed(AfterVoucherInspectFailedInfo)
     */
    protected abstract void doAfterVoucherInspectFailed(AfterVoucherInspectFailedInfo info) throws Exception;

    @Override
    public String toString() {
        return "AbstractChecker{" +
                "context=" + context +
                '}';
    }
}
