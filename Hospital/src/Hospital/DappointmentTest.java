package Hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DappointmentTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testcorrectupload() {
		DaUpload Dupload = new DaUpload();
		boolean result =Dupload.upload("2020-12-11 9:00", "Child", "1002");
		Assert.assertEquals("DoctorAppointment 1", true , result);
	}
	
	@Test
	void testmissinfoupload1() {
		DaUpload Dupload = new DaUpload();
		boolean result =Dupload.upload("", "Child", "1002");
		Assert.assertEquals("DoctorAppointment 2", false , result);
	}
	
	@Test
	void testmissinfoupload2() {
		DaUpload Dupload = new DaUpload();
		boolean result =Dupload.upload("2020-12-11 9:00","", "1002");
		Assert.assertEquals("DoctorAppointment 3", false , result);
	}
	
	@Test
	void testtimewrongupload2() {
		DaUpload Dupload = new DaUpload();
		boolean result =Dupload.upload("2020-11-11 9:00","Child", "1002");
		Assert.assertEquals("DoctorAppointment 4", false , result);
	}
	
	@Test
	void testcorrectupdate() {
		DaUpdate Dupload = new DaUpdate();
		boolean result = Dupload.Update("2020-12-31 10:00", "Surgery", "1");
		Assert.assertEquals("DoctorAppointment 5", true , result);
	}
	
	@Test
	void testwrongtimeupdate() {
		DaUpdate Dupload = new DaUpdate();
		boolean result = Dupload.Update("2020-10-31 10:00", "Surgery", "1");
		Assert.assertEquals("DoctorAppointment 6", false , result);
	}
	
	@Test
	void testwmissinfoupdate() {
		DaUpdate Dupload = new DaUpdate();
		boolean result = Dupload.Update("2020-10-31 10:00", "", "1");
		Assert.assertEquals("DoctorAppointment 7", false , result);
	}
	
	@Test
	void testcorrectDelete() {
	DaDelete Ddelete = new DaDelete();
	boolean result = Ddelete.Delete("15");
	Assert.assertEquals("DoctorAppointment 8", true , result);
	}
	
	@Test
	void testincorrectDelete() {
	DaDelete Ddelete = new DaDelete();
	boolean result = Ddelete.Delete("");
	Assert.assertEquals("DoctorAppointment 9", false , result);
	}

}
