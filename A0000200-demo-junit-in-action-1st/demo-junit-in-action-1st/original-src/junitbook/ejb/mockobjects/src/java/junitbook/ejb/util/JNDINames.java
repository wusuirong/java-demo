package junitbook.ejb.util;

public abstract class JNDINames 
{
    public static final String QUEUE_CONNECTION_FACTORY = 
        "java:comp/env/jms/petstore/QueueConnectionFactory";
    public static final String QUEUE_ORDER = 
        "java:comp/env/jms/queue/petstore/order";
    public static final String ORDER_LOCALHOME = 
        "ejb/petstore/Order";
}
