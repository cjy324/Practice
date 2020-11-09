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

	public int makeBoard(String bName) {
		return articleDao.makeBoard(bName);
	}

	public Board getBoardByInputedNum(int inputedNum) {
		for (Board board : articleDao.getBoards()) {
			if (board.bNum == inputedNum) {
				return board;
			}
		}

		return null;
	}

	public int add(int boardNum, String title, String body, int writerNum) {
		return articleDao.add(boardNum, title, body, writerNum);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public List<Article> getArticlesByBoardNum(int selectedBoardNum) {
		List<Article> newArticles = new ArrayList<>();
		for (Article article : articleDao.getArticles()) {
			if (article.boardNum == selectedBoardNum) {
				newArticles.add(article);
			}
		}
		
		Collections.reverse(newArticles);  //ArrayList 역순시켜주는 유틸?

		return newArticles;
	}

	public int getDefultBoardNum() {
		return articleDao.getBoards().get(0).bNum;
	}

}
