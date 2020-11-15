package mySqlTest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mySqlTest.dto.Article;
import mySqlTest.dto.Board;

public class ArticleDao {

	String driver;
	Connection conn;
	String url;
	String userName;
	String userPw;
	String sql;
	List<Board> boards;

	public ArticleDao() {
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/textBoard?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull&connectTimeout=60000&socketTimeout=60000";
		userName = "sbsst";
		userPw = "sbs123414";
		sql = "";
		boards = new ArrayList<>();

	}

	public int boardAdd(String boardName) {
		int boardId = 0;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sql = "INSERT INTO board ";
			sql += "SET ";
			sql += "boardName = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, boardName);

				pstmt.executeUpdate();

				ResultSet addedBoardId = pstmt.getGeneratedKeys();
				addedBoardId.next();

				boardId = addedBoardId.getInt(1);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return boardId;
	}

	public Board getBoard(int inputedId) {
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sql = "SELECT * FROM board ";
			sql += "WHERE boardId = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, inputedId);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					if (rs.getInt("boardId") == inputedId) {
						int boardId = rs.getInt("boardId");
						String boardName = rs.getString("boardName");

						Board board = new Board();
						board.boardId = boardId;
						board.boardName = boardName;

						return board;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	public int add(int boardId, String title, String body, int memberId) {
		int id = 0;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sql = "INSERT INTO article ";
			sql += "SET ";
			sql += "regDate = NOW(), ";
			sql += "updateDate = NOW(), ";
			sql += "title = ?, ";
			sql += "body = ?, ";
			sql += "boardId = ?, ";
			sql += "memberId = ?";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, title);
				pstmt.setString(2, body);
				pstmt.setInt(3, boardId);
				pstmt.setInt(4, memberId);

				pstmt.executeUpdate();

				ResultSet addedId = pstmt.getGeneratedKeys();
				addedId.next();

				id = addedId.getInt(1);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return id;
	}

	public List<Article> getArticles() {
		
		List<Article> articles = new ArrayList<>();
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(url, userName, userPw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sql = "SELECT * FROM article";

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String regDate = rs.getString("regDate");
					String updateDate = rs.getString("updateDate");
					String title = rs.getString("title");
					String body = rs.getString("body");
					int boardId = rs.getInt("boardId");
					int memberId = rs.getInt("memberId");

					Article article = new Article(id, regDate, updateDate, title, body, boardId, memberId);
					articles.add(article);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return articles;
	}

}
