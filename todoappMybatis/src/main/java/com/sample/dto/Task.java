package com.sample.dto;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	private int taskId;
	private int memberId;
	private Date deadline;
	private String taskText;
	private boolean isCompleted;
	private Member member;

	public Task() {

	}

	public Task(int taskId, int memberId, Date deadline, String taskText, boolean isCompleted, Member member) {
		super();
		this.taskId = taskId;
		this.memberId = memberId;
		this.deadline = deadline;
		this.taskText = taskText;
		this.isCompleted = isCompleted;
		this.member = member;
	}
	public Task(int taskId, int memberId, Date deadline, String taskText, boolean isCompleted) {
		super();
		this.taskId = taskId;
		this.memberId = memberId;
		this.deadline = deadline;
		this.taskText = taskText;
		this.isCompleted = isCompleted;

	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int id) {
		this.taskId = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}



	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", memberId=" + memberId + ", deadline=" + deadline + ", text=" + taskText
				+ ", isCompleted=" + isCompleted + ", member=" + member + "]";
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	
}


