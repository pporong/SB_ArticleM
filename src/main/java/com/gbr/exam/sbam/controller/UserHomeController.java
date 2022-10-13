package com.gbr.exam.sbam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	
	@RequestMapping("user/home/main")
	@ResponseBody
	public String getString() {
		return "hi";

	}
}