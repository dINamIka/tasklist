package com.tasklist.ui;

import java.util.List;
import java.util.Scanner;

import com.tasklist.domain.Project;
import com.tasklist.domain.ProjectService;
import com.tasklist.domain.ProjectServiceImpl;
import com.tasklist.domain.TaskService;
import com.tasklist.domain.TaskServiceImpl;


public class ProjectController {
	
	private Scanner scanner;
	private ProjectService prjService; 
	private TaskService taskService;
	private List<Project> prjList;
	
	public ProjectController(Scanner scanner) {
		this.scanner = scanner;
		this.prjService = new ProjectServiceImpl();
		this.taskService = new TaskServiceImpl();
	}
	
	/**
	 * Display list of existed projects in command line
	 */
	public void showProjects() {
		System.out.println("Number\tProject\t\t\t\tDescription\t\t\t\t\t\tTasks");
		int i = 0;
		this.prjList = prjService.getProjects();
		for (Project p : this.prjList) {
			System.out.println(" [" + (++i) + "]\t" + p + "\t\t ["+ taskService.countTasksByProjectIds(p.getId()) + "]");
		}	
	}
	
	/**
	 *  Open mode for adding new project
	 */
	public void addProject() {
		Project project = new Project();
		while (project.getProjectName() == null || project.getProjectName().equals("")) {
			System.out.print("Project name: ");
			project.setProjectName(scanner.nextLine());
		}
		System.out.print("Description: ");
		project.setDescription(scanner.nextLine());
		prjService.saveProject(project);
		showProjects();
	}
	
	
	/**
	 * Open mode for editing new project
	 * @param int lineNumber of project in displayed list
	 */
	public void editProject(int lineNumber) {
		try {
			Project project = prjList.get(lineNumber - 1);
			do {
				System.out.println("Old project name: " + project.getProjectName());
				System.out.print("New project name: ");
				project.setProjectName(scanner.nextLine());
			} while (project.getProjectName() == null || project.getProjectName().equals(""));
			
			System.out.println("Old description: " + project.getDescription());
			System.out.print("New description: ");
			project.setDescription(scanner.nextLine());
			prjService.saveProject(project);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Chosen project doesn't exist! Please type correct line number.");
		} finally {
			showProjects();
		}
	}
	
	
	/**
	 * Delete existing project by line number
	 * @param int lineNumber of project in displayed list
	 */
	public void deleteProject(int lineNumber) {
		try {
			Project project = prjList.get(lineNumber - 1);
			System.out.println("Warning! All related tasks with Project \"" + project.getProjectName() 
								+ "\" will be removed too!\nPlease type \"y\" for continue and \"n\" for cancel.");
			String input = null;
			do {
				input = scanner.nextLine();
			} while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
			if (input.equalsIgnoreCase("y")) {
				prjService.removeProject(project.getId());
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Chosen project doesn't exist! Please type correct line number.");
		} finally {
			showProjects();
		}
	}
	
	

}
