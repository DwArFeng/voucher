package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategory;
import com.dwarfeng.voucher.stack.bean.entity.VoucherCategoryVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherCategoryVariableKey;
import com.dwarfeng.voucher.stack.service.VoucherCategoryMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherCategoryVariableMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VoucherCategoryVariableMaintainServiceImplTest {

    private static final StringIdKey VOUCHER_CATEGORY_KEY = new StringIdKey("test.voucher_category");

    @Autowired
    private VoucherCategoryMaintainService voucherCategoryMaintainService;
    @Autowired
    private VoucherCategoryVariableMaintainService voucherCategoryVariableMaintainService;

    private VoucherCategory voucherCategory;
    private List<VoucherCategoryVariable> voucherCategoryVariables;

    @Before
    public void setUp() {
        voucherCategory = new VoucherCategory(VOUCHER_CATEGORY_KEY, true, "name", "remark");
        voucherCategoryVariables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VoucherCategoryVariable voucherCategoryVariable = new VoucherCategoryVariable(
                    new VoucherCategoryVariableKey(VOUCHER_CATEGORY_KEY.getStringId(), "test.variable_id." + i),
                    "value", new Date()
            );
            voucherCategoryVariables.add(voucherCategoryVariable);
        }
    }

    @After
    public void tearDown() {
        voucherCategory = null;
        voucherCategoryVariables.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            for (VoucherCategoryVariable voucherCategoryVariable : voucherCategoryVariables) {
                voucherCategoryVariableMaintainService.insertOrUpdate(voucherCategoryVariable);
                VoucherCategoryVariable testVoucherCategoryVariable = voucherCategoryVariableMaintainService.get(voucherCategoryVariable.getKey());
                assertEquals(BeanUtils.describe(
                        voucherCategoryVariable), BeanUtils.describe(testVoucherCategoryVariable
                ));
                voucherCategoryVariableMaintainService.update(voucherCategoryVariable);
                testVoucherCategoryVariable = voucherCategoryVariableMaintainService.get(voucherCategoryVariable.getKey());
                assertEquals(BeanUtils.describe(voucherCategoryVariable), BeanUtils.describe(testVoucherCategoryVariable));
            }
        } finally {
            for (VoucherCategoryVariable voucherCategoryVariable : voucherCategoryVariables) {
                voucherCategoryVariableMaintainService.deleteIfExists(voucherCategoryVariable.getKey());
            }
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }

    @Test
    public void testForVoucherCategoryVariableCategoryCascade() throws Exception {
        try {
            voucherCategoryMaintainService.insertOrUpdate(voucherCategory);
            for (VoucherCategoryVariable voucherCategoryVariable : voucherCategoryVariables) {
                voucherCategoryVariableMaintainService.insertOrUpdate(voucherCategoryVariable);
            }

            assertEquals(
                    voucherCategoryVariables.size(),
                    voucherCategoryVariableMaintainService.lookup(
                            VoucherCategoryVariableMaintainService.CHILD_FOR_VOUCHER_CATEGORY,
                            new Object[]{voucherCategory.getKey()}
                    ).getCount()
            );

            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());

            assertEquals(
                    0,
                    voucherCategoryVariableMaintainService.lookup(
                            VoucherCategoryVariableMaintainService.CHILD_FOR_VOUCHER_CATEGORY,
                            new Object[]{voucherCategory.getKey()}
                    ).getCount()
            );

            assertTrue(voucherCategoryVariableMaintainService.nonExists(
                    voucherCategoryVariables.stream().map(VoucherCategoryVariable::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (VoucherCategoryVariable voucherCategoryVariable : voucherCategoryVariables) {
                voucherCategoryVariableMaintainService.deleteIfExists(voucherCategoryVariable.getKey());
            }
            voucherCategoryMaintainService.deleteIfExists(voucherCategory.getKey());
        }
    }
}
