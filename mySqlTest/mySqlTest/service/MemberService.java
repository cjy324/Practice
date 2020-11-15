package mySqlTest.service;

import mySqlTest.container.Container;
import mySqlTest.dao.MemberDao;
import mySqlTest.dto.Member;

public class MemberService {

	MemberDao memberDao;
	
	public MemberService() {
		
		memberDao = Container.memberDao;
	}
	
	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId,loginPw,name);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

	public Member getMemberByMemberId(int memberId) {
		return memberDao.getMemberByMemberId(memberId);
	}

}
