package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		int a = 10;
		int b = 90;
		return "this is testing and sum of a and b : "+( a + b );
	}
}
