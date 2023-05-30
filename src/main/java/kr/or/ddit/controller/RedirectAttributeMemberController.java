package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/redirectattribute")
public class RedirectAttributeMemberController {
	
	/*
	 *  4. RedirectAttribute 타입
	 *  - RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용
	 *  
	 */
	
	@RequestMapping(value = "/registerForm", method= RequestMethod.GET)
	public String registerForm() {
		return "member/registerRedirectAttributeForm";
	}
	
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(Member member, RedirectAttributes redirectAttribute) {
		log.info("register() 실행...!");
		redirectAttribute.addFlashAttribute("msg", "success");
		return "redirect:/redirectattribute/result";
	}
	
	@RequestMapping(value="/result", method = RequestMethod.GET)
	public String result() {
		log.info("result() 실행...!");
		return "result";
	}
	
	
	
}
