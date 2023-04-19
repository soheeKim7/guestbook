package org.zerock.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("layout/basic")
	public String layoutTest() {
		return "layout/layoutTest";
	}

}
