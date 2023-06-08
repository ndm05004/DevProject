package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface LoginMapper {

	DDITMemberVO idCheck(String memId);

	int signup(DDITMemberVO memberVO);

	DDITMemberVO loginCheck(DDITMemberVO memberVO);

}
