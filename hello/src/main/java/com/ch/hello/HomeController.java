package com.ch.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@RequestMapping("/color") //  method = RequestMethod.GET는 GET방식으로 전송 이지만 GET방식은 기본 방식이라서 생략 가능 
	public String color(Model model) {
		String[] color = { "red", "orange", "yellow", "green", "blue", "navy", "violet" };
		int num = (int) (Math.random() * 7);
		model.addAttribute("color", color[num]);
		return "color";
		// return 할때는 view페이지의 최상위 디렉토리 경로 빼고 확장자를 뺴서 써주면 된다.
	}

	@RequestMapping("/gugu")
	public String gugu(Model model) {
		int num = (int) (Math.random() * 8) + 2;
//		int a = num / 0;
		model.addAttribute("num", num);
		return "gugu";
	}
	/*
	 * @ExceptionHandler(ArithmeticException.class) 
	 * public String err() { 
	 * return
	 * "arr-err"; }
	 */
}