package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo findAll() {
		return sqlSession.selectOne("site.select");
	}
	
	public int update(SiteVo siteVo) {
		return sqlSession.update("site.update", siteVo);
	}
	
}
