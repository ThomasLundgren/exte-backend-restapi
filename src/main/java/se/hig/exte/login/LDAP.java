package se.hig.exte.login;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Authenticates users via the HiG LDAP server.
 */
public class LDAP {

	/**
	 * Authenticates a user via the HiG LDAP server. All code is taken from the VFU
	 * Socionom project.
	 * 
	 * @param userId   The ID of the user.
	 * @param password The passed in password.
	 * @return True if the authentication succeeded, false if the authentication
	 *         failed.
	 */
	static boolean authenticateHigLdap(String userId, String password) {

		try {
			Hashtable<String, String> env = new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://ldap.hig.se:389/");
			final String TIMEOUT_MS = "5000";
			env.put("com.sun.jndi.ldap.connect.timeout", TIMEOUT_MS);
			env.put("com.sun.jndi.ldap.read.timeout", TIMEOUT_MS);

			env.put(Context.SECURITY_PROTOCOL, "tls");

			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "uid=" + userId + ",ou=people,ou=student,dc=hig,dc=se");
			env.put(Context.SECURITY_CREDENTIALS, password);

			DirContext ctx = new InitialDirContext(env);
			ctx.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
