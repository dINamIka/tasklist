package test;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import com.tasklist.DbUtil;
import com.tasklist.Project;
import com.tasklist.ProjectDao;
import com.tasklist.ProjectDaoImpl;
import com.tasklist.Task;
import com.tasklist.TaskDao;
import com.tasklist.TaskDaoImpl;

public class Test {

	public static void main(String[] args) {
		
//		ProjectDao prjDao = new ProjectDaoImpl();
//		Project prj = new Project();
//		prj.setProjectName("New test project");
//		prj.setDescription("New test description");
//		prjDao.save(prj);
//		
		TaskDao tskDao = new TaskDaoImpl();
//		ProjectDao prjDao = new ProjectDaoImpl();
		
//		Task task = new Task();
//		task.setTaskName("352 test task");
//		task.setPriority(1);
//		task.setProjectId(349);
//		task.setStartDate(Date.valueOf(LocalDate.now()));
//		tskDao.save(task);
		
//		System.out.println("Project\t\t\t\tDescription\t\t\t\t\t\tTasks");
//		prjDao.getAll().forEach(System.out::println);
		
		tskDao.getAllByProjectId(349).forEach(System.out::println);
//		System.out.println(tskDao.count());

		
	}

}
