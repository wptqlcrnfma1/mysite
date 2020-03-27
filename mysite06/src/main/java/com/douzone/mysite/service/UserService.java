package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public int join(UserVo vo) {
		int count = userRepository.insert(vo);
		return count;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no); //사용자 정보 찾기
	}

	public int update(UserVo vo) {
		int count = userRepository.update(vo);
		return count;
	}
	
	public UserVo getPassword(Long no) {
		return userRepository.comparePassword(no);
	}
	
}
