package main;

import java.util.List;

import com.tasklist.Project;
import com.tasklist.ProjectDao;
import com.tasklist.ProjectDaoImpl;

public class ProjectServiceImpl implements ProjectService {

	ProjectDao prjDao;
	
	public ProjectServiceImpl() {
		this.prjDao = new ProjectDaoImpl();
	}
	
	@Override
	public void saveProject(Project project) {
		prjDao.save(project);
	}

	@Override
	public void removeProject(int id) {
		prjDao.remove(id);
	}

	@Override
	public Project findProject(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjects() {
		return prjDao.getAll();
	}

}
