package com.tasklist;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

public class Task {

	private int id;
	private String taskName;
	private boolean completed;
	private boolean starred;
	private int priority;
	private Date startDate;
	private Date dueDate;
	private int projectId;
	private String note;
	
	public Task() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public boolean isOverdued(){
		boolean response = false;
		if (this.dueDate != null) {
			response = this.dueDate.before(Date.valueOf(LocalDate.now()));
		}
		return response;
	}

	public String stringCutter(String str, int length) {
		char[] array = new char[length];
	    Arrays.fill(array, ' ');
		String space = new String(array);
		if (str.length() >= length) {
			return str.substring(0, length - 3) + "...";
		} else {
			return str + space.substring(0, length - str.length());
		}
	}
	
	
	@Override
	public String toString() {
		return " " + (completed ? "[\u2714]" : "[ ]") + "\t" + "  " + (starred ? "[\u2605]" : "[ ]") + "\t"
				+ (isOverdued() ? "  [!]" : "  [ ]") + "\t" + stringCutter(taskName, 30) + "\t" + priority + "\t" + (startDate != null ? startDate : "[not defined]") + "\t" 
				+ (dueDate != null ? dueDate : "[not defined]") + "\t";
	}
	
	
	
}
