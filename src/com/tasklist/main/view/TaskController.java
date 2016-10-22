package com.tasklist.main.view;

import java.util.List;
import java.util.Scanner;

import com.tasklist.Project;
import com.tasklist.Task;

import main.ProjectService;
import main.TaskService;
import main.TaskServiceImpl;

public class TaskController {
	
	private ProjectController prjController;
	
	private TaskService taskService; 
	private Scanner scanner;
	private List<Task> taskList;

	public TaskController() {
		this.taskService = new TaskServiceImpl();
		this.scanner = new Scanner(System.in);
	}

	public void showTasks() {
		System.out.println("Number\tCompleted\tStarred\tTask\tStart Date\tDue Date\t");
		int i = 0;
		this.taskList = taskService.getTasks();
		for (Task t : this.taskList) {
			System.out.println(" [" + (++i) + "]\t" + t + "\t ["+ taskService.countTasksByProjectIds(t.getId()) + "]");
		}	
	}
	
	public void showCompleted() {
	}
	
	public void addTask() {
	}
	
	public void editTask() {
	}
	
	public void deleteTask() {
	}
	
	public void backToMenu() {
	}
	
	public void markAsCompleted() {
	}
	
	public void markAsStarred() {
	}
}
