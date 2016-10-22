package com.tasklist;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCutterTest {

	@Test
	public void testStringCutter() {
		Project prj = new Project();
		prj.setProjectName("12345678901234567890");
		prj.setDescription("New test description");
		assertEquals("12345678901234567890          ", prj.stringCutter(prj.getProjectName(), 30));
		assertEquals(30, prj.stringCutter(prj.getProjectName(), 30).length());
		prj.setProjectName("123456789012345678901234567890");
		prj.setDescription("New test description");
		assertEquals("123456789012345678901234567...", prj.stringCutter(prj.getProjectName(), 30));
		assertEquals(30, prj.stringCutter(prj.getProjectName(), 30).length());
	}
}
