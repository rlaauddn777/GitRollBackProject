package com.sist.dao;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import org.apache.tomcat.jdbc.pool.DisposableConnectionFacade;

import java.sql.*;
import com.sist.conn.*;

import oracle.jdbc.logging.annotations.DisableTrace;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBConnection dbconn=DBConnection.newInstance();
	public List<CategoryVO> categoryListData(){
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			conn=dbconn.getConnection();
			String sql="select /*+ INDEX_ASC(food_category pk_food_category)*/ "
			+ "cno,title,subject,poster "
			+"from food_category ";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo = new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dbconn.disConnection(ps);
		}
		return list;
	}
}
