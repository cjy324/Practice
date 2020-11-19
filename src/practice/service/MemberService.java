package practice.service;

import practice.container.Container;
import practice.dao.MemebrDao;
import practice.dto.Member;

public class MemberService {

	MemebrDao memebrDao;

	public MemberService() {

		memebrDao = Container.memebrDao;
	}

	public int join(String loginId, String loginPw, String name) {
		return memebrDao.join(loginId, loginPw, name);
	}

	public Member getMemberByloginId(String loginId) {
		return memebrDao.getMember(loginId);
	}

}
