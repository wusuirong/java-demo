package junitbook.a11_database;

import java.util.ArrayList;

import com.mockobjects.dynamic.Mock;
import com.mockobjects.dynamic.C;

import junit.framework.TestCase;

public class TestAdminServletDynaMock extends TestCase
{
    public void testSomething() throws Exception
    {
        Mock mockManager = new Mock(DataAccessManager.class);
        DataAccessManager manager = 
            (DataAccessManager) mockManager.proxy();

        mockManager.expectAndReturn("execute", C.ANY_ARGS, 
            new ArrayList());

        AdminServlet servlet = new AdminServlet();
        servlet.setDataAccessManager(manager);
            
        // Call the method to test here. For example:
        // manager.doGet(request, response)
        
        // [...]
    }
}
