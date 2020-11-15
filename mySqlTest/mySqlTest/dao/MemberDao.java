package mySqlTest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mySqlTest.dto.Board;
import mySqlTest.dto.Member;

public class MemberDao {

	String driver;
	Connection conn;
	String url;
	String userName;
	String userPw;
	String sql;
	List<Member> members;

	public MemberDao() {
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
		userName = "sbsst";
		userPw = "sbs123414";
		sql = "";
		members = new ArrayList<>();
	}

	public int join(String loginId, String loginPw, String name) {
		int memberId = 0;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			sql = "INSERT INTO member ";
			sql += "SET ";
			sql += " loginId = ?";
			sql += ", loginPw = ?";
			sql += ", name = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, loginId);
				pstmt.setString(2, loginPw);
				pstmt.setString(3, name);

				pstmt.executeUpdate();

				ResultSet addedMemberId = pstmt.getGeneratedKeys();
				addedMemberId.next();
				memberId = addedMemberId.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberId;
	}

	public Member getMemberByloginId(String loginId) {

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			sql = "SELECT * FROM member ";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					String equlasLoginId = rs.getString("loginId");
					if (equlasLoginId.equals(loginId)) {
						Member member = new Member();
						member.memberId = rs.getInt("memberId");
						member.loginId = equlasLoginId;
						member.loginPw = rs.getString("loginPw");
						member.name = rs.getString("name");

						return member;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Member getMemberByMemberId(int memberId) {
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			sql = "SELECT * FROM member ";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int equlasMemberId = rs.getInt("memberId");
					if (equlasMemberId == memberId) {
						Member member = new Member();
						member.memberId = equlasMemberId;
						member.loginId = rs.getString("loginId");
						member.loginPw = rs.getString("loginPw");
						member.name = rs.getString("name");

						return member;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
