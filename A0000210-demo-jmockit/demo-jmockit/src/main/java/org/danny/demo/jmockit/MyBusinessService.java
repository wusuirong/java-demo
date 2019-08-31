/*
 * MicroFocus.com Inc.
 * Copyright(c) 2019 All Rights Reserved.
 */
package org.danny.demo.jmockit;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;

/**
 * @author wusui
 * @version $Id: MyBusinessService.java, 2019-07-07 12:54 PM wusui Exp $
 */
public final class MyBusinessService {
    private final EntityX data;

    public MyBusinessService(EntityX data) {
        this.data = data;
    }

    public void doBusinessOperationXyz() throws EmailException {
        List<EntityX> items =
                find("select item from EntityX item where item.someProperty = ?1", data.getSomeProperty());

        // Compute or obtain from another service a total value for the new persistent entity:
//        BigDecimal total = ...
//        data.setTotal(total);

        persist(data);

        sendNotificationEmail(items);
    }

    private List<EntityX> find(String s, String someProperty) {
        return null;
    }

    private void persist(EntityX data) {
    }

    private void sendNotificationEmail(List<EntityX> items) throws EmailException {
        Email email = new SimpleEmail();
        email.setSubject("Notification about processing of ...");
        email.addTo(data.getCustomerEmail());

        // Other e-mail parameters, such as the host name of the mail server, have defaults defined
        // through external configuration.

        String message = buildNotificationMessage(items);
        email.setMsg(message);

        email.send();
    }

    private String buildNotificationMessage(List<EntityX> items) {
        return "";
    }
}
