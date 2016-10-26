package com.tasklist.domain;

import java.util.List;

public interface TaskService {

	void saveTask(Task task);
	
	Task findTask(int id);

	void removeTask(int id);

	List<Task> getTasks(boolean completed);
	
	public List<Task> getTasksByProjectId(int projectId, boolean completed);

	int countTasks();
	
	int countTasksByProjectIds(int projectId);
	
	void markAsCompleted(Task task);

	void markAsStarred(Task task);
}
