/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package org.danny.demo.jmockit;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

/**
 * @author wusui
 * @version $Id: MyBusinessServiceTest.java, 2019-07-07 2:05 PM wusui Exp $
 */
public final class MyBusinessServiceTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Tested
    final EntityX data = new EntityX(1, "abc", "someone@somewhere.com");
    @Tested(fullyInitialized = true)
    MyBusinessService businessService;
    @Mocked
    SimpleEmail anyEmail;

    @Test
    public void doBusinessOperationXyz() throws Exception {
        EntityX existingItem = new EntityX(1, "AX5", "abc@xpta.net");
        persist(existingItem);

        businessService.doBusinessOperationXyz();

        assertNotEquals(0, data.getId()); // implies "data" was persisted
        new Verifications() {{
            anyEmail.send();
            times = 1;
        }};
    }

    private void persist(EntityX existingItem) {
    }

    @Test
    public void doBusinessOperationXyzWithInvalidEmailAddress() throws Exception {
        final String email = "invalid address";
        data.setCustomerEmail(email);
        new Expectations() {{
            anyEmail.addTo(email);
            result = new EmailException();
        }};
        thrown.expect(EmailException.class);

        businessService.doBusinessOperationXyz();
    }
}
