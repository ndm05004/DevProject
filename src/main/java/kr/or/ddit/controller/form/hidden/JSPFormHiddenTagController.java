package kr.or.ddit.controller.form.hidden;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/formtag/hidden")
@Slf4j
public class JSPFormHiddenTagController {
	/*
	 *  8장 스프링 폼 태그
	 *  
	 *  12. 숨겨진 필드 요소
	 *  - HTML 숨겨진 필드를 출력하려면 <form:hidden> 요소를 사용한다.
	 */
	
	
	// 1) 모델에 Map타입의 데이터를 생성하여 추가한 후에 화면에 전달한다.
	@RequestMapping(value="/registerFormHiddem01", method = RequestMethod.GET)
	public String registerFormHiddem01(Model model) {
		log.info("registerFormHiddem01()실행...!");
		
		Member member = new Member();
		member.setUserId("hong123");
		member.setUserName("홍길동");
		model.addAttribute("member", member);
				
		return "form/hidden/registerFormHidden01";
	}	
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String registerFormHiddenResult(Model model, Member member) {
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		
		return "form/hidden/result";
	}
	
}
