package com.dwarfeng.voucher.impl.handler;

/**
 * 更新器支持器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckerSupporter {

    /**
     * 提供类型。
     *
     * @return 类型。
     */
    String provideType();

    /**
     * 提供标签。
     *
     * @return 标签。
     */
    String provideLabel();

    /**
     * 提供描述。
     *
     * @return 描述。
     */
    String provideDescription();

    /**
     * 提供示例内容。
     *
     * @return 示例内容。
     */
    String provideExampleParam();
}
