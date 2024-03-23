package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.voucher.stack.bean.entity.Voucher;
import com.dwarfeng.voucher.stack.bean.entity.VoucherVariable;
import com.dwarfeng.voucher.stack.bean.key.VoucherVariableKey;
import com.dwarfeng.voucher.stack.service.VoucherMaintainService;
import com.dwarfeng.voucher.stack.service.VoucherVariableMaintainService;
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
public class VoucherVariableMaintainServiceImplTest {

    private static final LongIdKey VOUCHER_KEY = new LongIdKey(12450L);

    @Autowired
    private VoucherMaintainService voucherMaintainService;
    @Autowired
    private VoucherVariableMaintainService voucherVariableMaintainService;

    private Voucher voucher;
    private List<VoucherVariable> voucherVariables;

    @Before
    public void setUp() {
        voucher = new Voucher(VOUCHER_KEY, null, "content", "remark", true);
        voucherVariables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VoucherVariable voucherVariable = new VoucherVariable(
                    new VoucherVariableKey(VOUCHER_KEY.getLongId(), "test.variable_id." + i), "value", new Date()
            );
            voucherVariables.add(voucherVariable);
        }
    }

    @After
    public void tearDown() {
        voucher = null;
        voucherVariables.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            voucherMaintainService.insertOrUpdate(voucher);
            for (VoucherVariable voucherVariable : voucherVariables) {
                voucherVariableMaintainService.insertOrUpdate(voucherVariable);
                VoucherVariable testVoucherVariable = voucherVariableMaintainService.get(voucherVariable.getKey());
                assertEquals(BeanUtils.describe(
                        voucherVariable), BeanUtils.describe(testVoucherVariable
                ));
                voucherVariableMaintainService.update(voucherVariable);
                testVoucherVariable = voucherVariableMaintainService.get(voucherVariable.getKey());
                assertEquals(BeanUtils.describe(voucherVariable), BeanUtils.describe(testVoucherVariable));
            }
        } finally {
            for (VoucherVariable voucherVariable : voucherVariables) {
                voucherVariableMaintainService.deleteIfExists(voucherVariable.getKey());
            }
            voucherMaintainService.deleteIfExists(voucher.getKey());
        }
    }

    @Test
    public void testForVoucherVariableCascade() throws Exception {
        try {
            voucherMaintainService.insertOrUpdate(voucher);
            for (VoucherVariable voucherVariable : voucherVariables) {
                voucherVariableMaintainService.insertOrUpdate(voucherVariable);
            }

            assertEquals(
                    voucherVariables.size(),
                    voucherVariableMaintainService.lookup(
                            VoucherVariableMaintainService.CHILD_FOR_VOUCHER,
                            new Object[]{voucher.getKey()}
                    ).getCount()
            );

            voucherMaintainService.deleteIfExists(voucher.getKey());

            assertEquals(
                    0,
                    voucherVariableMaintainService.lookup(
                            VoucherVariableMaintainService.CHILD_FOR_VOUCHER,
                            new Object[]{voucher.getKey()}
                    ).getCount()
            );

            assertTrue(voucherVariableMaintainService.nonExists(
                    voucherVariables.stream().map(VoucherVariable::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (VoucherVariable voucherVariable : voucherVariables) {
                voucherVariableMaintainService.deleteIfExists(voucherVariable.getKey());
            }
            voucherMaintainService.deleteIfExists(voucher.getKey());
        }
    }
}
