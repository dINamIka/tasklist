package com.tasklist.domain;

import java.util.List;

public interface ProjectService {

	public void saveProject(Project project);
	
	public void removeProject(int id);
	
	public Project findProject(int id);
	
	public List<Project> getProjects();
	
}
