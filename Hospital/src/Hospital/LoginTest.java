package Hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testcorrectinputPatient() {
		Login login = new Login();
		int result = login.LoginAct("Patient", "Peiran Sun", "qwer");
		Assert.assertEquals("Patient login y", 1 , result);
	}
	@Test
	public void testcorrectinputDoctor() {
		Login login = new Login();
		int result = login.LoginAct("Doctor", "Peter", "queen");
		Assert.assertEquals("Doctor login y", 2 , result);
	}
	@Test
	public void testincorrectinputDoctor() {
		Login login = new Login();
		int result = login.LoginAct("Doctor", "Peter", null);
		Assert.assertEquals("Doctor login n", -1 , result);
	}
	@Test
	public void testincorrectinputPatient() {
		Login login = new Login();
		int result = login.LoginAct("Patient", null, "qwer");
		Assert.assertEquals("Patient login n", -1 , result);
	}
}
