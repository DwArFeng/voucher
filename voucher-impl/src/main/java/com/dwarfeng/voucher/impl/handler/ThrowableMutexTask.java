package com.dwarfeng.voucher.impl.handler;

/**
 * 可抛出异常的互斥任务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ThrowableMutexTask {

    /**
     * 执行任务。
     *
     * @throws Exception 执行过程中发生的任何异常。
     */
    void run() throws Exception;
}
