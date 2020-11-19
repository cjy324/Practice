package practice.service;

import java.util.List;

import practice.container.Container;
import practice.dao.ArticleDao;
import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Recommand;
import practice.dto.Reply;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int addBoard(String name, String code) {
		return articleDao.addBoard(name, code);
	}

	public Board getBoard(int inputedId) {
		return articleDao.getBoard(inputedId);
	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add(boardId, title, body, memberId);
	}

	public List<Article> getArticlesForPrint(int boardId) {
		return articleDao.getArticlesForPrint(boardId);
	}

	public Article getArticleForDetail(int inputedId) {
		return articleDao.getArticleForDetail(inputedId);
	}

	public void articleModify(int id, String title, String body) {
		articleDao.articleModify(id, title, body);
	}

	public void articleDelete(int id) {
		articleDao.articleDelete(id);

	}

	public int addReply(int articleId, String replyBody, int replyMemberId) {
		return articleDao.addReply(articleId, replyBody, replyMemberId);
	}

	public List<Reply> getRepliesForPrint(int articleId) {
		return articleDao.getRepliesForPrint(articleId);
	}

	public Reply getReply(int inputedId) {
		return articleDao.getReply(inputedId);
	}

	public void replyModify(int id, String replyBody) {
		articleDao.replyModify(id, replyBody);

	}

	public void replyDelete(int id) {
		articleDao.replyDelete(id);

	}

	public int addRecommand(int articleId, int recommandMemberId) {
		return articleDao.addRecommand(articleId, recommandMemberId);
	}

	public Recommand getRecommand(int articleId, int recommandMemberId) {
		return articleDao.getRecommand(articleId, recommandMemberId);
	}

	public List<Recommand> getRecommands(int articleId) {
		return articleDao.getRecommands(articleId);
	}

	public void cancelRecommand(int articleId, int recommandMemberId) {
		articleDao.cancelRecommand(articleId, recommandMemberId);

	}

}
