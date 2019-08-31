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
package junitbook.a6_coarse.try1;

import java.io.IOException;
import java.io.OutputStream;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpFields;
import org.mortbay.http.HttpRequest;
import org.mortbay.http.HttpResponse;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SocketListener;
import org.mortbay.http.handler.AbstractHttpHandler;
import org.mortbay.http.handler.NotFoundHandler;
import org.mortbay.util.ByteArrayISO8859Writer;

public class TestWebClientSetup2 extends TestSetup
{
    protected static HttpServer server;

    public TestWebClientSetup2(Test suite)
    {
        super(suite);
    }

    protected void setUp() throws Exception
    {
        server = new HttpServer();

        SocketListener listener = new SocketListener();

        listener.setPort(8080);
        server.addListener(listener);

        HttpContext context1 = new HttpContext();

        context1.setContextPath("/testGetContentOk");
        context1.addHandler(new TestGetContentOkHandler());
        server.addContext(context1);

        HttpContext context2 = new HttpContext();

        context2.setContextPath("/testGetContentNotFound");
        context2.addHandler(new NotFoundHandler());
        server.addContext(context2);

        server.start();
    }

    protected void tearDown() throws Exception
    {
        server.stop();
    }

    private class TestGetContentOkHandler
        extends AbstractHttpHandler
    {
        public void handle(String pathInContext, String pathParams, 
            HttpRequest request, HttpResponse response)
            throws IOException
        {
            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = 
                new ByteArrayISO8859Writer();

            writer.write("It works");
            writer.flush();
            response.setIntField(
                HttpFields.__ContentLength, writer.size());
            writer.writeTo(out);
            out.flush();
            request.setHandled(true);
        }
    }
}