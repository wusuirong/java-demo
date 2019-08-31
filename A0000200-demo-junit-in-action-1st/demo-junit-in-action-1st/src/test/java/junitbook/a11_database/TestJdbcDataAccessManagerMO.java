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
package junitbook.a11_database;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.beanutils.DynaBean;

import com.mockobjects.sql.MockConnection2;
import com.mockobjects.sql.MockResultSetMetaData;
import com.mockobjects.sql.MockSingleRowResultSet;
import com.mockobjects.sql.MockStatement;

import junit.framework.TestCase;

public class TestJdbcDataAccessManagerMO extends TestCase
{
    private MockSingleRowResultSet resultSet;
    private MockResultSetMetaData resultSetMetaData;    
    private MockStatement statement;
    private MockConnection2 connection;
    private TestableJdbcDataAccessManager2 manager;

    protected void setUp() throws Exception
    {
        resultSetMetaData = new MockResultSetMetaData();

        resultSet = new MockSingleRowResultSet();
        resultSet.setupMetaData(resultSetMetaData);

        statement = new MockStatement();

        connection = new MockConnection2();
        connection.setupStatement(statement);

        manager = new TestableJdbcDataAccessManager2();
        manager.setConnection(connection);
    }

    protected void tearDown()
    {
        connection.verify();
        statement.verify();
        resultSet.verify();
    }
        
    public void testExecuteOk() throws Exception
    {
        String sql = "SELECT * FROM CUSTOMER";
        statement.addExpectedExecuteQuery(sql, resultSet);        

        String[] columnsUppercase = new String[] {"FIRSTNAME", 
            "LASTNAME"}; 
        String[] columnsLowercase = new String[] {"firstname", 
            "lastname"};
        String[] columnClasseNames = new String[] {
            String.class.getName(), String.class.getName()};
        
        resultSetMetaData.setupAddColumnNames(columnsUppercase);
        resultSetMetaData.setupAddColumnClassNames(
            columnClasseNames);
        resultSetMetaData.setupGetColumnCount(2);
        
        resultSet.addExpectedNamedValues(columnsLowercase,
            new Object[] {"John", "Doe"});

        connection.setExpectedCreateStatementCalls(1);
        connection.setExpectedCloseCalls(1);
               
        Collection result = manager.execute(sql);
        
        Iterator beans = result.iterator();

        assertTrue(beans.hasNext());
        DynaBean bean1 = (DynaBean) beans.next();
        assertEquals("John", bean1.get("firstname"));
        assertEquals("Doe", bean1.get("lastname"));

        assertTrue(!beans.hasNext());
    }

    public void testExecuteCloseConnectionOnException() 
        throws Exception
    {
        String sql = "SELECT * FROM CUSTOMER";

        statement.setupThrowExceptionOnExecute(
            new SQLException("sql error"));

        connection.setExpectedCloseCalls(1);
        
        try
        {
            manager.execute(sql);
            fail("Should have thrown a SQLException");
        }
        catch (SQLException expected)
        {
            assertEquals("sql error", expected.getMessage());
        }                   
    }
}
