package com.tasklist.main.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainController {

	private Scanner scanner;
	private TaskController taskController;
	private ProjectController prjController;
	
	public MainController(Scanner scanner) {
		this.scanner = scanner;
		this.taskController = new TaskController(scanner);
		this.prjController = new ProjectController(scanner);
	}
	
	
	public void launchCommandMenu() {
		Pattern comPat = Pattern.compile("^([a-zA-Z]+\\s?){1,3}");
		boolean menu = true;
		while(menu) {
			String input = this.scanner.nextLine();
            Matcher comMatcher = comPat.matcher(input);
            
            if (comMatcher.find()) {
            	int startOfCommand = comMatcher.start();
            	int endOfCommand = comMatcher.end();
	            String command = input.substring(startOfCommand, endOfCommand);
	            if (command.substring(endOfCommand - 1, endOfCommand).equals(" ")) {

	            	command = command.substring(0, endOfCommand - 1);
	            }
	            String line;
	            int number;
	            switch(command.toUpperCase()) {
		            case "ADD TASK":
		            	this.taskController.addTask();
		            	break;
		            case "EDIT TASK":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.taskController.editTask(number);
		 	            break;
		            case "DELETE TASK":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.taskController.deleteTask(number);
		 	            break;
		            case "MARK COMPLETED":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.taskController.markAsCompleted(number);
		 	            break;
		            case "MARK STARRED":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.taskController.markAsStarred(number);
		 	            break;
		            case "SHOW TASKS":
		            	this.taskController.showTasks();
		            	break;
		            case "SHOW COMPLETED":
		            	//add method to controller
		            case "ADD PROJECT":
		            	this.prjController.addProject();
		            	break;
		            case "EDIT PROJECT":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.prjController.editProject(number);
		 	            break;
		            case "DELETE PROJECT":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	this.prjController.deleteProject(number);
		 	            break;
		            case "SHOW PROJECTS":
		            	this.prjController.showProjects();
		            	break;
		            case "SHOW MENU":
		            	menu = false;
		            	this.showMenu();
		            	break;
		            case "HELP":
		            	//add method to main controoler
		            	System.out.println("help menu");
		            	break;
		            case "EXIT":
		            	System.exit(0);
	            	default:
	            		System.out.println("Unrecognized command!\nType \"help\" for showing tips");
	            		break;
	            }   
            } else {
            	System.out.println("Unrecognized command!");
            }
		}
	}
	
	public void showMenu() {
		boolean menu = true;
		
		while(menu) {
			System.out.println("Welcome to TODO Manager\n  MENU:");
			Arrays.asList(MainMenu.values())
			.forEach(e -> System.out.println("[" + e.getMenuLine() + "] " + e.getMenuString()));
			System.out.println("Please make your choice!");
			String id = this.scanner.nextLine();
			switch(id) {
			case "1":
				taskController.showTasks();
				menu = false;
				launchCommandMenu();
				break;
			case "2":
				taskController.addTask();
				menu = false;
				break;
			case "3": 
				prjController.showProjects();
				menu = false;
				launchCommandMenu();
				break;
			case "4":
				prjController.addProject();
				menu = false;
				break;
			case "5":
				System.exit(0);
			default:
				System.out.println("Unrecognized command! Please choose from displayed list!");
			}
		}
	}
	
	/**
	 * parsing the line number in command
	 * @param line - end of string that contents number of line
	 * @return - an int number of line in list
	 */
	private int getLineNumber(String line) {
		int number = 0;
		Pattern digitPat = Pattern.compile("\\s[0-9]{1,4}");
		Matcher digitMatcher = digitPat.matcher(line);
		if (digitMatcher.find()) {
			number = Integer.valueOf(line.substring(1, digitMatcher.end()));
		}
        return number;
	}

	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		MainController m = new MainController(scanner);
		m.showMenu();
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
	
	public enum Commands {
		ADD_TASK("add task - add new task"),
		SHOW_TASKS("show tasks - display all current tasks"),
		SHOW_COMPLETED("show completed - display all done tasks"),
		EDIT_TASK("edit task <line number> - editing particular task"),
		DELETE_TASK("delete task <line number>"),
		MARK_COMPLETED(""),
		MARK_STARRED(""),
		SHOW_BY_PROJECT(""),
		ADD_PROJECT(""),
		SHOW_PROJECTS(""),
		EDIT_PROJECT(""),
		DELETE_PROJECT(""),
		SHOW_MENU(""),
		HELP(""),
		EXIT("");
		
		Commands(String tip) {
		}
		
	}
}
