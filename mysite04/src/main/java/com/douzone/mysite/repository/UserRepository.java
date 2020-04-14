package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo); //처음엔 vo에 값이 없다가 24줄의 vo가 끝나면 
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo); //하나를 부르는데 list쓰면 에러 One 써야한다.	
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		//new UserVo 해서 하면 되는데 vo를 쓰기 싫으면 Map을 만든다.
		
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password); //예제니까 e,p를 썼지만 full name을 쓰자
		return sqlSession.selectOne("user.findByEmailAndPassword2", map); //하나를 부르는데 list쓰면 에러 One 써야한다.	
	}

	public UserVo findByNo(Long no) { //User inform update
		return sqlSession.selectOne("user.findByNo", no);
	}
	
	public UserVo comparePassword(Long no) { //User inform update
		return sqlSession.selectOne("user.comparePassword", no);
	}


	public int update(UserVo vo) {
		return sqlSession.update("user.update", vo);
	}
	
	public UserVo find(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}

}
