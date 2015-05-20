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

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.exception.GuestbookDaoException;

@Repository
public class BoardDao {
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

	public void insert(BoardVo vo) {

		try {
			// 1 connection 생성
			Connection conn = getConnection();

			// 2 Statement 준비

			String sql = "insert into board values(board_no_seq.nextval,?,?,?,?,0,sysdate)";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMemberno());
			pstmt.setString(4, vo.getMembername());
			// pstmt.setLong(5, vo.getViewcnt());

			// 4 쿼리실행
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}

	}

	public void delete(BoardVo vo) {

		try {
			Connection conn = getConnection();

			String sql = "delete from board where NO=? and Member_no=?";

			// statement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getMemberno());

			pstmt.executeUpdate();

			// 자원정리

			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
	}

	public void Update(BoardVo vo) {

		try {
			Connection conn = getConnection();

			String sql = "update board set title=?, content=? where NO=? and Member_no=?";

			// statement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			pstmt.setLong(4, vo.getMemberno());

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
	}

	public void UpdateViewcnt(BoardVo vo) {

		try {

			Connection conn = getConnection();

			String sql = "update board set view_cnt=view_cnt+1 where NO=?";

			// statement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
	}

	public BoardVo View(BoardVo vo) {

		BoardVo vo2 = new BoardVo();
		try {

			Connection conn = getConnection();

			String sql = "select * from board where no=?";
			// 3 statement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4 SQL문 실행
			pstmt.setLong(1, vo.getNo());

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			// 5 결과 처리

			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			Long memberno = rs.getLong(4);
			String membername = rs.getString(5);
			Long viewcnt = rs.getLong(6);
			String date = rs.getString(7);

			vo2.setNo(no);
			vo2.setTitle(title);
			vo2.setContent(content);
			vo2.setMemberno(memberno);
			vo2.setMembername(membername);
			vo2.setViewcnt(viewcnt);
			vo2.setDate(date);

			rs.close();
			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
		return vo2;

	}

	public List<BoardVo> findList(String kwd) {

		List<BoardVo> findlist = new ArrayList<BoardVo>();

		try {
			Connection conn = getConnection();

			String sql = "select * from board where title like ?";

			// 3 statement 생성
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 4 SQL문 실행
			String newtitle = "%" + kwd + "%";

			pstmt.setString(1, newtitle);

			ResultSet rs = pstmt.executeQuery();

			// 5 결과 처리
			while (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long memberno = rs.getLong(4);
				String membername = rs.getString(5);
				Long viewcnt = rs.getLong(6);
				String date = rs.getString(7);

				BoardVo vo2 = new BoardVo();
				vo2.setNo(no);
				vo2.setTitle(title);
				vo2.setContent(content);
				vo2.setMemberno(memberno);
				vo2.setMembername(membername);
				vo2.setViewcnt(viewcnt);
				vo2.setDate(date);

				findlist.add(vo2);

			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
		return findlist;
	}

	public List<BoardVo> fetchList() {
		List<BoardVo> list = new ArrayList<BoardVo>();

		try {

			Connection conn = getConnection();

			// 3 statement 생성
			Statement stmt = conn.createStatement();

			// 4 SQL문 실행
			String sql = "select * from board";

			ResultSet rs = stmt.executeQuery(sql);

			// 5 결과 처리
			while (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long memberno = rs.getLong(4);
				String membername = rs.getString(5);
				Long viewcnt = rs.getLong(6);
				String date = rs.getString(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setMemberno(memberno);
				vo.setMembername(membername);
				vo.setViewcnt(viewcnt);
				vo.setDate(date);

				list.add(vo);

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new GuestbookDaoException(ex.getMessage());
		}
		return list;
	}

}
