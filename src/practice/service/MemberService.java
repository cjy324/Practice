package practice.service;

import practice.container.Container;
import practice.dao.MemberDao;
import practice.dto.Member;

public class MemberService {

	MemberDao memberDao; 
	
	public MemberService() {
		
		memberDao = Container.memberDao;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public boolean checkUsableMemberIdByLoginId(String loginId) {
		for(Member member : memberDao.getMembers()) {
			if(member.loginId.equals(loginId)) {
				return false;
			}		
		}
		
			return true;	
	}

	public Member getMemberByInputedLoginId(String inputedLoginId) {
		for(Member member : memberDao.getMembers()) {
			
			if(member.loginId.equals(inputedLoginId)) {
				return member;
			}
		}
		
		return null;
	}

	public Member getMemberByMemberId(int memberId) {
		for(Member member : memberDao.getMembers()) {
			if(member.memberId == memberId) {
				return member;
			}
		}
		return null;
	}
	
	
	
	
	
}
