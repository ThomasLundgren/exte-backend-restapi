package login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LDAPTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		boolean isLoggedIn = LDAP.authenticateHigLdap("17hame01", "Skolan0");
		assertTrue(isLoggedIn);
	}
}
