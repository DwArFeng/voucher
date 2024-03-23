package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VoucherCategoryMaintainServiceImplTest {

    @Autowired
    private VoucherCategoryMaintainService voucherCategoryMaintainService;

    private List<VoucherCategory> voucherCategories;

    @Before
    public void setUp() {
        voucherCategories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VoucherCategory voucherCategory = new VoucherCategory(
                    new StringIdKey("test.voucher_category." + i), true, "name", "remark"
            );
            voucherCategories.add(voucherCategory);
        }
    }

    @After
    public void tearDown() {
        voucherCategories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (VoucherCategory voucherCategory : voucherCategories) {
                voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
                VoucherCategory testVoucherCategory = voucherCategoryMaintainService.get(voucherCategory.getKey());
                assertEquals(BeanUtils.describe(
                        voucherCategory), BeanUtils.describe(testVoucherCategory
                ));
                voucherCategoryMaintainService.update(voucherCategory);
                testVoucherCategory = voucherCategoryMaintainService.get(voucherCategory.getKey());
                assertEquals(BeanUtils.describe(voucherCategory), BeanUtils.describe(testVoucherCategory));
            }
        } finally {
            for (VoucherCategory voucherCategory : voucherCategories) {
                voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
            }
        }
    }
}
