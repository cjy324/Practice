package practice.dto;

import java.util.Map;

public class Article {

	public Article(Map<String, Object> articlesMap) {
		this.boardId = (int) articlesMap.get("boardId");
		this.id = (int) articlesMap.get("id");
		this.regDate = (String) articlesMap.get("regDate");
		this.updateDate = (String) articlesMap.get("updateDate");
		this.title = (String) articlesMap.get("title");
		this.body = (String) articlesMap.get("body");
		this.memberId = (int) articlesMap.get("memberId");

	}

	public int boardId;
	public int id;
	public String title;
	public String body;
	public String regDate;
	public String updateDate;
	public int memberId;

}
