package practice.dto;

import java.util.Map;

public class Recommand {

	public Recommand(Map<String, Object> recommandMap) {
		this.id = (int)recommandMap.get("id");
		this.regDate = (String)recommandMap.get("regDate");
		this.recommandArticleId = (int)recommandMap.get("recommandArticleId");
		this.recommandMemberId = (int)recommandMap.get("recommandMemberId");
	}
	public int id;
	public String regDate;
    public int recommandArticleId;
    public int recommandMemberId;
	
}
