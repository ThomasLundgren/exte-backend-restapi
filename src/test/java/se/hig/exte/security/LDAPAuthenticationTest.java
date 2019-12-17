package se.hig.exte.security;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;
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
