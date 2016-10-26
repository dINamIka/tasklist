package com.tasklist.dao;

import java.util.List;

import com.tasklist.domain.Task;

public interface TaskDao {

	void save(Task task);
	
	Task findById(int id);

	void remove(int id);

	List<Task> getAll(boolean completed);
	
	public List<Task> getAllByProjectId(int projectId, boolean completed);

	int count();
	
	int countByProjectId(int projectId);
	
}