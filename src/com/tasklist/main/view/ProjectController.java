package com.tasklist.main.view;

import java.util.List;
import java.util.Scanner;
import com.tasklist.Project;
import main.ProjectService;
import main.ProjectServiceImpl;
import main.TaskService;
import main.TaskServiceImpl;


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
	 * display list of existed projects
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
	 *  adding new project
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
	 * edit existed project
	 * @param lineNumber - number of project in displayed list
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
		} catch (Exception ex) {
			System.out.println("Chosen project doesn't exist! Please type correct line number.");
		} finally {
			showProjects();
		}
	}
	
	
	/**
	 * delete existing project
	 * @param lineNumber - number of project in displayed list
	 */
	public void deleteProject(int lineNumber) {
		try {
			Project project = prjList.get(lineNumber - 1);
			prjService.removeProject(project.getId());
		} catch (Exception ex) {
			System.out.println("Chosen project doesn't exist! Please type correct line number.");
		} finally {
			showProjects();
		}
	}
	
	

}
