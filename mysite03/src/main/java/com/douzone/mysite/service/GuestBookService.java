package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookRepository guestbookRepository;
	

	public List<GuestBookVo> findAll(Model model) {
		List<GuestBookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return list;
	}
	
	
	public boolean insert(GuestBookVo vo) {
		boolean count = guestbookRepository.insert(vo);
		return count == true;
	}
	
	public String delete(Long no, String password) {
		guestbookRepository.delete(no,password);
		
		return "redirect:/";
	}
	
}
