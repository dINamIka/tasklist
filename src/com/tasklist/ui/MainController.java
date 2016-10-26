package com.tasklist.ui;

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
	
	/**
	 * Launch view of command line menu. Parse commands that have 
	 * typed by user like "edit task, add task..."
	 */
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
		            case "MARK DONE":
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
		            	this.taskController.showUndoneTasks();
		            	break;
		            case "SHOW DONE":
		            	this.taskController.showDone();
		            	break;
		            case "ADD PROJECT":
		            	this.prjController.addProject();
		            	break;
		            case "EDIT PROJECT":
		            	line = input.substring(comMatcher.end() - 1, input.length());
		            	number = getLineNumber(line);
		            	System.out.println(number);
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
		            case "MENU":
		            	menu = false;
		            	this.launchMainMenu();
		            	break;
		            case "HELP":
		            	showHelp();
		            	break;
		            case "EXIT":
		            	System.out.println("Good bye!");
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
	
	
	/**
	 * Launch view of main menu 
	 */
	public void launchMainMenu() {
		boolean menu = true;
		
		while(menu) {
			System.out.println("Welcome to TODO Manager\n  MENU:");
			Arrays.asList(MainMenu.values())
							.forEach(e -> System.out.println("[" + e.getMenuLine() + "] " + e.getMenuString()));
			System.out.println("Please make your choice!");
			String id = this.scanner.nextLine();
			switch(id) {
			case "1":
				taskController.showUndoneTasks();
				menu = false;
				launchCommandMenu();
				break;
			case "2":
				taskController.addTask();
				menu = false;
				launchCommandMenu();
				break;
			case "3": 
				prjController.showProjects();
				menu = false;
				launchCommandMenu();
				break;
			case "4":
				prjController.addProject();
				menu = false;
				launchCommandMenu();
				break;
			case "5":
				System.out.println("Good bye!");
				System.exit(0);
			default:
				System.out.println("Unrecognized command! Please choose from displayed list!");
			}
		}
	}
	
	/**
	 * Show help menu with commands tips
	 */
	public void showHelp() {
		System.out.println("HELP MENU:");
    	Arrays.asList(Commands.values()).forEach(e -> System.out.println(e.getTip()));
	}
	
	/**
	 * Parsing the line number in command
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
		SHOW_TASKS("show tasks - show all current tasks"),
		SHOW_DONE("show done - show all done tasks"),
		EDIT_TASK("edit task <line number> - editing selected task (task select by line number)"),
		DELETE_TASK("delete task <line number> - delete selected task (task select by line number)"),
		MARK_DONE("mark done <line number> - mark task as completed (or uncompleted)"),
		MARK_STARRED("mark starred <line number> - mark task as starred (or unstarred)"),
		ADD_PROJECT("add project - add new project"),
		SHOW_PROJECTS("show projects - show all projects"),
		EDIT_PROJECT("edit project <line number> - edit selected project (project select by line number)"),
		DELETE_PROJECT("delete project <line number> - delete selected project (project select by line number)"),
		MENU("menu - open main menu of the application"),
		HELP("help - show list of commands with descriptions"),
		EXIT("exit - closing the application");
		
		String tip;
		
		Commands(String tip) {
			this.tip = tip;
		}
		
		public String getTip() {
			return tip;
		}
		
	}
}
