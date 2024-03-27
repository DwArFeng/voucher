package com.dwarfeng.voucher.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.exception.CheckerException;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * 检查器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Checker {

    /**
     * 初始化检查器。
     *
     * <p>
     * 该方法在检查器被使用前被调用，检查器需要将该方法传入的上下文对象妥善持有，以便在后续的方法中使用。
     *
     * @param context 检查器上下文。
     */
    void init(Context context);

    /**
     * 凭证初始化后回调方法。
     *
     * <p>
     * 该方法在凭证初始化后被调用，检查器可以在该方法中进行一些初始化后的操作，如设置一些凭证变量等。
     *
     * @param voucherCategoryKey 凭证类型的主键。
     * @param voucherKey         凭证的主键。
     * @throws CheckerException 检查器异常。
     */
    void afterVoucherInitialize(StringIdKey voucherCategoryKey, LongIdKey voucherKey) throws CheckerException;

    /**
     * 检查凭证是否有效。
     *
     * <p>
     * 该方法在凭证被检查时被调用，检查器需要在该方法中检查凭证是否有效。
     *
     * <p>
     * 如果凭证无效，则需要调用 <code>context.invalidVoucher</code> 方法作废凭证。<br>
     * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，以便在此时使用。
     *
     * @param voucherCategoryKey 凭证类型的主键。
     * @param voucherKey         凭证的主键。
     * @throws CheckerException 检查器异常。
     */
    void doVoucherValidCheck(StringIdKey voucherCategoryKey, LongIdKey voucherKey) throws CheckerException;

    /**
     * 凭证查看成功后回调方法。
     *
     * <p>
     * 该方法在凭证查看成功后被调用，检查器可以在该方法中进行一些查看成功后的操作，如设置一些凭证变量等。<br>
     * 如果凭证在查看成功后失效，则可以调用 <code>context.invalidVoucher</code> 方法作废凭证，也可以不做任何操作，
     * 等待下一次 <code>doVoucherValidCheck</code> 方法被调用时再作废凭证。
     * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，以便在此时使用。
     *
     * @param voucherCategoryKey 凭证类型的主键。
     * @param voucherKey         凭证的主键。
     * @throws CheckerException 检查器异常。
     */
    void afterVoucherInspectSucceed(StringIdKey voucherCategoryKey, LongIdKey voucherKey) throws CheckerException;

    /**
     * 凭证查看失败后回调方法。
     *
     * <p>
     * 该方法在凭证查看失败后被调用，检查器可以在该方法中进行一些查看失败后的操作，如设置一些凭证变量等。<br>
     * 如果凭证在查看失败后失效，则可以调用 <code>context.invalidVoucher</code> 方法作废凭证，也可以不做任何操作，
     * 等待下一次 <code>doVoucherValidCheck</code> 方法被调用时再作废凭证。
     * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，以便在此时使用。
     *
     * @param voucherCategoryKey 凭证类型的主键。
     * @param voucherKey         凭证的主键。
     * @throws CheckerException 检查器异常。
     */
    void afterVoucherInspectFailed(StringIdKey voucherCategoryKey, LongIdKey voucherKey) throws CheckerException;

    /**
     * 检查器上下文。
     *
     * <p>
     * 该接口定义了检查器的上下文对象，检查器可以通过该对象获取一些信息，如凭证类型变量、凭证变量等。
     *
     * <p>
     * 需要注意的是，该上下文只能在检查器的规定方法中使用，不能在其他地方使用:
     * <ul>
     *     <li>在 {@link #afterVoucherInitialize(StringIdKey, LongIdKey)} 方法中使用。</li>
     *     <li>在 {@link #doVoucherValidCheck(StringIdKey, LongIdKey)} 方法中使用。</li>
     *     <li>在 {@link #afterVoucherInspectSucceed(StringIdKey, LongIdKey)} 方法中使用。</li>
     *     <li>在 {@link #afterVoucherInspectFailed(StringIdKey, LongIdKey)} 方法中使用。</li>
     * </ul>
     * 并且，该上下文中方法的凭证类型的主键以及凭证的主键等参数只能是从以上方法中传入的参数。
     * 即检查器上下文不能查询以上方法传入的参数之外的凭证类型或凭证。<br>
     * 如果检查器上下文操作了以上方法传入的参数之外的凭证类型或凭证，有可能导致被操作的凭证数据不一致。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    interface Context {

        /**
         * 查询凭证类型变量。
         *
         * <p>
         * 如果凭证类型变量不存在，则返回 null。
         *
         * <p>
         * 需要注意的是，凭证类型变量是用户在创建凭证类型时手动设置的，检查器只能查看，不能修改。
         *
         * @param voucherCategoryKey 凭证类型的主键。
         * @param variableId         变量的 ID。
         * @return 对应的凭证类型变量。
         * @throws CheckerException 检查器异常。
         */
        @Nullable
        String inspectVoucherCategoryVariable(StringIdKey voucherCategoryKey, String variableId)
                throws CheckerException;

        /**
         * 查询凭证类型变量，如果不存在则返回默认值。
         *
         * <p>
         * 如果凭证类型变量不存在，则返回 null。
         *
         * <p>
         * 需要注意的是，凭证类型变量是用户在创建凭证类型时手动设置的，检查器只能查看，不能修改。
         *
         * @param voucherCategoryKey 凭证类型的主键。
         * @param variableId         变量的 ID。
         * @param defaultValue       默认值。
         * @return 对应的凭证类型变量。
         * @throws CheckerException 检查器异常。
         */
        @Nullable
        String inspectVoucherCategoryVariableOrDefault(
                StringIdKey voucherCategoryKey, String variableId, String defaultValue
        ) throws CheckerException;

        /**
         * 查询凭证变量。
         *
         * <p>
         * 如果凭证变量不存在，则返回 null。
         *
         * <p>
         * 建议在检查器 <code>afterVoucherInitialize</code> 方法中初始化凭证变量，这样会简化后续的判断逻辑。
         *
         * @param voucherKey 凭证的主键。
         * @param variableId 变量的 ID。
         * @return 对应的凭证变量。
         * @throws CheckerException 检查器异常。
         */
        @Nullable
        String inspectVoucherVariable(LongIdKey voucherKey, String variableId) throws CheckerException;

        /**
         * 查询凭证变量，如果不存在则返回默认值。
         *
         * <p>
         * 如果凭证变量不存在，则返回 null。
         *
         * <p>
         * 建议在检查器 <code>afterVoucherInitialize</code> 方法中初始化凭证变量，这样会简化后续的判断逻辑。
         *
         * @param voucherKey   凭证的主键。
         * @param variableId   变量的 ID。
         * @param defaultValue 默认值。
         * @return 对应的凭证变量。
         * @throws CheckerException 检查器异常。
         */
        @Nullable
        String inspectVoucherVariableOrDefault(LongIdKey voucherKey, String variableId, String defaultValue)
                throws CheckerException;

        /**
         * 设置凭证变量。
         *
         * <p>
         * 如果凭证变量不存在，则创建一个新的凭证变量；如果凭证变量存在，则更新对应的凭证变量。
         *
         * @param voucherKey 凭证的主键。
         * @param variableId 变量的 ID。
         * @param value      变量的值。
         * @throws CheckerException 检查器异常。
         */
        void putVoucherVariable(LongIdKey voucherKey, String variableId, String value) throws CheckerException;

        /**
         * 批量设置凭证变量。
         *
         * <p>
         * 对于变量 ID - 变量值映射中的每一个变量 ID，
         * 如果凭证变量不存在，则创建一个新的凭证变量；如果凭证变量存在，则更新对应的凭证变量。
         *
         * @param voucherKey 凭证的主键。
         * @param valueMap   变量 ID 和变量值对应的映射。
         * @throws CheckerException 检查器异常。
         */
        void batchPutVoucherVariable(LongIdKey voucherKey, Map<String, String> valueMap) throws CheckerException;

        /**
         * 移除凭证变量。
         *
         * <p>
         * 如果凭证变量不存在，则什么都不做。
         *
         * @param voucherKey 凭证的主键。
         * @param variableId 变量的 ID。
         * @throws CheckerException 检查器异常。
         */
        void removeVoucherVariable(LongIdKey voucherKey, String variableId) throws CheckerException;

        /**
         * 作废凭证。
         *
         * @param voucherKey 凭证的主键。
         * @throws CheckerException 检查器异常。
         */
        void invalidVoucher(LongIdKey voucherKey) throws CheckerException;
    }
}
