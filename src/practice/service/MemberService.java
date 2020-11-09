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

	public boolean checkUsableMemberIdByMId(String mId) {
		for(Member member : memberDao.getMembers()) {
			if(member.mId.equals(mId)) {
				return false;
			}		
		}
		
			return true;	
	}

	public Member getMemberByInputedMId(String inputedMId) {
		for(Member member : memberDao.getMembers()) {
			if(member.mId.equals(inputedMId)) {
				return member;
			}
		}
		
		return null;
	}

	public Member getMemberByNum(int writerNum) {
		for(Member member : memberDao.getMembers()) {
			if(member.mNum == writerNum) {
				return member;
			}
		}
		return null;
	}
	
	
	
	
	
}
