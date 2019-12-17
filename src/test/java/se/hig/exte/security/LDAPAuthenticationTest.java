package se.hig.exte.security;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import se.hig.exte.authentication.LDAPAuthentication;

class LDAPAuthenticationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		boolean isLoggedIn = LDAPAuthentication.authenticateHigLdap("17hame01", "Skolan0");
		assertTrue(isLoggedIn);
	}

}
