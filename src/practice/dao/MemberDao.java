package practice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import practice.dto.Member;
import practice.mysqlutil.MysqlUtil;
import practice.mysqlutil.SecSql;

public class MemberDao {

	public MemberDao() {

	}
	//회원가입
	public int join(String loginId, String loginPw, String name) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO member ");
		sql.append("SET loginId = ?, ", loginId);
		sql.append("loginPw = ?, ", loginPw);
		sql.append("name = ?", name);

		return MysqlUtil.insert(sql);
	}
	//로그인 아이디 확인
	public List<Member> getMembers() {

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM member");
		
		List<Member> members = new ArrayList<>();
		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> memberMap : memberMapList) {
			Member member = new Member(memberMap);

			members.add(member);
		}

		return members;
	}
}
