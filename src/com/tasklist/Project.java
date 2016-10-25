package com.tasklist;

import java.util.Arrays;

public class Project {

	private int id;
	private String projectName;
	private String description;
	
	public Project() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String stringCutter(String str, int length) {
		char[] array = new char[length];
	    Arrays.fill(array, ' ');
		String space = new String(array);
		if (str != null) {
			if (str.length() >= length) {
				return str.substring(0, length - 3) + "...";
			} else {
				return str + space.substring(0, length - str.length());
			}
		}
		return space;
	}
	
	@Override
	public String toString() {
		return stringCutter(projectName, 30) + "\t" + stringCutter(description, 40);
	}

	
}	