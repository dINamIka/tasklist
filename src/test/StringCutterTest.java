package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tasklist.domain.Project;

public class StringCutterTest {

	@Test
	public void testStringCutter() {
		Project prj = new Project();
		prj.setProjectName("12345678901234567890");
		prj.setDescription("New test description");
		assertEquals("12345678901234567890          ", prj.cutString(prj.getProjectName(), 30));
		assertEquals(30, prj.cutString(prj.getProjectName(), 30).length());
		
		prj.setProjectName("123456789012345678901234567890");
		prj.setDescription("New test description");
		assertEquals("123456789012345678901234567...", prj.cutString(prj.getProjectName(), 30));
		assertEquals(30, prj.cutString(prj.getProjectName(), 30).length());
		
		
		assertEquals("1...", prj.cutString(prj.getProjectName(), 4));
		assertEquals(4, prj.cutString(prj.getProjectName(), 4).length());
	}
}
