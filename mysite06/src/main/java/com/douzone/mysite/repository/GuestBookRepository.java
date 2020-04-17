package com.douzone.mysite.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;



@Repository
public class GuestBookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestBookVo> findAll(){
		List<GuestBookVo> result = new ArrayList<GuestBookVo>();
		
		return sqlSession.selectList("guestbook.findAll" , result);
	}
	
	public int insert(GuestBookVo vo) {
		return sqlSession.insert("guestbook.insert", vo);	
	}
	
	public int deleteMap(Long no, String password) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("guestbook.delete", map);	
	}
	
	public int delete( GuestBookVo vo ) {
		return sqlSession.delete( "guestbook.delete2", vo );
	}

	public List<GuestBookVo> findAll(Long startNo) {
			return sqlSession.selectList("guestbook.findAllByNo" , startNo);
	}
	
	/*
	public Boolean delete(GuestBookVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
	
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			throw new GuestBookRepositoryException(e.getMessage());
		} finally {
			//6. 자원정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	*/
}
