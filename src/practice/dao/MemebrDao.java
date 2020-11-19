package practice.dao;

import java.util.Map;

import MysqlUtil.MysqlUtil;
import MysqlUtil.SecSql;
import practice.dto.Member;

public class MemebrDao {

	public MemebrDao() {

	}

	public int join(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("name = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Member getMember(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM `member` WHERE loginId = ?", loginId);

		Map<String, Object> memberMap = MysqlUtil.selectRow(sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}

		return new Member(memberMap);
	}

}
