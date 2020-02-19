package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserRepository {

	public Boolean insert(UserVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// insert into emaillist values (null, '김', '정석', 'zozfd@daum.net');
			String sql = "insert into user values(null, ?, ?, ?,?,now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.1.112:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;

	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, name " + " from user " + " where email = ? and password = ?  "
					+ " order by no desc";
			pstmt = conn.prepareStatement(sql);

			// binding 작업
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());

			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				userVo = new UserVo(); // 있으면 생성하니까 null이 안된다.

				userVo.setNo(no);
				userVo.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userVo;
	}

	public UserVo findByNo(Long no) {

		UserVo userVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select no, name, email, password, gender from user where no = ?";
			pstmt = conn.prepareStatement(sql);

			// binding 작업
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			if (rs.next()) {
				Long num = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String gender = rs.getString(5);

				userVo = new UserVo(); // 있으면 생성하니까 null이 안된다.

				userVo.setNo(num);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setPassword(password);
				userVo.setGender(gender);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}

	public Boolean update(UserVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// update user set password = '1234', name = '김정석' where no = 1;
			String sql = "update user set name = ? ,password = ?, gender =? where no = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());

			pstmt.setLong(4, vo.getNo());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 6. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
