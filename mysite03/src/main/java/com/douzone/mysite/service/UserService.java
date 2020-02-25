package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean join(UserVo vo) {
		boolean count = userRepository.insert(vo);
		return count == true;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no); //사용자 정보 찾기
	}

	public Boolean update(UserVo vo) {
		boolean count = userRepository.update(vo);
		return count == true;
	}
	
}
