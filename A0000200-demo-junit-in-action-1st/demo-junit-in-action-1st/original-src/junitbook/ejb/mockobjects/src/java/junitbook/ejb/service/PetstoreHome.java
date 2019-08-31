package junitbook.ejb.service;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface PetstoreHome extends EJBHome
{
    Petstore create() throws CreateException;
}
