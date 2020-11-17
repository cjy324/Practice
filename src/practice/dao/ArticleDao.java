package practice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import practice.dto.Article;
import practice.dto.Board;
import practice.mysqlutil.MysqlUtil;
import practice.mysqlutil.SecSql;

public class ArticleDao {

	public ArticleDao() {

	}

	// 게시판 생성
	public int makeBoard(String boardName) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO board");
		sql.append("SET boardName = ?", boardName);

		return MysqlUtil.insert(sql);
	}

	// 게시판 가져오기
	public Board getBoard(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM board WHERE boardId = ?", inputedId);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);

		if (boardMap.isEmpty()) { // Map의 정보가 비어있는지 여부 확인 isEmpty()
			return null;
		}

		return new Board(boardMap);
	}

	// 게시판 리스트 가져오기
	public List<Board> getBoards() {
		List<Board> boards = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM board");

		List<Map<String, Object>> boardsMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> boardsMap : boardsMapList) {
			Board board = new Board(boardsMap);
			boards.add(board);
		}

		return boards;
	}

	// 게시물 생성
	public int add(int boardId, String title, String body, int memberId) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(), ");
		sql.append("updateDate = NOW(), ");
		sql.append("title = ?, ", title);
		sql.append("body = ?, ", body);
		sql.append("boardId = ?, ", boardId);
		sql.append("memberId = ?", memberId);

		return MysqlUtil.insert(sql);
	}

	// 게시물 리스팅
	public List<Article> getArticles() {

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM article");

		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);
			articles.add(article);
		}

		return articles;
	}

	// 게시물 가져오기
	public Article getArticleById(int inputedId) {

		SecSql sql = new SecSql();

		sql.append("SELECT * FROM article WHERE id = ?", inputedId);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	// 게시물 수정
	public void articleModify(int inputedId, String title, String body) {

		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET title = ?, ", title);
		sql.append("updateDate = NOW(), ");
		sql.append("body = ? ", body);
		sql.append("WHERE id = ?", inputedId);

		MysqlUtil.update(sql);
	}

	// 게시물 삭제
	public void articleDelete(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article WHERE id = ?", inputedId);

		MysqlUtil.update(sql);

	}

}
