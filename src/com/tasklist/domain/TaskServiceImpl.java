package com.tasklist.domain;

import java.util.List;

import com.tasklist.dao.TaskDao;
import com.tasklist.dao.jdbcMysql.TaskDaoImpl;

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
	public List<Task> getTasks(boolean completed) {
		return this.taskDao.getAll(completed);
	}

	@Override
	public List<Task> getTasksByProjectId(int projectId, boolean completed) {
		return getTasksByProjectId(projectId, completed);
	}

	@Override
	public int countTasks() {
		return this.taskDao.count();
	}

	@Override
	public int countTasksByProjectIds(int projectId) {
		return taskDao.countByProjectId(projectId);
	}

	@Override
	public void markAsCompleted(Task task) {
		boolean done = true;
		if (task.isCompleted()) {
			done = false;
		}
		task.setCompleted(done);
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
