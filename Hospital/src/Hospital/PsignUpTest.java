package Hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PsignUpTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testcorrectPatientSignUp() {
		PsignUp psignup = new PsignUp();
		int result = psignup.signup("Jack", "1987.03.21", "0008", "male", "2265078794", "33", "zxcv", "zxcv");
		Assert.assertEquals("Patient signup y", 1 , result);
	}

	@Test
	void testPasswordnotsameSignUp() {
		PsignUp psignup = new PsignUp();
		int result = psignup.signup("Jack Ma", "1987.03.21", "0007", "male", "2265078794", "33", "zxcv", "zxc");
		Assert.assertEquals("Patient signup 1", -3 , result);
	}
	
	@Test
	void testmissinfoSignUp() {
		PsignUp psignup = new PsignUp();
		int result = psignup.signup("", "1987.03.21", "0007", "male", "2265078794", "33", "zxcv", "zxcv");
		Assert.assertEquals("Patient signup 2", -1 , result);
	}
	
	@Test
	void testexistSignUp() {
		PsignUp psignup = new PsignUp();
		int result = psignup.signup("jack Ma", "1987.03.21", "0001", "male", "2265078794", "33", "zxcv", "zxcv");
		Assert.assertEquals("Patient signup 3", -2 , result);
	}
	
}
