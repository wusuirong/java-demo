package com.sherwin.examples.jms.weblogic.topic;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Props {
	private static final String File_Name = "E:/dba88/j2eeworkspace/javaGuide/src/com/sherwin/examples/jms/weblogic/queue/jmsSetting.properties";

	private static Properties propts;

	public static void makeProptsInstance() {
		propts = new Properties();

		try {
			propts.load(new FileInputStream(File_Name));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String get(String name) {
		if (propts == null) {
			makeProptsInstance();
		}

		return (String) propts.get(name);
	}

	@SuppressWarnings("unchecked")
	public static Context getInitialContext() {
		Context context = null;

		String jndiFactory = Props.get("jndi.factory");
		String providerUrl = Props.get("jndi.provider.url");
		String username = Props.get("jndi.security.principal");
		String password = Props.get("jndi.security.credentials");

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, jndiFactory);
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			context = new InitialContext(env);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return context;
	}
}