package com.tasklist.domain;

import java.util.List;

import com.tasklist.dao.ProjectDao;
import com.tasklist.dao.jdbcMysql.ProjectDaoImpl;

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
		return prjDao.findById(id);
	}

	@Override
	public List<Project> getProjects() {
		return prjDao.getAll();
	}

}
