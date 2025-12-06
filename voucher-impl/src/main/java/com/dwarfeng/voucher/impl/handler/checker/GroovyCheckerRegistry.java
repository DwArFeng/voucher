package com.dwarfeng.voucher.impl.handler.checker;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.voucher.sdk.handler.checker.AbstractChecker;
import com.dwarfeng.voucher.sdk.handler.checker.AbstractCheckerRegistry;
import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInitializeInfo;
import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInspectFailedInfo;
import com.dwarfeng.voucher.stack.bean.dto.AfterVoucherInspectSucceedInfo;
import com.dwarfeng.voucher.stack.bean.dto.VoucherValidCheckInfo;
import com.dwarfeng.voucher.stack.exception.CheckerException;
import com.dwarfeng.voucher.stack.exception.CheckerMakeException;
import com.dwarfeng.voucher.stack.handler.Checker;
import com.dwarfeng.voucher.stack.handler.Checker.Context;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 检查器注册。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class GroovyCheckerRegistry extends AbstractCheckerRegistry {

    public static final String CHECKER_TYPE = "groovy_checker";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyCheckerRegistry.class);

    private final ApplicationContext ctx;

    public GroovyCheckerRegistry(ApplicationContext ctx) {
        super(CHECKER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy检查器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的Groovy脚本对数据进行判断。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleCheckerProcessor.groovy");
            String example;
            try (
                    InputStream sin = resource.getInputStream();
                    StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)
            ) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleCheckerProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Checker makeChecker(String type, String param) throws CheckerException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 构建过滤器对象。
            return ctx.getBean(GroovyChecker.class, processor);
        } catch (Exception e) {
            throw new CheckerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyCheckerRegistry{" +
                "checkerType='" + checkerType + '\'' +
                ", ctx=" + ctx +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyChecker extends AbstractChecker {

        private final Processor processor;

        public GroovyChecker(Processor processor) {
            this.processor = processor;
        }

        @Override
        public void doAfterVoucherInitialize(AfterVoucherInitializeInfo info) throws Exception {
            processor.afterVoucherInitialize(context, info);
        }

        @Override
        public void doVoucherValidCheck(VoucherValidCheckInfo info) throws Exception {
            processor.doVoucherValidCheck(context, info);
        }

        @Override
        public void doAfterVoucherInspectSucceed(AfterVoucherInspectSucceedInfo info) throws Exception {
            processor.afterVoucherInspectSucceed(context, info);
        }

        @Override
        public void doAfterVoucherInspectFailed(AfterVoucherInspectFailedInfo info) throws Exception {
            processor.afterVoucherInspectFailed(context, info);
        }

        @Override
        public String toString() {
            return "GroovyChecker{" +
                    "processor=" + processor +
                    ", context=" + context +
                    '}';
        }
    }

    /**
     * Groovy处理器。
     *
     * @author DwArFeng
     * @since 1.0.0
     */
    public interface Processor {

        /**
         * 凭证初始化后回调方法。
         *
         * <p>
         * 该方法在凭证初始化后被调用，检查器可以在该方法中进行一些初始化后的操作，如设置一些凭证变量等。
         *
         * @param context 检查器上下文。
         * @param info    凭证初始化后回调信息。
         * @throws Exception 方法执行时抛出的任何异常。
         */
        void afterVoucherInitialize(Context context, AfterVoucherInitializeInfo info) throws Exception;

        /**
         * 检查凭证是否有效。
         *
         * <p>
         * 该方法在凭证被检查时被调用，检查器需要在该方法中检查凭证是否有效。
         *
         * <p>
         * 如果凭证无效，则需要调用 <code>context.invalidVoucher</code> 方法作废凭证。<br>
         * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，
         * 以便在此时使用。
         *
         * @param context 检查器上下文。
         * @param info    凭证有效性检查信息。
         * @throws Exception 方法执行时抛出的任何异常。
         */
        void doVoucherValidCheck(Context context, VoucherValidCheckInfo info) throws Exception;

        /**
         * 凭证查看成功后回调方法。
         *
         * <p>
         * 该方法在凭证查看成功后被调用，检查器可以在该方法中进行一些查看成功后的操作，如设置一些凭证变量等。<br>
         * 如果凭证在查看成功后失效，则可以调用 <code>context.invalidVoucher</code> 方法作废凭证，也可以不做任何操作，
         * 等待下一次 <code>doVoucherValidCheck</code> 方法被调用时再作废凭证。
         * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，以便在此时使用。
         *
         * @param context 检查器上下文。
         * @param info    凭证查看成功后回调信息。
         * @throws Exception 方法执行时抛出的任何异常。
         */
        void afterVoucherInspectSucceed(Context context, AfterVoucherInspectSucceedInfo info) throws Exception;

        /**
         * 凭证查看失败后回调方法。
         *
         * <p>
         * 该方法在凭证查看失败后被调用，检查器可以在该方法中进行一些查看失败后的操作，如设置一些凭证变量等。<br>
         * 如果凭证在查看失败后失效，则可以调用 <code>context.invalidVoucher</code> 方法作废凭证，也可以不做任何操作，
         * 等待下一次 <code>doVoucherValidCheck</code> 方法被调用时再作废凭证。
         * <code>context</code> 对象在检查器初始化后通过 <code>init</code> 方法传入，检查器应该妥善持有该对象，以便在此时使用。
         *
         * @param context 检查器上下文。
         * @param info    凭证查看失败后回调信息。
         * @throws Exception 方法执行时抛出的任何异常。
         */
        void afterVoucherInspectFailed(Context context, AfterVoucherInspectFailedInfo info) throws Exception;
    }
}
