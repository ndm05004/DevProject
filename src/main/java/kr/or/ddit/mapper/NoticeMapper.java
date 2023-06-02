package kr.or.ddit.mapper;

import kr.or.ddit.vo.NoticeVO;

public interface NoticeMapper {

	int insertNotice(NoticeVO notice);

	NoticeVO selectNotice(int boNo);

	void increamentHit(int boNo);

	int updateNotice(NoticeVO noticeVO);

}
