package com.tasklist.ui;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.tasklist.domain.Project;
import com.tasklist.domain.ProjectService;
import com.tasklist.domain.ProjectServiceImpl;
import com.tasklist.domain.Task;
import com.tasklist.domain.TaskService;
import com.tasklist.domain.TaskServiceImpl;

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
	 * Show uncompleted tasks in command line
	 */
	public void showUndoneTasks() {
		this.taskList = taskService.getTasks(false);
		showTaskFormat();
	}
	
	/**
	 * Show completed tasks in command line
	 */
	public void showDone() {
		this.taskList = taskService.getTasks(true);
		showTaskFormat();
	}
	
	/**
	 * Format view for proper displaying in command line
	 */
	private void showTaskFormat() {
		System.out.println("NUMBER\tDONE\t   \u2605\tOVERDUE\tTASK\t\t\t    PRIORITY\tSTART DATE\t  DUE DATE\t  PROJECT");
		int i = 0;
		Project prj = null;
		for (Task t : this.taskList) {
			prj = prjService.findProject(t.getProjectId());
			System.out.println(" [" + (++i) + "]\t" + t + (prj != null ? prj.cutString(prj.getProjectName(), 20) : "[not defined]"));
		}	
	}
	
	/**
	 * Open mode for adding new task to the list
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
		int prjLine = 0;
		do {
			System.out.print("Type number of project: ");
			try {
				input = scanner.nextLine();
				prjLine = Integer.valueOf(input);
				if (prjLine > 0 && prjLine <= i) {
					task.setProjectId(list.get(prjLine - 1).getId());
				}
			} catch (NumberFormatException ex) {
			}
		} while(prjLine < 1 || prjLine > list.size());
		taskService.saveTask(task);
		showUndoneTasks();
	}
	
	
	/**
	 * Open mode for editing existing task
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
		int prjLine = 0;
		do {
			System.out.print("Type number of project: ");
			try {
				input = scanner.nextLine();
				prjLine = Integer.valueOf(input);
				if (prjLine > 0 && prjLine <= i) {
					task.setProjectId(list.get(prjLine - 1).getId());
				}
			} catch (NumberFormatException ex) {
			}
		} while(prjLine < 1 || prjLine > list.size());
		taskService.saveTask(task);
		} catch (IndexOutOfBoundsException ex) {
		System.out.println("Chosen task doesn't exist! Please type correct line number.");
	} finally {
		showUndoneTasks();
	}
	}
	
	
	/**
	 * Delete existing task by line number
	 * @param lineNumber - number of task in displayed list
	 * @throws IndexOutOfBoundsException - if line number doesn't exist
	 */
	public void deleteTask(int lineNumber) {
		try {
			Task task =	taskList.get(lineNumber - 1);
			taskService.removeTask(task.getId());
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
	
	
	/**
	 * Mark task as Done by number of line
	 * @param int lineNumber 
	 * @throws IndexOutOfBoundsException - if line number doesn't exist
	 */
	public void markAsCompleted(int lineNumber) {
		try {
			Task task =	taskList.get(lineNumber - 1);
			taskService.markAsCompleted(task);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
	
	
	/**
	 * Mark task as Starred by number of line
	 * @param int lineNumber
	 * @throws IndexOutOfBoundsException - if line number doesn't exist
	 */
	public void markAsStarred(int lineNumber) {
		try {
			taskService.markAsStarred(taskList.get(lineNumber - 1));
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Chosen task doesn't exist! Please type correct line number.");
		} finally {
			showUndoneTasks();
		}
	}
}
