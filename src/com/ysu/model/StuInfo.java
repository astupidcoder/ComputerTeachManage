package com.ysu.model;

public class StuInfo {

	private int userId;
	private boolean status;
	private String name;
	private int gradeId;
	private String score;
	private String password;
	private String tel;
	private String email;
	public StuInfo(int userId, String name, String password, String tel, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.email = email;
	}
	
	public StuInfo(int userId, String tel, String email) {
		super();
		this.userId = userId;
		this.tel = tel;
		this.email = email;
	}
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public StuInfo(int userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StuInfo() {
		// TODO Auto-generated constructor stub
	}

}
