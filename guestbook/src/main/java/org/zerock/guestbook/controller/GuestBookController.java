package org.zerock.guestbook.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("guestbook")   
@Log4j2
public class GuestBookController {
	
	@GetMapping({"/","list"})   // 여기서 /의미는 guestbook의 폴더 안에!! 들어온거의 의미! 
								//그래서 guestbook 과 guestbook/ 의미는 달라
	public String list() {
		log.info("리스트 페이지 요청");
		return "/guestbook/list";
	}
	
	
}
