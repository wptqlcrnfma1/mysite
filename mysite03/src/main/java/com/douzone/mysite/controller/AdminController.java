package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		
		SiteVo siteVo = siteService.getContents();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(@RequestParam(value="file") MultipartFile multipartFile,@ModelAttribute SiteVo siteVo,
			Model model) {
		
		String profile = siteService.restore(multipartFile);
		siteVo.setProfile(profile);
		
		siteService.update(siteVo);
		
		return "admin/main";
	}
	
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
