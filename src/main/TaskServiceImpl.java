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
	public List<Task> getTasksByProjectId(int taskId) {
		return getTasksByProjectId(taskId);
	}

	@Override
	public int countTasks() {
		return this.taskDao.count();
	}

	@Override
	public int countTasksByProjectIds(int taskId) {
		return taskDao.countByProjectId(taskId);
	}

	@Override
	public void markAsCompleted(Task task) {
		task.setCompleted(true);
		taskDao.save(task);
	}

	@Override
	public void markAsStarred(Task task) {
		boolean starred = true;
		if (task.isStarred()) {
			starred = false;
		}
		task.setStarred(starred);
		taskDao.save(task);
	}

}
