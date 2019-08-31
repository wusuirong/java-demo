<%@ page contentType="text/html;charset=UTF-8" language="java" 
  import="java.util.*,org.apache.commons.beanutils.*" %>

<%
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

        request.setAttribute("results", results);
%>

<jsp:forward page="/results.jsp"/>
