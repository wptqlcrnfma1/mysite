package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@RestController("guestBookApiController")
@RequestMapping("/api/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestbookService;
	
	@GetMapping("/list/{startNo}")
	public JsonResult list(@PathVariable("startNo") Long startNo ) {
		List<GuestBookVo> list = guestbookService.getMessageList(startNo);
		return JsonResult.success(list);
	}
	
	
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		System.out.println("add called : " + vo );
		guestbookService.insert(vo);
		vo.setPassword(""); //보안 상 비밀번호 숨기기
		return JsonResult.success(vo);
	}
	
	//자동으로 문서화하는것 알아보기
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no,
			@RequestParam(value="password", required =true, defaultValue="") String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		
		//System.out.println(no+":"+password);
		
		return JsonResult.success(result ? no : -1);
	}
	
	
	
}
