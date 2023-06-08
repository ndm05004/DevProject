package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.file.item03.UploadFileUtils;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO notice, Model model) {
		String goPage="";
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(notice.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if(StringUtils.isBlank(notice.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", notice);
			goPage = "notice/form";
		}else {
			notice.setBoWriter("a001");
			ServiceResult result = noticeService.insertNotice(notice);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?boNo="+notice.getBoNo();
			}else {
				model.addAttribute("message", "서버에러, 다시 시도해주세요!");
				goPage = "notice/form";
			}
			
			
		}
		return goPage;
	}
	
	// 요청 URI : /notice/generalForm
	// 요청방식 : get
	
	@GetMapping("/generalForm")
	public String generalForm() {
		//forwarding
		return "notice/generalForm";
	}
	
	/* 
	       요청URI : /notice/generalFormPost                                        
		요청방식 : post                                                            
		요청 파라미터 : {boTitle = 제목입니다, boContent=내용입니다. boWriter=작성자, boFile=파일객체}
		
		@@ResponseBody : json이지만 텍스트로 리턴해줌
	 */
	@ResponseBody
	@RequestMapping("/generalFormPost")
	public String generalFormPost(NoticeVO notice) {
		log.info("notice : " + notice);
		
		//  파일을 업로드할 대상
		String uploadFolder = "D:\\A_TeachingMaterial\\02.SPRING2\\workspace_spring2\\DevProject\\src\\main\\webapp\\resources\\upload";
		MultipartFile[] bofile = notice.getBoFile();
		
		// 파일 배열 객체로부터 파일을 하나씩 추출
		for(MultipartFile mulfile : bofile) {
			log.info("----------------------------------------------");
			log.info("upload file name : " + mulfile.getOriginalFilename());
			log.info("upload file size : " + mulfile.getSize());
			log.info("upload file contentType : " + mulfile.getContentType());
			
			// File객체 복사 설계
			File saveFile = new File(uploadFolder, mulfile.getOriginalFilename());
			
			// 연/월/일 폴더 생성
			
			// 파일 복사 실행(파일원본.transeferTo(설계)
			try {
				mulfile.transferTo(saveFile);
				return "Success";
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Failed";
			}
			
		}
		this.noticeService.insertNotice(notice);
		
		return "Success";
	}
	
	
	
	
	
	
}
