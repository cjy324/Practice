package practice.service;

import java.util.List;

import practice.container.Container;
import practice.dao.ArticleDao;
import practice.dto.Article;

public class ArticleService {

	ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articeDao;
		
	}
	
	
	
	public int add(String title, String body, String writer) {
		return articleDao.add(title, body, writer);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}



	public int getArticlesSize() {
		return articleDao.getArticlesSize() ;
	}



	public Article getArticlesByIndex(int i) {
		return articleDao.getArticlesByIndex(i);
	}

}
