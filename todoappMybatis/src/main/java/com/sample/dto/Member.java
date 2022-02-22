package com.sample.dto;

import java.io.Serializable;
import java.util.List;

public class Member implements Serializable{
	
	private int memberId;
	private String name;
	private String password;
	private List<Task> myTaskList;
	
	

	public Member() {
		super();
	}

	public Member(int memberId, String name, String password, List<Task> myTaskList) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.myTaskList = myTaskList;
	}
	
	public Member(int memberId, String name, String password) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
	}
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [id=" + memberId + ", name=" + name + ", password=" + password + "]";
	}

	public List<Task> getMyTaskList() {
		return myTaskList;
	}

	public void setMyTaskList(List<Task> myTaskList) {
		this.myTaskList = myTaskList;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	

}
