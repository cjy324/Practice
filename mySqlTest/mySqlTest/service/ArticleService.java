package mySqlTest.service;

import java.util.ArrayList;
import java.util.List;

import mySqlTest.container.Container;
import mySqlTest.dao.ArticleDao;
import mySqlTest.dto.Article;
import mySqlTest.dto.Board;

public class ArticleService {

	ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int boardAdd(String boardName) {
		return articleDao.boardAdd(boardName);
	}

	public Board getBoard(int inputedId) {
		return articleDao.getBoard(inputedId);
	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add( boardId,  title,  body,  memberId);
	}

	public List<Article> getBoardArticles(int boardId) {
		List<Article> boardArticles = new ArrayList<>();
		
		for(Article article : articleDao.getArticles()) {
			if(article.boardId == boardId) {
				boardArticles.add(article);
			}
		}
		return boardArticles;
	}

	public int getDefultBoardId(int defultBoardId) {
		 Board board = articleDao.getBoard(defultBoardId);
		
		return  board.boardId;
	}
	
}
