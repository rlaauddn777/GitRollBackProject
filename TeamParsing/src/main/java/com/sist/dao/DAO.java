package com.sist.dao;

import java.sql.*;
import com.sist.db.*;


public class DAO {
//	private Connection conn;
//	private PreparedStatement ps;
//	private final String URL="jdbc:oracle:thin:@10.211.55.5:1521:xe";
//	private final String USERNAME="hr";
//	private final String PASSWORD="happy";
//	
//	
//	// 1. �뱶�씪�씠踰� �벑濡� => �븳踰덈쭔 �닔�뻾 => �깮�꽦�옄
//	public DAO()
//	{
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (Exception e) {}
//	}
//	// 2. �뜲�씠�꽣踰좎씠�뒪 �뿰寃�
//	public void getConnection()
//	{
//		try {
//			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			// conn hr/happy
//		} catch (Exception e) {}
//	}
//	
//	// 3. �뜲�씠�꽣踰좎씠�뒪 �빐�젣
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	Connection conn=null;
	PreparedStatement ps=null;
	
	public void dataInsert(VO v)
	{
		try {
			conn=new DBConnect().getConn();
			String sql="INSERT INTO ORD_4 "
					+ "VALUES ((SELECT NVL(MAX(o_no)+1,1) FROM ORD_4),"
					+ "?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
	//		ps.setInt(1,  v.getO_no());
			ps.setString(1, v.getName());
			ps.setString(2, v.getPrice());
			ps.setString(3, v.getStore());
			ps.setString(4, v.getS_info());
			ps.setString(5, v.getPoster());
			ps.setString(6, v.getImg());
			ps.setString(7, v.getAddress());
			
			ps.executeUpdate();
		} catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally {
			disConnection();
		}
	}
}
