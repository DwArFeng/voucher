import com.dwarfeng.subgrade.stack.bean.key.LongIdKey
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey
import com.dwarfeng.voucher.impl.handler.checker.GroovyCheckerRegistry
import com.dwarfeng.voucher.stack.handler.Checker

/**
 * 示例检查器处理器。
 *
 * <p>
 * 在初始化时，向凭据变量中写入一个随机数。<br>
 * 每次查看成功时，再生成一个随机数，如果生成的随机数比前一个随机数大，则标记为失效。
 *
 * <p>
 * 该示例检查器演示了 GroovyCheckerRegistry.Processor 接口的使用，并且演示了 Checker.Context 接口的使用。<br>
 * 其本身不具有实际意义，开发人员需要根据自身的需求来编写具体的检查器处理器。
 */
@SuppressWarnings("GrPackage")
class ExampleCheckerProcessor implements GroovyCheckerRegistry.Processor {

    private static final String VARIABLE_ID_RANDOM_NUMBER = "random_number"

    @Override
    void afterVoucherInitialize(Checker.Context context, StringIdKey voucherCategoryKey, LongIdKey voucherKey)
            throws Exception {
        int randomInt = new Random().nextInt()
        context.putVoucherVariable(voucherKey, VARIABLE_ID_RANDOM_NUMBER, Integer.toString(randomInt))
    }

    @Override
    void doVoucherValidCheck(Checker.Context context, StringIdKey voucherCategoryKey, LongIdKey voucherKey) {
        // 不做任何操作。
    }

    @Override
    void afterVoucherInspectSucceed(Checker.Context context, StringIdKey voucherCategoryKey, LongIdKey voucherKey)
            throws Exception {
        int randomInt = new Random().nextInt()
        String neoRandomNumberString = context.inspectVoucherVariableOrDefault(
                voucherKey, VARIABLE_ID_RANDOM_NUMBER, "0"
        )
        int neoRandomInt = Integer.parseInt(neoRandomNumberString)
        if (randomInt > neoRandomInt) {
            context.invalidVoucher(voucherKey)
        }
    }

    @Override
    void afterVoucherInspectFailed(Checker.Context context, StringIdKey voucherCategoryKey, LongIdKey voucherKey) {
        // 不做任何操作。
    }
}
