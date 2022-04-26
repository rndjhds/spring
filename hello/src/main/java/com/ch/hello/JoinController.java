package com.ch.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JoinController {
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute Member member, Model model) {
		System.out.println("id:"+member.getId());
		System.out.println("pass:"+member.getPass());
		
		model.addAttribute("member", member);
		return "joinResult";
	}
}