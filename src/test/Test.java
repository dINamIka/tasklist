package test;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tasklist.DbUtil;
import com.tasklist.Project;
import com.tasklist.ProjectDao;
import com.tasklist.ProjectDaoImpl;
import com.tasklist.Task;
import com.tasklist.TaskDao;
import com.tasklist.TaskDaoImpl;

public class Test {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Pattern comPat = Pattern.compile("([a-zA-Z]+\\s?){1,3}");
		
		while(true) {
			String input = scanner.nextLine();
            Matcher comMatcher = comPat.matcher(input);
            
            while(comMatcher.find()) {
            	System.out.println(input.substring(comMatcher.end() -1 ));
            	System.out.println(comMatcher.group());
            }
            
		}
	}

}
