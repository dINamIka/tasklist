package com.tasklist.dao;

import java.util.List;

import com.tasklist.domain.Project;

public interface ProjectDao {

	void save(Project project);
	
	Project findById(int id);

	void remove(int id);

	List<Project> getAll();

	int count();
	
}