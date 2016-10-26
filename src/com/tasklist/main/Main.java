package com.tasklist.main;

import java.util.Scanner;

import com.tasklist.ui.MainController;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainController m = new MainController(scanner);
		m.launchMainMenu();
	}

}
