package kr.or.ddit.controller.noticeboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.web.TelegramSendController;
import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.mapper.NoticeMapper;
import kr.or.ddit.mapper.ProfilMapper;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private NoticeMapper noticeMapper;
	
	@Inject
	private LoginMapper loginMapper;
	
	@Inject
	private ProfilMapper profileMapper;
	
	
	TelegramSendController tst = new TelegramSendController();
	
	@Override
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO notice) {
		ServiceResult result = null;
		int status = noticeMapper.insertNotice(notice);
		
		if(status > 0) {
			List<NoticeFileVO> noticeFileList = notice.getNoticeFileList();
			
			try {
				noticeFileUpload(noticeFileList, notice.getBoNo(), req);
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// 성공 했다는 메시지를 텔레그램 BOT API를 이용하여 알림!
			try {
				tst.sendGet("쉬는시간", notice.getBoTitle());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	private void noticeFileUpload(List<NoticeFileVO> noticeFileList, int boNo, HttpServletRequest req) throws IllegalStateException, IOException {
		String savePath = "/resources/notice/";
		
		if(noticeFileList != null && noticeFileList.size() > 0) {
			for(NoticeFileVO noticeFileVO : noticeFileList) {
				String saveName = UUID.randomUUID().toString();
				
				// 파일명을 설정할 때 원본 파일명의 공백을 '_'로 변경한다.
				saveName = saveName + "_" + noticeFileVO.getFileName().replaceAll(" ", "_");
				String endFileName = noticeFileVO.getFileName().split("\\.")[1]; // 확장자 (디버깅 및 확장자 추출 참고)
				
				// /resources/notice/bono
				String saveLocate = req.getServletContext().getRealPath(savePath+boNo);
				File file = new File(saveLocate);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				saveLocate += "/" + saveName;
				
				File saveFile = new File(saveLocate);
				
				noticeFileVO.setBoNo(boNo);
				noticeFileVO.setFileSavepath(saveLocate);
				noticeMapper.insertNoticeFile(noticeFileVO);
				
				noticeFileVO.getItem().transferTo(saveFile); // 파일 복사
				
			}
		}
		
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.increamentHit(boNo); // 조회수 증가
		NoticeVO noticeVO = noticeMapper.selectNotice(boNo);
		
		return noticeVO;
	}

	@Override
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(noticeVO);
		if(status > 0) {
			
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			try {
				// 1. 수정을 위해서 새로 추가된 파일 데이터를 먼저 업로드 처리한다.
				noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);
				// 2. 기존 추가되었던 파일들 중, 삭제를 원하는 파일 번호들을 가져온다.
				Integer[] delNoticeNo = noticeVO.getDelNoticeNo();
				if(delNoticeNo != null) {
					// 3. 삭제를 원하는 파일 번호 하나하나씨기을 데이터베이스로 던져서 파일 정보를 얻어 온 다음에
					//	  데이터베이스에서 데이터를 삭제하고 서버 업로드 경로에 업로드 되어있는 파일 데이터를 삭제한다.
					for (int i =0; i < delNoticeNo.length; i++) {
						NoticeFileVO noticeFileVO = noticeMapper.selectNoticeFile(delNoticeNo[i]);
						noticeMapper.deleteNoticeFile(delNoticeNo[i]);
						File file = new File(noticeFileVO.getFileSavepath());
						file.delete();
					}
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo) {
		ServiceResult result = null;
		
		// 아래에서 boNo에 해당하는 파일 데이터를 삭제할때 사용할 객체(noticeVO)
		NoticeVO noticeVO =  noticeMapper.selectNotice(boNo);
		// 본체인 notice를 삭제하기 전에 File 데이터를 먼저 삭제하기 위한 요청
		noticeMapper.deleteNoticeFileByBoNo(boNo);
		
		int status = noticeMapper.deleteNotice(boNo);
		if(status > 0) {
			// 파일 데이터를 완벽하게 삭제처리하면 끝
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			
			// D:\A_TeachingMaterial\02.SPRING2\workspace_spring2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DevProject\resources\notice\50/5ef41c74-95ff-4e15-87f2-93f95e34d41a_KakaoTalk_20230207_155047298.jpg
			// 5ef41c74-95ff-4e15-87f2-93f95e34d41a_KakaoTalk_20230207_155047298.jpg
			// '/'를 기준으로 자른다.
			if(noticeFileList != null && noticeFileList.size() > 0) {
				String[] filePath =  noticeFileList.get(0).getFileSavepath().split("/");
				
				// \resources\notice\{bo_no}/... 형태를 알고 있기에 filePath[0]로 설정 가능
				String path = filePath[0];
				deleteFolder(req, path);
			}
			
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	public static void deleteFolder(HttpServletRequest req, String path) {
		File folder = new File(path);
		if(folder.exists()) {
			File[] fileList = folder.listFiles();
			
			for(int i =0; i < fileList.length; i++) {
				if(fileList[i].isFile()) {	// 폴더안의 파일이 이 파일일때
					// 폴더 안에 파일을 차례대로 삭제
					fileList[i].delete();
				}else { 					// 폴더안에 있는 파일이 폴더 일때 재귀함수 호출(폴더 안으로 들어갈라고)
					deleteFolder(req, fileList[i].getPath());
				}
			}
			folder.delete();	// 폴더 삭제(boNo에 해당하는 폴더를 삭제)
		}
		
		
	}

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {

		return noticeMapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeList(pagingVO);
	}

	@Override
	public ServiceResult idCheck(String memId) {
		
		ServiceResult result =null;
		
		DDITMemberVO member = loginMapper.idCheck(memId);
		if(member != null) {
			result = ServiceResult.EXIST;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

	@Override
	public ServiceResult signup(HttpServletRequest req, DDITMemberVO memberVO) {
		
		ServiceResult result =null;
		
		// 프로필 이미지 파일을 업로드할 경로 설정
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg = ""; // memberVO안에 프로필 이미지 경로를 담을 변수
		MultipartFile proFileImgFile = memberVO.getImgFile();
		
		if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + proFileImgFile.getOriginalFilename();
			uploadPath += "/" + fileName;
			try {
				proFileImgFile.transferTo(new File(uploadPath)); // 파일 복사
			}catch(IllegalStateException | IOException e ) {
				e.printStackTrace();
			}

			proFileImg = "/resources/profile/" + fileName;
		}
		
		memberVO.setMemProfileimg(proFileImg);
		
		int status = loginMapper.signup(memberVO);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public DDITMemberVO loginCheck(DDITMemberVO memberVO) {
		return loginMapper.loginCheck(memberVO);
	}

	@Override
	public String idForgetProcess(DDITMemberVO member) {

		return loginMapper.idForgetProcess(member);
	}

	@Override
	public String pwForgetProcess(DDITMemberVO member) {

		return loginMapper.pwForgetProcess(member);
	}

	@Override
	public NoticeFileVO noticeDownload(int fileNo) {
		
		NoticeFileVO noticeFileVO = noticeMapper.noticeDownload(fileNo);
		
		if(noticeFileVO == null) {
			throw new RuntimeException();
		}
		noticeMapper.increamenNoticeDownCount(fileNo);
		return noticeFileVO;
	}

	@Override
	public DDITMemberVO selectMember(DDITMemberVO sessionMember) {
		return profileMapper.selectMember(sessionMember);
	}

	@Override
	public ServiceResult profileUpdate(HttpServletRequest req, DDITMemberVO memberVO)  {
		
		ServiceResult result =null;
		
		// 사용자가 수정한 프로필 이미지 정보에 따라서 프로필 이미지 정보 값을 설정 후 memberVO에 셋팅해서 전달한다.
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg ="";
		MultipartFile proFileImgFile = memberVO.getImgFile();
		if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
			String fileName = UUID.randomUUID().toString();
			fileName += "_" + proFileImgFile.getOriginalFilename();
			uploadPath += "/" + fileName;
			try {
				proFileImgFile.transferTo(new File(uploadPath));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 프로필 이미지 복사
			proFileImg = "/resources/profile/" + fileName;
		}
		
		// 선택한 프로필 이미지가 존재하지 않으면 "" 공백이 넘어가고
		// 프로필 이미지가 존재하면 업로드 경로와 파일명으로 구성된 경로가 넘어간다.
		memberVO.setMemProfileimg(proFileImg);
		
		int status = profileMapper.profileUpdate(memberVO);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}


}
