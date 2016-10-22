package main;

import java.util.List;

import com.tasklist.Task;
import com.tasklist.TaskDao;
import com.tasklist.TaskDaoImpl;

public class TaskServiceImpl implements TaskService{

	TaskDao taskDao;
	
	public TaskServiceImpl() {
		this.taskDao = new TaskDaoImpl();
	}

	@Override
	public void saveTask(Task task) {
		this.taskDao.save(task);
	}

	@Override
	public Task findTask(int id) {
		return this.findTask(id);
	}

	@Override
	public void removeTask(int id) {
		this.taskDao.remove(id);
	}

	@Override
	public List<Task> getTasks() {
		return this.taskDao.getAll();
	}

	@Override
	public List<Task> getTasksByProjectId(int projectId) {
		return getTasksByProjectId(projectId);
	}

	@Override
	public int countTasks() {
		return this.taskDao.count();
	}

	@Override
	public int countTasksByProjectIds(int projectId) {
		return taskDao.countByProjectId(projectId);
	}
	
}
