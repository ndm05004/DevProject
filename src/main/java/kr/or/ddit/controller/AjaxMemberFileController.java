package kr.or.ddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxMemberFileController {
	
	
	//@PostMapping(value="/registerFileForm") // method = RequestMethod.POST가 포함되어 있는 어노테이션
	//@RequestMapping(value="/registerFileForm", method = RequestMethod.GET)
	@GetMapping(value="/registerFileForm") // method = RequestMethod.GET가 포함되어 있는 어노테이션
	public String ajaxRegisterFileForm() {
		
		return "member/ajaxRegisterFile";
	}
	//produces 속성을 이용했을때 return이 text로 나감
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file){
		String originalfileName = file.getOriginalFilename();
		log.info("uploadAjax() 실행...!");
		log.info("originalfileName : " + originalfileName);
		return new ResponseEntity<String>("UPLOAD SUCCESS", HttpStatus.OK);
	}
}
