package practice.dto;

import java.util.Map;

public class Article {

	public Article(Map<String, Object> articlesMap) {
		this.id = (int)articlesMap.get("id");
		this.regDate = (String)articlesMap.get("regDate");
		this.updateDate = (String)articlesMap.get("updateDate");
		this.title = (String)articlesMap.get("title");
		this.body = (String)articlesMap.get("body");
		this.boardId = (int)articlesMap.get("boardId");
		this.memberId = (int)articlesMap.get("memberId");
	
		if(articlesMap.containsKey("extra_memberName")) {
			this.extra_memberName = (String)articlesMap.get("extra_memberName");
		}
		
	}
	public int id;
	public String regDate; 
	public String updateDate;
	public String title;
	public String body;
	public int boardId;
	public int memberId;
	public String extra_memberName;
	
	
}
