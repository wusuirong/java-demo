/*
 * =================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All 
 * rights reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above 
 *    copyright notice, this list of conditions and the following 
 *    disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 *
 * 3. The end-user documentation included with the redistribution, 
 *    if any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software 
 *    itself, if and wherever such third-party acknowlegements 
 *    normally appear.
 *
 * 4. The names "The Jakarta Project", "junitbook", "jia", 
 *    "JUnit in Action" and "Apache Software Foundation" must not be 
 *    used to endorse or promote products derived from this software 
 *    without prior written permission. For written permission, 
 *    please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 * =================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For 
 * more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package junitbook.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.cactus.ServletTestCase;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class TestAdminServlet extends ServletTestCase
{
    private Collection createCommandResult() throws Exception
    {
        List results = new ArrayList();
        
        DynaProperty[] props = new DynaProperty[] {
            new DynaProperty("id", String.class),
            new DynaProperty("responsetime", Long.class)
        };
        BasicDynaClass dynaClass = new BasicDynaClass("requesttime",
            null, props);

        DynaBean request1 = dynaClass.newInstance();
        request1.set("id", "12345");
        request1.set("responsetime", new Long(500));
        results.add(request1);

        DynaBean request2 = dynaClass.newInstance();
        request2.set("id", "56789");
        request2.set("responsetime", new Long(430));
        results.add(request2);

        return results;
    }
         
    public void testCallView() throws Exception
    {
        AdminServlet servlet = new AdminServlet();       
        request.setAttribute("results", createCommandResult());
        servlet.callView(request, response);
    }

    public void endCallView(
        com.meterware.httpunit.WebResponse response)
        throws Exception
    {
        assertTrue(response.isHTML());

        assertEquals("tables", 1, response.getTables().length);
        assertEquals("columns", 2, 
            response.getTables()[0].getColumnCount());
        assertEquals("rows", 3, 
            response.getTables()[0].getRowCount());

        assertEquals("id", 
            response.getTables()[0].getCellAsText(0, 0));
        assertEquals("responsetime", 
            response.getTables()[0].getCellAsText(0, 1));

        assertEquals("12345", 
            response.getTables()[0].getCellAsText(1, 0));
        assertEquals("500", 
            response.getTables()[0].getCellAsText(1, 1));
        assertEquals("56789", 
            response.getTables()[0].getCellAsText(2, 0));
        assertEquals("430", 
            response.getTables()[0].getCellAsText(2, 1));
    }
}
