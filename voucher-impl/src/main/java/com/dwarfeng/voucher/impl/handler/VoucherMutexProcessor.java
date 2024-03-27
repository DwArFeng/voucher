package com.dwarfeng.voucher.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 凭证分布式锁处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class VoucherMutexProcessor {

    private final CuratorFramework curatorFramework;

    @Value("${curator.mutex_prefix.voucher_lock}")
    private String voucherMutexPrefix;

    public VoucherMutexProcessor(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public void executeInMutex(LongIdKey voucherKey, MutexTask task) throws Exception {
        // 构造凭证的分布式锁。
        InterProcessMutex interProcessMutex = new InterProcessMutex(
                curatorFramework, voucherMutexPrefix + voucherKey.getLongId()
        );
        // 在锁内执行任务。
        try {
            // 尝试获取锁。
            interProcessMutex.acquire();
            // 执行任务。
            task.run();
        } finally {
            // 释放锁。
            if (interProcessMutex.isAcquiredInThisProcess()) {
                interProcessMutex.release();
            }
        }
    }

    public void executeInMutexThrowable(LongIdKey voucherKey, ThrowableMutexTask task) throws Exception {
        // 构造凭证的分布式锁。
        InterProcessMutex interProcessMutex = new InterProcessMutex(
                curatorFramework, voucherMutexPrefix + voucherKey.getLongId()
        );
        // 在锁内执行任务。
        try {
            // 尝试获取锁。
            interProcessMutex.acquire();
            // 执行任务。
            task.run();
        } finally {
            // 释放锁。
            if (interProcessMutex.isAcquiredInThisProcess()) {
                interProcessMutex.release();
            }
        }
    }

    public <T> T executeReturnInMutexThrowable(LongIdKey voucherKey, ThrowableReturningMutexTask<T> task)
            throws Exception {
        // 构造凭证的分布式锁。
        InterProcessMutex interProcessMutex = new InterProcessMutex(
                curatorFramework, voucherMutexPrefix + voucherKey.getLongId()
        );
        // 在锁内执行任务。
        try {
            // 尝试获取锁。
            interProcessMutex.acquire();
            // 执行任务。
            return task.runAndReturn();
        } finally {
            // 释放锁。
            if (interProcessMutex.isAcquiredInThisProcess()) {
                interProcessMutex.release();
            }
        }
    }
}
