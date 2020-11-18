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
		
		if (articlesMap.containsKey("extra_memberName")) { // 이 변수는 리스트 출력용으로만 생성되기 때문에
																								// 단지 articles 가져오기를 했을때는 퀴리에 입력값이 없으므로,
																								// 값을 불러올 수 없다.
																								// 따라서 if를 사용해 null point exception를 방지
			this.extra_memberName = (String) articlesMap.get("extra_memberName");
		}

	}

	public int boardId;
	public int id;
	public String title;
	public String body;
	public String regDate;
	public String updateDate;
	public int memberId;
	public String extra_memberName;

}
