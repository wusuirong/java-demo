package junitbook.ejb.service;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.EJBObject;

public interface Petstore extends EJBObject
{
    int createOrder(Date orderDate, String orderItem) 
        throws RemoteException;
}
