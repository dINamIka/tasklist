package main;

import java.util.List;

import com.tasklist.Project;

public interface ProjectService {

	public void saveProject(Project project);
	
	public void removeProject(int id);
	
	public Project findProject(int id);
	
	public List<Project> getProjects();
	
}
