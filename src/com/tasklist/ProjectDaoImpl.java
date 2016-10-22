package com.tasklist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

	public ProjectDaoImpl() {
	}

	public void save(Project project) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement ps = null;
		
		
		int projectId = project.getId();
		String projectName = project.getProjectName();
		String description = project.getDescription();
		
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

	
	public List<Project> getAll() {
		Connection connection = DbUtil.getConnection();
		List<Project> prjList = new ArrayList<>();
		Project project = null;
		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM PROJECT");
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

	
	public Project findById(int id) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement prepStatement = null;
		Project project = null;
		try {
			prepStatement = connection.prepareStatement("SELECT * FROM PROJECT WHERE ID = ?");
			prepStatement.setInt(1, id);

			ResultSet result = prepStatement.executeQuery();
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
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return project;
	}
	
}
