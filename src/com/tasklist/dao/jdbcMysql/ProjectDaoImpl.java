package com.tasklist.dao.jdbcMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tasklist.dao.DbUtil;
import com.tasklist.dao.ProjectDao;
import com.tasklist.domain.Project;

public class ProjectDaoImpl implements ProjectDao {

	public ProjectDaoImpl() {
	}

	/**
	 * Saving and updating Project in database
	 * @param Project object for saving or merging
	 */
	public void save(Project project) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement ps = null;
		int projectId = project.getId();
		String projectName = project.getProjectName();
		if (projectName != null && projectName.length() > 64) {
			projectName = projectName.substring(0, 64);
		}
		String description = project.getDescription();
		if (description != null && description.length() > 128) {
			description = description.substring(0, 128);
		}
		
		try {
			if (projectId == 0) {
				ps = connection
						.prepareStatement("INSERT INTO PROJECT(PROJECTNAME, DESCRIPTION) VALUES(?, ?)");
				ps.setString(1, projectName);
				ps.setString(2, description);
			} else {
				ps = connection
						.prepareStatement("UPDATE PROJECT SET PROJECTNAME = ?, DESCRIPTION = ? WHERE ID = ?");
				ps.setString(1, projectName);
				ps.setString(2, description);
				ps.setInt(3, projectId);
			}
				ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}

	/**
	 * Removing Project from database by Id
	 * @param int Id number of Project
	 */
	public void remove(int id) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("DELETE FROM PROJECT WHERE ID = ?");
			prepStatement.setInt(1, id);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getting all Projects from database (ordered by projectName)
	 * @return List<Project> 
	 */
	public List<Project> getAll() {
		Connection connection = DbUtil.getConnection();
		List<Project> prjList = new ArrayList<>();
		Project project = null;
		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM PROJECT ORDER BY PROJECTNAME");
			if (result != null) {
				while (result.next()) {
					project = new Project();
					project.setId(result.getInt("ID"));
					project.setProjectName(result.getString("PROJECTNAME"));
					project.setDescription(result.getString("DESCRIPTION"));
					prjList.add(project);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prjList;
	}
	
	
	public int count() {
		Connection connection = DbUtil.getConnection();
		Statement st = null;
		int counter = 0;
		try {
			st = connection.createStatement();
			ResultSet result = st.executeQuery("SELECT COUNT(*) FROM PROJECT");
			if (result != null) {
				while(result.next()) {
					counter = result.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return counter;
	}

	
	/**
	 * Looking for Project in database by Id 
	 * @param int Id number of Project
	 * @return Project from database
	 */
	public Project findById(int id) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement prepStatement = null;
		ResultSet result = null;
		Project project = null;
		try {
			prepStatement = connection.prepareStatement("SELECT * FROM PROJECT WHERE ID = ?");
			prepStatement.setInt(1, id);

			result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					project = new Project();
					project.setId(result.getInt("ID"));
					project.setProjectName(result.getString("PROJECTNAME"));
					project.setDescription(result.getString("DESCRIPTION"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return project;
	}
	
}
