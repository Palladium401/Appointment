package Hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoctorSignTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testcorrectSignUp() {
		DoctorSign doctor = new DoctorSign();
		boolean result = doctor.signUp("1010", "Surgery", "qwerasdf@gmail.com", "Jacky", "123456", "123456");
		Assert.assertEquals("Doctor signup 1", true , result);
	}
	
	@Test
	void testmissinfoSignUp() {
		DoctorSign doctor = new DoctorSign();
		boolean result = doctor.signUp("1004", "", "qwerasdf@gmail.com", "Jacky", "123456", "123456");
		Assert.assertEquals("Doctor signup 2", false , result);
	}
	
	@Test
	void testexistaccountSignUp() {
		DoctorSign doctor = new DoctorSign();
		boolean result = doctor.signUp("1001", "Surgery", "qwerasdf@gmail.com", "Jacky", "123456", "123456");
		Assert.assertEquals("Doctor signup 3", false , result);
	}
	
	@Test
	void testpasswordnotsameSignUp() {
		DoctorSign doctor = new DoctorSign();
		boolean result = doctor.signUp("1005", "Surgery", "qwerasdf@gmail.com", "Jacky", "123456", "12345");
		Assert.assertEquals("Doctor signup 4", false , result);
	}

}
