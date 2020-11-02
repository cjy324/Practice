package practice.service;

import practice.container.Container;
import practice.dao.MemberDao;
import practice.dto.Member;

public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String mId, String mPw, String mName) {
		
		return memberDao.join(mId, mPw, mName); 
}

	public Member getMemberIdBy(String inputedId) {
		return memberDao.getMemberIdBy(inputedId);
	}
	
}
