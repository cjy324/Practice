package practice.dao;

import java.util.ArrayList;
import java.util.List;

import practice.dto.Member;

public class MemberDao {

	
	List<Member> members;
	int lastMemberNum;
	
	public MemberDao() {
		members = new ArrayList<>();
		lastMemberNum = 0;
		
		makeTestData();
	}

	private void makeTestData() {
		join("aaa", "aaa", "nameAaa");
		join("bbb", "bbb", "nameBbb");
			
	}

	public int join(String mId, String mPw, String mName) {
		Member member = new Member();
		
		member.mNum = lastMemberNum + 1;
		member.mId = mId;
		member.mPw = mPw;
		member.mName = mName;
		members.add(member);
		lastMemberNum = member.mNum;
		
		return member.mNum;
		
	}

	public Member getMemberEqualsId(String inputedLoginId) {
		for(Member member : members) {
			if(member.mId.equals(inputedLoginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean checkEqualsMemberId(String mId) {
		for(Member member : members) {
			if(member.mId.equals(mId)) {
				return true;
			}
		}
		return false;
	}

	public Member getMemberByMemberNum(int memberNum) {
		for(Member member : members) {
			if(member.mNum == memberNum) {
				return member;
			}
		}
		return null;
	}
	
	
	
	
}
