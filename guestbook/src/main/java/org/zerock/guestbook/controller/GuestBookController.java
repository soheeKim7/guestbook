package org.zerock.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.repository.GuestBookRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("guestbook")   
@Log4j2
public class GuestBookController {
	
	@Autowired
	GuestBookRepository guestBookRepository;
	
	@GetMapping({"/","list"})   // 여기서 /의미는 guestbook의 폴더 안에!! 들어온거의 의미! 
								//그래서 guestbook 과 guestbook/ 의미는 달라
	public String list() {
		log.info("리스트 페이지 요청");
		return "/guestbook/list";
	}
	
	@GetMapping("quiz1")
	public void quiz1() {
		
	}
	
	@PostMapping("result1")
	public void result1(int page,int amount,Model model) {
		Pageable pageable= PageRequest.of(page-1, amount,Sort.by("gno").descending()); //10개씩 봤을때 첫페이지		
		Page<GuestBook> list= guestBookRepository.findAll(pageable);
		model.addAttribute("list",list);
	}
	
}
