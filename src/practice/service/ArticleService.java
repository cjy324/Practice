package practice.service;

import java.util.List;

import practice.container.Container;
import practice.dao.ArticleDao;
import practice.dto.Article;

public class ArticleService {

	ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
		
	}
	
	public int add(String title, String body, int writerNum) {
		return articleDao.add(title, body, writerNum);
	}

	public List<Article> getArticles() {
		return articleDao.articles();
	}
	
}
