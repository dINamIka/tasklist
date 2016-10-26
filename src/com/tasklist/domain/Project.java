package com.tasklist.domain;


public class Project implements StringCutter {

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
	
	@Override
	public String toString() {
		return cutString(projectName, 30) + "\t" + cutString(description, 40);
	}

	
}	