package practice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Reply;
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

	// 게시물 리스팅x => 게시물s 가져오기만 수행
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

	// 댓글 추가
	public int addReply(int replyArticleId, String replyBody, int replyWriterId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO reply");
		sql.append("SET replyBody = ?,", replyBody);
		sql.append("replyArticleId = ?,", replyArticleId);
		sql.append("replyWriterId = ?", replyWriterId);

		return MysqlUtil.insert(sql);
	}

	// 댓글s 가져오기
	public List<Reply> getReplies() {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM reply");
		List<Reply> replies = new ArrayList<Reply>();
		List<Map<String, Object>> repliesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> repliseMap : repliesMapList) {
			Reply reply = new Reply(repliseMap);

			replies.add(reply);
		}

		return replies;
	}

	// 댓글 가져오기
	public Reply getReply(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM reply WHERE replyId = ?", inputedId);

		Map<String, Object> replyMap = MysqlUtil.selectRow(sql);

		if (replyMap.isEmpty()) {
			return null;
		}

		return new Reply(replyMap);
	}

	// 댓글 수정
	public void modifyReply(int inputedId, String replyBody, int replyWriterId) {
		SecSql sql = new SecSql();

		sql.append("UPDATE reply");
		sql.append("SET replyBody = ?,", replyBody);
		sql.append("replyWriterId = ?", replyWriterId);
		sql.append("WHERE replyId = ?", inputedId);

		MysqlUtil.update(sql);
	}

	// 댓글 삭제
	public void deleteReply(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM reply");
		sql.append("WHERE replyId = ?", inputedId);

		MysqlUtil.update(sql);

	}

	// 출력용 게시물 리스트 가져오기(쿼리 한개만 수행)
	public List<Article> getArticlesForPrint() { 					// 오직 리스트 출력용으로만 사용될 함수
		SecSql sql = new SecSql();

		sql.append("SELECT article.*, member.name AS extra_memberName"); // inner join을 통해 쿼리를 한번만 실행해도
		sql.append("FROM article"); 																		// 멤버의 이름까지 가져올 수 있도록 함
		sql.append("INNER JOIN member");									 // 단, 이 퀴리 실행시 article 클래스 안에 member.name이라는 변수가 존재하지 않으므로
		sql.append("ON article.memberId = member.memberId"); 				// member.name를 extra_memberName로 명명후 article 클래스에
																														// extra_memberName 변수 선언

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);
			articles.add(article);
		}

		return articles;
	}

}
