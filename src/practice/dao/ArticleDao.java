package practice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import MysqlUtil.MysqlUtil;
import MysqlUtil.SecSql;
import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Member;
import practice.dto.Recommand;
import practice.dto.Reply;

public class ArticleDao {
	
	public ArticleDao() {
		
	}

	public int addBoard(String name, String code) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `board`");
		sql.append("SET code = ?,", code);
		sql.append("name = ?", name);

		return MysqlUtil.insert(sql);
	}

	public Board getBoard(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM board");
		sql.append("WHERE id = ?", inputedId);

		Map<String, Object> boardMap = MysqlUtil.selectRow(sql);
		
		if(boardMap.isEmpty()) {
			return null;
		}

		return new Board(boardMap);
	}

	public int add(int boardId, String title, String body, int memberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `article`");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?,", body);
		sql.append("boardId = ?,", boardId);
		sql.append("memberId = ?", memberId);

		return MysqlUtil.insert(sql);
	}

	public List<Article> getArticlesForPrint(int boardId) {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*,");
		sql.append("member.name AS extra_memberName");
		sql.append("FROM article");
		sql.append("INNER JOIN member");
		sql.append("ON article.memberId = member.id");
		sql.append("WHERE article.boardId = ?", boardId);

		List<Article> articles = new ArrayList<>();
		List<Map<String, Object>> articlesMapList = MysqlUtil.selectRows(sql);
		
		for(Map<String, Object> articlesMap : articlesMapList) {
			Article article = new Article(articlesMap);
			
			articles.add(article);
		}

		return articles;
	}

	public Article getArticleForDetail(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*,");
		sql.append("member.name AS extra_memberName");
		sql.append("FROM article");
		sql.append("INNER JOIN member");
		sql.append("ON article.memberId = member.id");
		sql.append("WHERE article.id = ?", inputedId);

		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);
		
		if(articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
	}

	public void articleModify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("body = ?", body);
		sql.append("WHERE id = ?", id);

		MysqlUtil.update(sql);
		
	}

	public void articleDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);
		
	}

	public int addReply(int articleId, String replyBody, int replyMemberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `reply`");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("replyBody = ?,", replyBody);
		sql.append("replyArticleId = ?,", articleId);
		sql.append("replyMemberId = ?", replyMemberId);

		return MysqlUtil.insert(sql);
	}

	public List<Reply> getRepliesForPrint(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT reply.*,");
		sql.append("member.name AS extra_memberName");
		sql.append("FROM reply");
		sql.append("INNER JOIN member");
		sql.append("ON reply.replyMemberId = member.id");
		sql.append("WHERE reply.replyArticleId = ?", articleId);

		List<Reply> replies = new ArrayList<>();
		List<Map<String, Object>> repliesMapList = MysqlUtil.selectRows(sql);
		
		for(Map<String, Object> repliesMap : repliesMapList) {
			Reply reply = new Reply(repliesMap);
			
			replies.add(reply);
		}

		return replies;
	}

	public Reply getReply(int inputedId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM reply");
		sql.append("WHERE id = ?", inputedId);

		Map<String, Object> replyMap = MysqlUtil.selectRow(sql);
		
		if(replyMap.isEmpty()) {
			return null;
		}

		return new Reply(replyMap);
	}

	public void replyModify(int id, String replyBody) {
		SecSql sql = new SecSql();

		sql.append("UPDATE reply");
		sql.append("SET");
		sql.append("updateDate = NOW(),");
		sql.append("replyBody = ?", replyBody);
		sql.append("WHERE id = ?", id);

		MysqlUtil.update(sql);
		
	}

	public void replyDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM reply");
		sql.append("WHERE id = ?", id);

		MysqlUtil.delete(sql);
		
		
	}

	public int addRecommand(int articleId, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `recommand`");
		sql.append("SET regDate = NOW(),");
		sql.append("recommandArticleId = ?,", articleId);
		sql.append("recommandMemberId = ?", recommandMemberId);

		return MysqlUtil.insert(sql);
	}

	public Recommand getRecommand(int articleId, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);
		sql.append("AND");
		sql.append("recommandMemberId = ?", recommandMemberId);

		Map<String, Object> recommandMap = MysqlUtil.selectRow(sql);
		
		if(recommandMap.isEmpty()) {
			return null;
		}

		return new Recommand(recommandMap);
	}

	public List<Recommand> getRecommands(int articleId) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);

		List<Recommand> recommands = new ArrayList<>();
		List<Map<String, Object>> recommandsMapList = MysqlUtil.selectRows(sql);
		
		for(Map<String, Object> recommandsMap : recommandsMapList) {
			Recommand recommand = new Recommand(recommandsMap);
			
			recommands.add(recommand);
		}

		return recommands;
	}

	public void cancelRecommand(int articleId, int recommandMemberId) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM recommand");
		sql.append("WHERE recommandArticleId = ?", articleId);
		sql.append("AND");
		sql.append("recommandMemberId = ?", recommandMemberId);

		MysqlUtil.delete(sql);
		
		
	}

}
