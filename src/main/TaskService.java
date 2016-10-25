package main;

import java.util.List;

import com.tasklist.Task;

public interface TaskService {

	void saveTask(Task task);
	
	Task findTask(int id);

	void removeTask(int id);

	List<Task> getTasks();
	
	public List<Task> getTasksByProjectId(int taskId);

	int countTasks();
	
	int countTasksByProjectIds(int taskId);
	
	void markAsCompleted(Task task);

	void markAsStarred(Task task);
}
