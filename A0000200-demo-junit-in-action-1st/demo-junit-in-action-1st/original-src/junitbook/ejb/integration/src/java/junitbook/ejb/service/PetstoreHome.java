package junitbook.ejb.service;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface PetstoreHome extends EJBHome
{
    Petstore create() throws CreateException, RemoteException;
}
