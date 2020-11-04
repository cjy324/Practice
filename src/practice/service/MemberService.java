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

	public Member getMemberById(String inputedLoginId) {
		return memberDao.getMemberEqualsId(inputedLoginId);
	}

	public boolean checkJoinableMemberId(String mId) {
		boolean result = memberDao.checkEqualsMemberId(mId);
			if(result == true) {
				return true;
			}
		
		return false;
	}

	public Member getMemberByMemberNum(int memberNum) {
		return memberDao.getMemberByMemberNum(memberNum);
	}

}
