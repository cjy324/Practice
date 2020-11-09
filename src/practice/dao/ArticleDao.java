package practice.dao;

import java.util.ArrayList;
import java.util.List;

import practice.dto.Article;
import practice.dto.Board;

public class ArticleDao {

	private List<Board> boards;
	private int lastBoardnum;
	private List<Article> articles;
	private int lastArticleNum;

	public ArticleDao() {

		boards = new ArrayList<>();
		lastBoardnum = 0;
		articles = new ArrayList<>();
		lastArticleNum = 0;

	}

	public int makeBoard(String bName) {

		Board board = new Board();

		board.bNum = lastBoardnum + 1;
		board.bName = bName;
		boards.add(board);
		lastBoardnum = board.bNum;

		return board.bNum;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public int add(int boardNum, String title, String body, int writerNum) {
		Article article = new Article();

		article.boardNum = boardNum;
		article.aNum = lastArticleNum + 1;
		article.title = title;
		article.body = body;
		article.writerNum = writerNum;
		articles.add(article);
		lastArticleNum = article.aNum;

		return article.aNum;
	}

	public List<Article> getArticles() {
		return articles;
	}

}
