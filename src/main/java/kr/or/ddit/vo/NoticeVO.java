package kr.or.ddit.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;
	private int boHit;
	
	private Integer[] delNoticeNo;
	private MultipartFile[] boFile;
	/* private List<NoticeFileVO> noticeFileList; */

}
