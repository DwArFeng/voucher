package com.dwarfeng.voucher.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.voucher.stack.bean.entity.CheckerSupport;
import com.dwarfeng.voucher.stack.service.CheckerSupportMaintainService;
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
public class CheckerSupportMaintainServiceImplTest {

    @Autowired
    private CheckerSupportMaintainService checkerSupportMaintainService;

    private List<CheckerSupport> checkerSupports;

    @Before
    public void setUp() {
        checkerSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CheckerSupport checkerSupport = new CheckerSupport(
                    new StringIdKey("test.checker_support." + i), "label", "description", "exampleParam"
            );
            checkerSupports.add(checkerSupport);
        }
    }

    @After
    public void tearDown() {
        checkerSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (CheckerSupport checkerSupport : checkerSupports) {
                checkerSupportMaintainService.insertOrUpdate(checkerSupport);
                CheckerSupport testCheckerSupport = checkerSupportMaintainService.get(checkerSupport.getKey());
                assertEquals(BeanUtils.describe(
                        checkerSupport), BeanUtils.describe(testCheckerSupport
                ));
                checkerSupportMaintainService.update(checkerSupport);
                testCheckerSupport = checkerSupportMaintainService.get(checkerSupport.getKey());
                assertEquals(BeanUtils.describe(checkerSupport), BeanUtils.describe(testCheckerSupport));
            }
        } finally {
            for (CheckerSupport checkerSupport : checkerSupports) {
                checkerSupportMaintainService.deleteIfExists(checkerSupport.getKey());
            }
        }
    }
}
