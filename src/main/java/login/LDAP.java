package login;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Autentiserar Användare, Hig LDAP Tagen från VFU_Socionom_tipsfiler 2019-12-02
 */
class LDAP {

	static boolean authenticateHigLdap(String userName, String password) {
		try {
			Hashtable<String, String> env = new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://ldap.hig.se:389/");
			final String TIMEOUT_MS = "5000";
			env.put("com.sun.jndi.ldap.connect.timeout", TIMEOUT_MS);
			env.put("com.sun.jndi.ldap.read.timeout", TIMEOUT_MS);

			env.put(Context.SECURITY_PROTOCOL, "tls");

			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "uid=" + userName + ",ou=people,ou=student,dc=hig,dc=se");
			env.put(Context.SECURITY_CREDENTIALS, password);

			DirContext context = new InitialDirContext(env);
			context.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
