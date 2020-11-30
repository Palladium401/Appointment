package Hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PappointmentTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testcorrectmakeAppointment() {
		Pmake pmake =new Pmake();
		boolean result =pmake.Make_Appointment("1", "0002");
		Assert.assertEquals("Patient appointment 1", true , result);
	}
	
	@Test
	void testmissinfomakeAppointment1() {
		Pmake pmake =new Pmake();
		boolean result =pmake.Make_Appointment("", "0002");
		Assert.assertEquals("Patient appointment 2", false , result);
	}

	@Test
	void testmissinfomakeAppointment2() {
		Pmake pmake =new Pmake();
		boolean result =pmake.Make_Appointment("", "");
		Assert.assertEquals("Patient appointment 3", false , result);
	}
	
	@Test
	void testmissinfomakeAppointment3() {
		Pmake pmake =new Pmake();
		boolean result =pmake.Make_Appointment("16", "");
		Assert.assertEquals("Patient appointment 4", false , result);
	}
	
	@Test
	void testcorrectupdateinfo() {
		Pupdateinfo pupdateinfo = new Pupdateinfo();
		boolean result = pupdateinfo.updateinfo("Peiran Sun", "Female", "2265067896", "25", "0003");
		Assert.assertEquals("Patient appointment 5", true , result);
	}
	
	@Test
	void testmissinfoupdateinfo() {
		Pupdateinfo pupdateinfo = new Pupdateinfo();
		boolean result = pupdateinfo.updateinfo("", "Female", "2265067896", "25", "0003");
		Assert.assertEquals("Patient appointment 6", false , result);
	}
	
	@Test
	void testcancelappointment() {
		Pcancel pcancel = new Pcancel();
		boolean result = pcancel.cancel("1", "0002");
		Assert.assertEquals("Patient appointment 7", true , result);
	}
	
	@Test
	void testwrongcancelappointment() {
		Pcancel pcancel = new Pcancel();
		boolean result = pcancel.cancel("8", "");
		Assert.assertEquals("Patient appointment 8", false , result);
	}
}
