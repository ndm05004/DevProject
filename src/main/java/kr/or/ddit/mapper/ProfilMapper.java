package kr.or.ddit.mapper;

import kr.or.ddit.vo.DDITMemberVO;

public interface ProfilMapper {

	DDITMemberVO selectMember(DDITMemberVO sessionMember);

	int profileUpdate(DDITMemberVO memberVO);

}
