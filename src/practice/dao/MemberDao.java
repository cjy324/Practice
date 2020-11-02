package practice.dao;

import java.util.ArrayList;
import java.util.List;

import practice.dto.Member;

public class MemberDao {

	private List<Member> members;
	private int lastMemberNum;
	
	public MemberDao() {
		members = new ArrayList<>();
		lastMemberNum = 0;

		for (int i = 1; i < 4; i++) {
			join("ID" + i, "PW" + i, "Name" + i);
		}
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
	
	public Member getMemberIdBy(String inputedId) {
			for(Member member : members) {
				if(member.mId.equals(inputedId)) {
					return member;
				}
			}
			return null;
	}

}
