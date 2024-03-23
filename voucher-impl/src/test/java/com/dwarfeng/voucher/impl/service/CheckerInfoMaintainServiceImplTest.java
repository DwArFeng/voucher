package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.entity.CheckerInfo;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.service.CheckerInfoMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class CheckerInfoMaintainServiceImplTest {

    private static final StringIdKey VOUCHER_CATEGORY_KEY = new StringIdKey("test.voucher_category");

    @Autowired
    private VoucherCategoryMaintainService voucherCategoryMaintainService;
    @Autowired
    private CheckerInfoMaintainService checkerInfoMaintainService;

    private VoucherCategory voucherCategory;
    private CheckerInfo checkerInfo;

    @Before
    public void setUp() {
        voucherCategory = new VoucherCategory(VOUCHER_CATEGORY_KEY, true, "name", "remark");
        checkerInfo = new CheckerInfo(VOUCHER_CATEGORY_KEY, true, "type", "param", "remark");
    }

    @After
    public void tearDown() {
        voucherCategory = null;
        checkerInfo = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            checkerInfoMaintainService.insertOrUpdate(checkerInfo);

            CheckerInfo testCheckerInfo = checkerInfoMaintainService.get(checkerInfo.getKey());
            assertEquals(BeanUtils.describe(
                    checkerInfo), BeanUtils.describe(testCheckerInfo
            ));
            checkerInfoMaintainService.update(checkerInfo);
            testCheckerInfo = checkerInfoMaintainService.get(checkerInfo.getKey());
            assertEquals(BeanUtils.describe(checkerInfo), BeanUtils.describe(testCheckerInfo));
        } finally {
            checkerInfoMaintainService.deleteIfExists(checkerInfo.getKey());
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }

    @Test
    public void testForVoucherCategoryCascade() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            checkerInfoMaintainService.insertOrUpdate(checkerInfo);

            assertTrue(checkerInfoMaintainService.exists(checkerInfo.getKey()));

            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());

            assertFalse(checkerInfoMaintainService.exists(checkerInfo.getKey()));
        } finally {
            checkerInfoMaintainService.deleteIfExists(checkerInfo.getKey());
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }
}
