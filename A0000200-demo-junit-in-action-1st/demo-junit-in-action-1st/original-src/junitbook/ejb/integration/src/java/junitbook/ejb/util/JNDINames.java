package junitbook.ejb.util;

public abstract class JNDINames 
{
    public static final String QUEUE_CONNECTION_FACTORY = 
        "ConnectionFactory";
    public static final String QUEUE_ORDER = 
        "queue/petstore/Order";
    public static final String ORDER_LOCALHOME = 
        "ejb/petstore/Order";
    public static final String PETSTORE_HOME = 
        "ejb/petstore/Petstore";
}
