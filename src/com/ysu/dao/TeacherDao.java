package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ysu.model.StuInfo;
import com.ysu.model.TeaInfo;

public class TeacherDao {


	public TeacherDao() {
		// TODO Auto-generated constructor stub
	}

	
	public TeaInfo teaLogin(Connection con,TeaInfo teaInfo) throws Exception{
		TeaInfo tea=null;
		String sql="select * from t_teacherinfo where userId=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,teaInfo.getUserId());
		pstmt.setString(2,teaInfo.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			tea=new TeaInfo();
			tea.setUserId(rs.getInt("userId"));
			tea.setPassword(rs.getString("password"));
			tea.setEmail(rs.getString("email"));
			tea.setName(rs.getString("name"));
			tea.setTel(rs.getString("tel"));
		}
		return tea;
	}
	
public boolean teaUpdate(Connection con,TeaInfo tea)throws Exception{
		
		String sql="update t_teacherinfo set email=?,tel=?,name=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,tea.getEmail());
		pstmt.setString(2,tea.getTel());
		pstmt.setString(3, tea.getName());
		pstmt.setInt(4,tea.getUserId());
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}

public TeaInfo getTeaById(Connection con,int userId)throws Exception{
	
	String sql="select * from t_teacherinfo where userId=?";
	TeaInfo Tea=null;
			
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,userId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			Tea=new TeaInfo();
			Tea.setUserId(rs.getInt("userId"));
			Tea.setName(rs.getString("name"));
			Tea.setPassword(rs.getString("password"));
			Tea.setEmail(rs.getString("email"));
			Tea.setTel(rs.getString("tel"));
		}
		return Tea;
}
	
}
