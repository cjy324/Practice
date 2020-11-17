package practice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import practice.container.Container;
import practice.dao.ArticleDao;
import practice.dto.Article;
import practice.dto.Board;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;

	}

	public int makeBoard(String boardName) {
		return articleDao.makeBoard(boardName);
	}

	public Board getBoard(int inputedId) {
		return articleDao.getBoard(inputedId);

	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add(boardId, title, body, memberId);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public List<Article> getArticlesByBoardId(int selectedBoardId) {
		List<Article> newArticles = new ArrayList<>();
		for (Article article : articleDao.getArticles()) {
			if (article.boardId == selectedBoardId) {
				newArticles.add(article);
			}
		}
		
		Collections.reverse(newArticles);  //ArrayList 역순시켜주는 유틸?

		return newArticles;
	}

	public int getDefultBoardNum() {
		return articleDao.getBoards().get(0).boardId;
	}

	public Article getArticleById(int inputedId) {
		return articleDao.getArticleById(inputedId);
	}

	public void articleModify(int inputedId, String title, String body) {
		articleDao.articleModify( inputedId,  title,  body);
		
	}

	public void articleDelete(int inputedId) {
		articleDao.articleDelete(inputedId);
	}

}
