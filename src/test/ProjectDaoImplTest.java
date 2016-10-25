package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tasklist.dao.ProjectDao;
import com.tasklist.dao.jdbcMysql.ProjectDaoImpl;
import com.tasklist.domain.Project;

public class ProjectDaoImplTest {

	ProjectDao prjDao;
	
	@Test
	public void testProjectDaoImpl() {
		prjDao = new ProjectDaoImpl();
		assertNotEquals(null, prjDao);
	}

	@Test
	public void testSave() {
		prjDao = new ProjectDaoImpl();
		
		if (prjDao.count() > 0) {
			prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
		}
		
		Project prj = new Project();
		prj.setProjectName("New test project");
		prj.setDescription("New test description");
		prjDao.save(prj);
		assertEquals(prjDao.count(), 1);
		assertEquals(prj.getDescription(), prjDao.getAll().get(0).getDescription());
		assertEquals(prj.getProjectName(), prjDao.getAll().get(0).getProjectName());
	}

	
	@Test
	public void testRemove() {
		prjDao = new ProjectDaoImpl();
		
		if (prjDao.count() > 0) {
			prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
		}
		
		Project prj = new Project();
		prj.setProjectName("New test project");
		prj.setDescription("New test description");
		prjDao.save(prj);
		assertEquals(1, prjDao.count());
		prjDao.remove(prjDao.getAll().get(0).getId());
		assertEquals(0, prjDao.count());
	}


	@Test
	public void testGetAll() {
		prjDao = new ProjectDaoImpl();
		
		if (prjDao.count() > 0) {
			prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
		}
		
		for (int i = 0; i < 5; i++ ) {
			Project prj = new Project();
			prj.setProjectName("New test project");
			prj.setDescription("New test description");
			prjDao.save(prj);
		}
		
		assertEquals(5, prjDao.count());
		prjDao.getAll().forEach(p -> assertNotEquals(null, p));
		prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
	}

	
	@Test
	public void testFindById() {
		prjDao = new ProjectDaoImpl();
		
		if (prjDao.count() > 0) {
			prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
		}
		
		Project prj = new Project();
		prj.setProjectName("New test project");
		prj.setDescription("New test description");
		prjDao.save(prj);
		
		int id = prjDao.getAll().get(0).getId();
		assertEquals(prjDao.getAll().get(0).getDescription(), prjDao.findById(id).getDescription());
		assertEquals(prjDao.getAll().get(0).getProjectName(), prjDao.findById(id).getProjectName());
		prjDao.getAll().forEach(p -> prjDao.remove(p.getId()));
	}

}
