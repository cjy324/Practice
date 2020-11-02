package practice.dao;

import java.util.ArrayList;
import java.util.List;

import practice.dto.Article;

public class ArticleDao {

	private List<Article> articles;
	private int lastArticleId;

	public List<Article> getArticles(){
		return articles;
	}
	
	public ArticleDao() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		
		for(int i = 1 ; i < 11; i ++) {
			add(i+"제목",i+"내용",i+"작성자");
		}
	}

	public int add(String title, String body, String writer) {
		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.writer = writer;
		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	public int getArticlesSize() {
		return getArticles().size();
	}

	public Article getArticlesByIndex(int i) {
		return getArticles().get(i);
	}


}
