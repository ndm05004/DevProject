package kr.or.ddit.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.MemberAuth;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberService implements IMemberService {
	
	@Inject
	MemberMapper mapper;
	
	@Override
	public void register(MemberVO member) {
		mapper.register(member);
		
		MemberAuth memberAuth = new MemberAuth();
		
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER");
		
		mapper.createAuth(memberAuth);
	}

	@Override
	public List<MemberVO> list() {
		// TODO Auto-generated method stub
		return mapper.list();
	}

	@Override
	public MemberVO read(int userNo) {
		// TODO Auto-generated method stub
		return mapper.read(userNo);
	}

}
