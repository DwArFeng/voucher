package com.dwarfeng.voucher.impl.handler;

/**
 * 可抛出异常并返回值的互斥任务。
 *
 * @param <T> 任务的返回值类型。
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ThrowableReturningMutexTask<T> {

    /**
     * 执行任务。
     *
     * @return 任务的返回值。
     * @throws Exception 执行过程中发生的任何异常。
     */
    T runAndReturn() throws Exception;
}
