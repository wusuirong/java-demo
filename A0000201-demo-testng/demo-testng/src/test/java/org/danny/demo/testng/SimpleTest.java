/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package org.danny.demo.testng;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author wusui
 * @version $Id: SimpleTest.java, 2019-07-07 12:43 PM wusui Exp $
 */
public class SimpleTest {

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test(groups = { "fast" })
    public void aFastTest() {
        System.out.println("Fast test");
    }

    @Test(groups = { "slow" })
    public void aSlowTest() {
        System.out.println("Slow test");
    }
}
