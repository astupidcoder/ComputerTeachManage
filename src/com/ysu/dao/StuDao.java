package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ysu.model.StuInfo;
import com.ysu.util.PageBean;

public class StuDao {

	public StuDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 学生登陆验证
	 * @param con
	 * @param stuInfo
	 * @return
	 * @throws Exception
	 */
	public StuInfo stuLogin(Connection con,StuInfo stuInfo)throws Exception{
		
			String sql="select * from t_stuinfo where userId=? and password=?";
			StuInfo stu=null;
					
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,stuInfo.getUserId());
				pstmt.setString(2,stuInfo.getPassword());
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					stu=new StuInfo();
					stu.setUserId(rs.getInt("userId"));
					stu.setName(rs.getString("name"));
					stu.setPassword(rs.getString("password"));
					stu.setEmail(rs.getString("email"));
					stu.setTel(rs.getString("tel"));
					stu.setScore(rs.getString("score"));
					stu.setStatus(rs.getBoolean("status"));
				}
				return stu;
	}
	/**
	 * 用来更新session,用学号来获取整个学生对象
	 */
	public StuInfo getStuById(Connection con,int userId)throws Exception{
		
		String sql="select * from t_stuinfo where userId=?";
		StuInfo stu=null;
				
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,userId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				stu=new StuInfo();
				stu.setUserId(rs.getInt("userId"));
				stu.setName(rs.getString("name"));
				stu.setPassword(rs.getString("password"));
				stu.setEmail(rs.getString("email"));
				stu.setTel(rs.getString("tel"));
				stu.setScore(rs.getString("score"));
				stu.setStatus(rs.getBoolean("status"));
			}
			return stu;
}
	
	
	/**
	 * 学生注册
	 * 
	 * @param con
	 * @param stu
	 * @return
	 * @throws Exception
	 */
	public boolean stuUpdate(Connection con,StuInfo stu)throws Exception{
		
		String sql="update t_stuinfo set password=?,email=?,tel=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stu.getPassword());
		pstmt.setString(2,stu.getEmail());
		pstmt.setString(3,stu.getTel());
		pstmt.setInt(4,stu.getUserId());
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 学生修改自己的信息
	 * @param con
	 * @param stu
	 * @return
	 * @throws Exception
	 */
public boolean stuModify(Connection con,StuInfo stu)throws Exception{
		
		String sql="update t_stuinfo set tel=?,email=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,stu.getTel());
		pstmt.setString(2,stu.getEmail());
		pstmt.setInt(3,stu.getUserId());
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}

/**
 * 根据学号来更改密码
 */
public boolean pwdModify(Connection con,int userId,String pwd)throws Exception{
	String sql="update t_stuinfo set password=? where userId=?";
	PreparedStatement pstmt=con.prepareStatement(sql);
	pstmt.setString(1,pwd);
	pstmt.setInt(2,userId);
	int result=pstmt.executeUpdate();
	if(result==1) {
		return true;
	}else {
		return false;
	}
			

}

/**
 * 根据学号来给学生输入成绩
 */
public boolean scoreSave(Connection con,int userId,String score)throws Exception{
	String sql="update t_stuinfo set score=? where userId=?";
	PreparedStatement pstmt=con.prepareStatement(sql);
	pstmt.setString(1,score);
	pstmt.setInt(2,userId);
	int result=pstmt.executeUpdate();
	if(result==1) {
		return true;
	}else {
		return false;
	}
			

}
/**
 * 学生注册时判断学号是否存在
 * @param con
 * @param id
 * @param name
 * @return
 * @throws Exception
 */
	public StuInfo isExist(Connection con,int id,String name) throws Exception {
		String sql="select * from t_stuinfo where userId=? and name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2,name);
		StuInfo stu=null;
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			stu=new StuInfo();
			stu.setUserId(rs.getInt("userId"));
			stu.setEmail(rs.getString("email"));
			stu.setName(rs.getString("name"));
			stu.setPassword(rs.getString("password"));
		}
		return stu;
		
	}
	/**
	 * 根据数据库中是否有密码来查询该学生是否注册过
	 * @return
	 */
	public boolean isRegistered(Connection con,int id)throws Exception{
		String sql="select password from t_stuinfo where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
			if(rs.getString(1)==null) {
				return false;
			}else {
			return true;
		}
	}
	/**
	 * 教师查询所有学生的信息
	 * @param con
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public ResultSet studentList(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_stuinfo");
		if(pageBean!=null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	
	/**
	 * 计算学生总数
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int studentCount(Connection con)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_stuinfo");
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else
		return 0;
	}
	/**
	 * 作业提交后更改数据表中学生的作业状态
	 */
	public boolean statusChange(Connection con,int userId)throws Exception{
		String sql="update t_stuinfo set status=1 where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,userId);
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 下载之前判断学生是否已提交作业
	 */
	public boolean isSubmited(Connection con,int userId)throws Exception{
		String sql="select * from t_stuinfo where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,userId);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
			return rs.getBoolean("status");
		
	}
	
	/**
	 * 按班级统计提交作业的同学数目
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet homeworkCount(Connection con)throws Exception{
		String sql="select gradeId,COUNT(*) from t_stuinfo where status=1 group by gradeId";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	
	/**
	 * 统计所有已提交作业提交的学生总数，不按班级划分
	 */
	public int submitCount(Connection con)throws Exception{
		String sql="select COUNT(*) from t_stuinfo WHERE status=1;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
			return rs.getInt(1);
	}
	
	/**
	 * 统计所有已录入成绩的学生总数
	 */
	public int scoreCount(Connection con)throws Exception{
		String sql="SELECT count(*) FROM t_stuinfo WHERE score IS NOT NULL;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
			return rs.getInt(1);
	}
	/**
	 * 按班级统计学生总数
	 * 
	 */
	public ResultSet studentCountByGrade(Connection con)throws Exception{
		String sql="select gradeId,COUNT(*) from t_stuinfo group by gradeId";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	
	
public boolean pwdUpdate(Connection con,String pwd,int userId)throws Exception{
		
		String sql="update t_stuinfo set password=?where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,pwd);
		pstmt.setInt(2,userId);
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}
}
