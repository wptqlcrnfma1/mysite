package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping("") 
	public String index(Model model) {
		guestbookService.findAll(model);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String join(GuestBookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model,@PathVariable("no") Long no) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{no}",method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value = "password", required=true, defaultValue="" ) String password) {
		guestbookService.delete(no,password);
		return "redirect:/board";
	}
	
	

}
