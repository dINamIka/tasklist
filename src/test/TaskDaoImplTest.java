package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tasklist.ProjectDaoImpl;
import com.tasklist.TaskDao;
import com.tasklist.TaskDaoImpl;

public class TaskDaoImplTest {

	TaskDao taskDao;
	
	@Test
	public void testTaskDaoImpl() {
		taskDao = new TaskDaoImpl();
		assertNotEquals(null, taskDao);
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

}
