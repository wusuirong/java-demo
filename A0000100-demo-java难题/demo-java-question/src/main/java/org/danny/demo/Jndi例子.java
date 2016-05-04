package org.danny.demo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Jndi例子 {

	public static void main(String[] args) throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		Context ctx = new InitialContext(env);
		
		String name = "d:\\桌面.jpg";
		Object obj = ctx.lookup(name);
		
		System.out.println("名称(" + name + ")绑定的对象是: " + obj);
	}
}
