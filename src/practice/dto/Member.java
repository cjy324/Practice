package practice.dto;

import java.util.Map;

public class Member {

	public Member(Map<String, Object> memberMap) {
		this.memberId = (int)memberMap.get("memberId");
		this.loginId = (String)memberMap.get("loginId");
		this.loginPw = (String)memberMap.get("loginPw");
		this.name = (String)memberMap.get("name");
		
	}
	public int memberId;
	public String loginId;
	public String loginPw;
	public String name;

	
}
