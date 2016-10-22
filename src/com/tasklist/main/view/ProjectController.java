package com.tasklist.main.view;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.tasklist.Project;

import main.ProjectService;
import main.ProjectServiceImpl;
import main.TaskService;
import main.TaskServiceImpl;


public class ProjectController {

	private ProjectService prjService; 
	private Scanner scanner;
	private List<Project> prjList;
	private TaskService taskService;
	
	
	public ProjectController() {
		this.prjService = new ProjectServiceImpl();
		this.scanner = new Scanner(System.in);
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
		showProjects();
	}
	
	
	/**
	 * delete existed project
	 * @param lineNumber - number of project in displayed list
	 */
	public void deleteProject(int lineNumber) {
		Project project = prjList.get(lineNumber - 1);
		prjService.removeProject(project.getId());
		showProjects();
	}
	
	
	/**
	 * display main menu
	 */
	public void showMenu() {
		System.out.println("Welcome to TODO Manager\n  MENU:");
		Arrays.asList(MainMenu.values())
		.forEach(e -> System.out.println("[" + e.getMenuLine() + "] " + e.getMenuString()));
		System.out.println("Please make your choice!");
	}
	
	public void choose(int id) throws IOException {
		switch(id) {
			case 3: 
				showProjects();
				break;
			case 4:
				addProject();
				break;
			case 5:
				System.exit(0);
				break;
			case 6:
				deleteProject(Integer.valueOf(scanner.nextLine()));
				break;
			case 7:
				editProject(Integer.valueOf(scanner.nextLine()));
				break;
			case 8:
				showMenu();
				break;
			default:
//				System.exit(0);
		}
	}

	public static void main(String[] args) throws IOException {
		ProjectController prjController = new ProjectController();
		Scanner scanner = prjController.scanner;	
	
		prjController.showMenu();
		
		while (true) {
			int choise = Integer.valueOf(scanner.nextLine());
			prjController.choose(choise);
		}

	}
	
	public enum MainMenu {
		SHOWTASKS(1, "SHOW ALL TASKS"),
		ADDTASK(2, "ADD NEW TASK"),
		SHOWPROJECTS(3, "SHOW ALL PROJECTS"),
		ADDPROJECT(4, "ADD NEW PROJECT"),
		QUIT(5, "QUIT");
		
		
		private int menuLine;
		private String menuString;
		
		MainMenu(int menuLine, String menuString) {
			this.menuLine = menuLine;
			this.menuString = menuString;
		}

		public int getMenuLine() {
			return menuLine;
		}

		public String getMenuString() {
			return menuString;
		}
		
		
	}
}
