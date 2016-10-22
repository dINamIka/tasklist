package com.tasklist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

	public TaskDaoImpl() {
	}

	public void save(Task task) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement ps = null;
		
		int taskId = task.getId();
		String taskName = task.getTaskName();
		boolean completed = task.isCompleted();
		boolean starred = task.isStarred();
		int priority = task.getPriority();
		Date startDate = task.getStartDate();
		Date dueDate = task.getDueDate();
		int projectId = task.getProjectId();
		String note = task.getNote();
		
		try {
			if (taskId == 0) {
				ps = connection
						.prepareStatement("INSERT INTO TASK(TASKNAME, COMPLETED, STARRED, "
								+ "PRIORITY, STARTDATE, DUEDATE, PROJECTID, NOTE) "
								+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, taskName);
				ps.setBoolean(2, completed);
				ps.setBoolean(3, starred);
				ps.setInt(4, priority);
				ps.setDate(5, startDate);
				ps.setDate(6, dueDate);
				ps.setInt(7, projectId);
				ps.setString(8, note);
			} else {
				ps = connection
						.prepareStatement("UPDATE PROJECT SET TASKNAME = ?, COMPLETED = ? "
								+ " STARRED = ?, PRIORITY = ?, STARTDATE = ?, DUEDATE = ?, "
								+ " PROJECTID = ?, NOTE = ? WHERE ID = ?");
				ps.setString(1, taskName);
				ps.setBoolean(2, completed);
				ps.setBoolean(3, starred);
				ps.setInt(4, priority);
				ps.setDate(5, startDate);
				ps.setDate(6, dueDate);
				ps.setInt(7, projectId);
				ps.setString(8, note);
				ps.setInt(9, taskId);
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
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement("DELETE FROM TASK WHERE ID = ?");
			prepStatement.setInt(1, id);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	public int count() {
		return countByProjectId(0);
	}

	
	public int countByProjectId(int projectId) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement st = null;
		ResultSet result = null;
		int counter = 0;
		
		try {
			if (projectId == 0) {
				st = connection.prepareStatement("SELECT COUNT(*) FROM TASK");
			} else {
				st = connection.prepareStatement("SELECT COUNT(*) FROM TASK WHERE PROJECTID = ?");
				st.setInt(1, projectId);
			}
			result = st.executeQuery();
			if (result != null) {
				while(result.next()) {
					counter = result.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				st.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return counter;
	}
	
	
	public List<Task> getAll() {
		return getAllByProjectId(0);
	}
	
	
	public List<Task> getAllByProjectId(int projectId) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement st = null;
		List<Task> tasksList = new ArrayList<>();
		Task task = null;
		try {
			if (projectId == 0) {
				st = connection.prepareStatement("SELECT * FROM TASK");
			} else {
				st = connection.prepareStatement("SELECT * FROM TASK WHERE PROJECTID = ?");
				st.setInt(1, projectId);
			}
			ResultSet result = st.executeQuery();
			if (result != null) {
				while (result.next()) {
					task = new Task();
					task.setId(result.getInt("ID"));
					task.setTaskName(result.getString("TASKNAME"));
					task.setCompleted(result.getBoolean("COMPLETED"));
					task.setStarred(result.getBoolean("STARRED"));
					task.setPriority(result.getInt("PRIORITY"));
					task.setStartDate(result.getDate("STARTDATE"));
					task.setDueDate(result.getDate("DUEDATE"));
					task.setProjectId(result.getInt("PROJECTID"));
					task.setNote(result.getString("NOTE"));
					tasksList.add(task);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasksList;
	}
	
	
	public Task findById(int id) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement prepStatement = null;
		Task task = null;
		try {
			prepStatement = connection.prepareStatement("SELECT * FROM TASK WHERE ID = ?");
			prepStatement.setInt(1, id);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					task = new Task();
					task.setId(result.getInt("ID"));
					task.setTaskName(result.getString("TASKNAME"));
					task.setCompleted(result.getBoolean("COMPLETED"));
					task.setStarred(result.getBoolean("STARRED"));
					task.setPriority(result.getInt("PRIORITY"));
					task.setStartDate(result.getDate("STARTDATE"));
					task.setDueDate(result.getDate("DUEDATE"));
					task.setProjectId(result.getInt("PROJECTID"));
					task.setNote(result.getString("NOTE"));
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
		return task;
	}
	
}
