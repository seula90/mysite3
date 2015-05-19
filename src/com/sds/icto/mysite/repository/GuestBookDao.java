package com.sds.icto.mysite.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.GuestBookVo;
import com.sds.icto.mysite.exception.GuestbookDaoException;




@Repository
public class GuestBookDao {
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;

		// 1 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2 connection 생성
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "webdb", "webdb");

		return conn;
	}

	public void insert(GuestBookVo vo) {
		try{
		
		
		// 1 connection 생성
		Connection conn = getConnection();

		// 2 Statement 준비
		
		String sql = "insert into GUESTBOOK values(guestbook_seq.nextval, ?,?,?, sysdate)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 3 바인딩
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getContent());
		
		
		// 4 쿼리실행
		pstmt.executeUpdate();
		
		}catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}	
	}

	
	public void delete(GuestBookVo vo) {
		
		try{
		
		Connection conn = getConnection();
		
		String sql = "delete from GUESTBOOK where NO=? and PASSWORD=?";

		// statement 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getPassword());
		
		pstmt.executeUpdate();
		
		// 자원정리

			pstmt.close();
			conn.close();
		
		}catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}

	}

	public List<GuestBookVo> fetchList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		try{

		Connection conn = getConnection();

		// 3 statement 생성
		Statement stmt = conn.createStatement();

		// 4 SQL문 실행
		String sql = "select * from guestbook";

		ResultSet rs = stmt.executeQuery(sql);

		// 5 결과 처리
		while (rs.next()) {
			Long no = rs.getLong(1);
			String nm = rs.getString(2);
			String msg = rs.getString(4);
			

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setName(nm);
			vo.setContent(msg);
			
			
			list.add(vo);
		}
		}catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}

		return list;
	}
}
