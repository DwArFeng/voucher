package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VoucherMaintainServiceImplTest {

    private static final StringIdKey VOUCHER_CATEGORY_KEY = new StringIdKey("test.voucher_category");

    @Autowired
    private VoucherCategoryMaintainService voucherCategoryMaintainService;
    @Autowired
    private VoucherMaintainService voucherMaintainService;

    private VoucherCategory voucherCategory;
    private List<Voucher> vouchers;

    @Before
    public void setUp() {
        voucherCategory = new VoucherCategory(VOUCHER_CATEGORY_KEY, true, "name", "remark");
        vouchers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Voucher voucher = new Voucher(null, null, "content", "remark", true);
            vouchers.add(voucher);
        }
    }

    @After
    public void tearDown() {
        voucherCategory = null;
        vouchers.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            for (Voucher voucher : vouchers) {
                voucher.setCategoryKey(voucherCategory.getKey());
                voucher.setKey(voucherMaintainService.insertOrUpdate(voucher));
                Voucher testVoucher = voucherMaintainService.get(voucher.getKey());
                assertEquals(BeanUtils.describe(
                        voucher), BeanUtils.describe(testVoucher
                ));
                voucherMaintainService.update(voucher);
                testVoucher = voucherMaintainService.get(voucher.getKey());
                assertEquals(BeanUtils.describe(voucher), BeanUtils.describe(testVoucher));
            }
        } finally {
            for (Voucher voucher : vouchers) {
                voucherMaintainService.deleteIfExists(voucher.getKey());
            }
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }

    @Test
    public void testForVoucherCategoryCascade() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            for (Voucher voucher : vouchers) {
                voucher.setCategoryKey(voucherCategory.getKey());
                voucher.setKey(voucherMaintainService.insertOrUpdate(voucher));
            }

            assertEquals(
                    vouchers.size(),
                    voucherMaintainService.lookup(
                            VoucherMaintainService.CHILD_FOR_CATEGORY, new Object[]{voucherCategory.getKey()}
                    ).getCount()
            );

            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());

            assertEquals(
                    0,
                    voucherMaintainService.lookup(
                            VoucherMaintainService.CHILD_FOR_CATEGORY, new Object[]{voucherCategory.getKey()}
                    ).getCount()
            );

            assertTrue(voucherMaintainService.nonExists(
                    vouchers.stream().map(Voucher::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (Voucher voucher : vouchers) {
                voucherMaintainService.deleteIfExists(voucher.getKey());
            }
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }
}
