package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tasklist.dao.TaskDao;
import com.tasklist.dao.jdbcMysql.TaskDaoImpl;

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
