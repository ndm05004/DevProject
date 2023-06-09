package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.NoticeFileVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface NoticeMapper {

	int insertNotice(NoticeVO notice);

	NoticeVO selectNotice(int boNo);

	void increamentHit(int boNo);

	int updateNotice(NoticeVO noticeVO);

	int deleteNotice(int boNo);

	int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);

	List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);

	void insertNoticeFile(NoticeFileVO noticeFileVO);

	NoticeFileVO noticeDownload(int fileNo);

	void increamenNoticeDownCount(int fileNo);

	NoticeFileVO selectNoticeFile(Integer integer);

	void deleteNoticeFile(Integer integer);

	void deleteNoticeFileByBoNo(int boNo);

}
