package com.tasklist.dao.jdbcMysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tasklist.dao.DbUtil;
import com.tasklist.dao.TaskDao;
import com.tasklist.domain.Task;

public class TaskDaoImpl implements TaskDao {

	public TaskDaoImpl() {
	}

	public void save(Task task) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement ps = null;
		int taskId = task.getId();
		String taskName = task.getTaskName();
		//cutting for proper saving in database
		if (taskName != null && taskName.length() > 128) {
			taskName = taskName.substring(0, 128);
		}
		boolean completed = task.isCompleted();
		boolean starred = task.isStarred();
		int priority = task.getPriority();
		Date startDate = task.getStartDate();
		Date dueDate = task.getDueDate();
		int projectId = task.getProjectId();
		String note = task.getNote();
		//cutting for proper saving in database
		if (note != null && note.length() > 256) {
			note = note.substring(0, 256);
		}

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
						.prepareStatement("UPDATE TASK SET TASKNAME = ?, COMPLETED = ?, "
								+ "STARRED = ?, PRIORITY = ?, STARTDATE = ?, DUEDATE = ?, "
								+ "PROJECTID = ?, NOTE = ? WHERE ID = ?");
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
				st = connection.prepareStatement("SELECT COUNT(*) FROM TASK WHERE COMPLETED = FALSE");
			} else {
				st = connection.prepareStatement("SELECT COUNT(*) FROM TASK WHERE "
												+ "PROJECTID = ? AND COMPLETED = FALSE");
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
	
	
	public List<Task> getAll(boolean completed) {
		return getAllByProjectId(0, completed);
	}
	
	
	public List<Task> getAllByProjectId(int projectId, boolean completed) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement st = null;
		List<Task> tasksList = new ArrayList<>();
		Task task = null;
		try {
			if (projectId == 0) {
				st = connection.prepareStatement("SELECT * FROM TASK WHERE COMPLETED = ? ORDER BY -DUEDATE DESC, "
												+ "PRIORITY DESC, STARRED DESC, TASKNAME");
				st.setBoolean(1, completed);
			} else {
				st = connection.prepareStatement("SELECT * FROM TASK WHERE COMPLETED = ? PROJECTID = ? "
									+ "ORDER BY -DUEDATE DESC, PRIORITY DESC, STARRED DESC, TASKNAME");
				st.setBoolean(1, completed);
				st.setInt(2, projectId);
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
				e.printStackTrace();
			}
		}
		return task;
	}
	
}
