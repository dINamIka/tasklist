package com.tasklist;

import java.util.List;

public interface ProjectDao {

	public void save(Project project);
	
	public Project findById(int id);

	public void remove(int id);

	public List<Project> getAll();

	public int count();
	
}