package com.ysu.model;

public class TeaInfo {

	private int userId;
	private String name;
	private String password;
	private String tel;
	private String email;
	
		public TeaInfo(int userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
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

	public void setName(String name) {
		this.name = name;
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

		public TeaInfo() {
		// TODO Auto-generated constructor stub
	}

		public TeaInfo(int userId, String name, String tel, String email) {
			super();
			this.userId = userId;
			this.name = name;
			this.tel = tel;
			this.email = email;
		}
		

}
