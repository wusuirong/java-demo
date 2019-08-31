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
package junitbook.a8_container;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.MockControl;

import junit.framework.TestCase;

public class TestSampleServlet extends TestCase
{
    private SampleServlet servlet;
    
    private MockControl controlHttpServlet;
    private HttpServletRequest mockHttpServletRequest;

    private MockControl controlHttpSession;
    private HttpSession mockHttpSession;
    
    protected void setUp()
    {
        servlet = new SampleServlet();
        
        controlHttpServlet = MockControl.createControl(
            HttpServletRequest.class);
        mockHttpServletRequest = 
            (HttpServletRequest) controlHttpServlet.getMock();
        
        controlHttpSession = MockControl.createControl(
            HttpSession.class);
        mockHttpSession = 
            (HttpSession) controlHttpSession.getMock();
    }

    protected void tearDown()
    {
        controlHttpServlet.verify();
        controlHttpSession.verify();
    }
    
    public void testIsAuthenticatedAuthenticated()
    {
        mockHttpServletRequest.getSession(false);
        controlHttpServlet.setReturnValue(mockHttpSession);

        mockHttpSession.getAttribute("authenticated");
        controlHttpSession.setReturnValue("true");
        
        controlHttpServlet.replay();       
        controlHttpSession.replay();
                
        assertTrue(servlet.isAuthenticated(mockHttpServletRequest));
    }

    public void testIsAuthenticatedNotAuthenticated()
    {
        mockHttpServletRequest.getSession(false);
        controlHttpServlet.setReturnValue(mockHttpSession);

        mockHttpSession.getAttribute("authenticated");
        controlHttpSession.setReturnValue(null);
        
        controlHttpServlet.replay();       
        controlHttpSession.replay();
                
        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }

    public void testIsAuthenticatedNoSession()
    {
        mockHttpServletRequest.getSession(false);
        controlHttpServlet.setReturnValue(null);
       
        controlHttpServlet.replay();       
        controlHttpSession.replay();
                
        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }

}
