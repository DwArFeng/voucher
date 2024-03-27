package com.dwarfeng.voucher.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.voucher.stack.struct.CheckInfo;

/**
 * 检查 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CheckQosService extends Service {

    /**
     * 获取指定凭证类型的检查信息。
     *
     * @param voucherCategoryKey 指定凭证类型的主键。
     * @return 指定凭证类型的检查信息。
     * @throws ServiceException 服务异常。
     */
    CheckInfo getCheckInfo(StringIdKey voucherCategoryKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
