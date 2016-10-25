package com.tasklist.main.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.tasklist.Project;
import com.tasklist.Task;

import main.ProjectService;
import main.ProjectServiceImpl;
import main.TaskService;
import main.TaskServiceImpl;

public class TaskController {
	
	private Scanner scanner;
	private TaskService taskService; 
	private ProjectService prjService; 
	private List<Task> taskList;

	public TaskController(Scanner scanner) {
		this.scanner = scanner;
		this.taskService = new TaskServiceImpl();
		this.prjService = new ProjectServiceImpl();
	}

	/**
	 * show uncompleted tasks
	 */
	public void showUndoneTasks() {
		this.taskList = taskService.getTasks(false);
		showTaskFormat();
	}
	
	public void showDone() {
		this.taskList = taskService.getTasks(true);
		showTaskFormat();
	}
	
	private void showTaskFormat() {
		System.out.println("NUMBER\tDONE\t   \u2605\tOVERDUE\tTASK\t\t\t    PRIORITY\tSTART DATE\t  DUE DATE\t  PROJECT");
		int i = 0;
		Project prj = null;
		for (Task t : this.taskList) {
			prj = prjService.findProject(t.getProjectId());
			System.out.println(" [" + (++i) + "]\t" + t + (prj != null ? prj.stringCutter(prj.getProjectName(), 20) : "[not defined]"));
		}	
	}
	
	
	public void processTask(int lineNumber) {
		Task task = null;
		if (lineNumber == -1) {
			task = new Task();
		} else {
			task = taskList.get(lineNumber - 1);
		}
		String input = null;
		while (task.getTaskName() == null || task.getTaskName().equals("")) {
			System.out.print("New Task: ");
			task.setTaskName(scanner.nextLine());
		}
		do {
			System.out.print("Starred (just type \"*\"): ");
			input = scanner.nextLine();
			if (input.equals("*")) {
				task.setStarred(true);
			} 
		} while (!(input.equals("*")) && !(input.equals("")) );
		do {
			System.out.print("Priority (choose from 0 to 3): ");
			input = scanner.nextLine();
			if(input.matches("[0-3]")) {
				task.setPriority(Integer.valueOf(input));
			}
		} while (!input.matches("[0-3]") && !input.equals(""));
		do {
			System.out.print("Start date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setStartDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getStartDate() == null && !input.equals(""));
		do {
			System.out.print("Due date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setDueDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getDueDate() == null && !input.equals(""));
		System.out.println("Choose the project from the list below for assignment the task");
		System.out.println("Number\tProject");
		int i = 0;
		List<Project> list = prjService.getProjects();
		for (Project p : list) {
			System.out.println("[" + (++i) + "]\t" + p.getProjectName());
		}
		do {
			System.out.println("Type number of project: ");
			try {
				input = scanner.nextLine();
				int prjLine = Integer.valueOf(input);
				if (prjLine > 0 && prjLine <= i) {
					task.setProjectId(list.get(prjLine - 1).getId());
				}
			} catch (Exception ex) {
			}
		} while(task.getProjectId() == 0);
		taskService.saveTask(task);
		showUndoneTasks();
	}
	
	/**
	 * add new task to the list
	 */
	public void addTask() {
		Task task = new Task();
		String input = null;
		while (task.getTaskName() == null || task.getTaskName().equals("")) {
			System.out.print("Task: ");
			task.setTaskName(scanner.nextLine());
		}
		do {
			System.out.print("Starred (just type \"*\"): ");
			input = scanner.nextLine();
			if (input.equals("*")) {
				task.setStarred(true);
			} 
		} while (!(input.equals("*")) && !(input.equals("")) );
		do {
			System.out.print("Priority (choose from 0 to 3): ");
			input = scanner.nextLine();
			if(input.matches("[0-3]")) {
				task.setPriority(Integer.valueOf(input));
			}
		} while (!input.matches("[0-3]") && !input.equals(""));
		do {
			System.out.print("Start date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setStartDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getStartDate() == null && !input.equals(""));
		do {
			System.out.print("Due date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setDueDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getDueDate() == null && !input.equals(""));
		System.out.println("Choose the project from the list below for assignment the task");
		System.out.println("Number\tProject");
		int i = 0;
		List<Project> list = prjService.getProjects();
		for (Project p : list) {
			System.out.println("[" + (++i) + "]\t" + p.getProjectName());
		}
		do {
			System.out.println("Type number of project: ");
			try {
				input = scanner.nextLine();
				int prjLine = Integer.valueOf(input);
				if (prjLine > 0 && prjLine <= i) {
					task.setProjectId(list.get(prjLine - 1).getId());
				}
			} catch (Exception ex) {
			}
		} while(task.getProjectId() == 0);
		taskService.saveTask(task);
		showUndoneTasks();
	}
	
	
	/**
	 * edit existing task
	 * @param lineNumber - number of task in displayed list
	 */
	public void editTask(int lineNumber) {
		Task task = null;
		try {
			task = taskList.get(lineNumber - 1);
		String input = null;
		do {
			System.out.println("Old Task: " + task.getTaskName());
			System.out.print("New Task: ");
			task.setTaskName(scanner.nextLine());
		} while (task.getTaskName() == null || task.getTaskName().equals(""));
		do {
			System.out.println("Current starred status: " + (task.isStarred() ? "[\u2605]" : "[ ]"));
			System.out.print("Starred (just type \"*\"): ");
			input = scanner.nextLine();
			if (input.equals("*")) {
				task.setStarred(true);
			} 
		} while (!(input.equals("*")) && !(input.equals("")) );
		do {
			System.out.println("Current Priority: " + task.getPriority());
			System.out.print("Priority (choose from 0 to 3): ");
			input = scanner.nextLine();
			if(input.matches("[0-3]")) {
				task.setPriority(Integer.valueOf(input));
			}
		} while (!input.matches("[0-3]") && !input.equals(""));
		do {
			System.out.println("Current Star Date: " + ((task.getStartDate() != null) ? task.getStartDate() : "[not defined]"));
			System.out.print("Start date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setStartDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getStartDate() == null && !input.equals(""));
		do {
			System.out.println("Current Due Date: " + ((task.getDueDate() != null) ? task.getDueDate() : "[not defined]"));
			System.out.print("Due date (type in format [YYYY-MM-DD]): ");
			input = scanner.nextLine();
			try {
				task.setDueDate(Date.valueOf(input));
			} catch (IllegalArgumentException ex) {
			}
		} while (task.getDueDate() == null && !input.equals(""));
		System.out.println("Current Project: " 
				+ ((task.getProjectId() != 0) ? prjService.findProject(task.getProjectId()).getProjectName() : "[not defined]"));
		System.out.println("Choose the project from the list below for assignment the task");
		System.out.println("Number\tProject");
		int i = 0;
		List<Project> list = prjService.getProjects();
		for (Project p : list) {
			System.out.println("[" + (++i) + "]\t" + p.getProjectName());
		}
		do {
			System.out.println("Type number of project: ");
			try {
				input = scanner.nextLine();
				int prjLine = Integer.valueOf(input);
				if (prjLine > 0 && prjLine <= i) {
					task.setProjectId(list.get(prjLine - 1).getId());
				}
			} catch (Exception ex) {
			}
		} while(task.getProjectId() == 0);
		taskService.saveTask(task);
	} catch (Exception ex) {
		System.out.println("Chosen task doesn't exist! Please type correct line number.");
	} finally {
		showUndoneTasks();
	}
	}
	
	
	/**
	 * delete existing task
	 * @param lineNumber - number of task in displayed list
	 */
	public void deleteTask(int lineNumber) {
		try {
			Task task =	taskList.get(lineNumber - 1);
			taskService.removeTask(task.getId());
		} catch (Exception ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
	
	public void markAsCompleted(int lineNumber) {
		try {
			Task task =	taskList.get(lineNumber - 1);
			taskService.markAsCompleted(task);
		} catch (Exception ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
	
	public void markAsStarred(int lineNumber) {
		try {
			taskService.markAsStarred(taskList.get(lineNumber - 1));
		} catch (Exception ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
}
