package practice.dto;

import java.util.Map;

public class Reply {

	public Reply(Map<String, Object> repliesMap) {
		this.id = (int)repliesMap.get("id");
		this.regDate = (String)repliesMap.get("regDate");
		this.updateDate = (String)repliesMap.get("updateDate");
		this.replyBody = (String)repliesMap.get("replyBody");
		this.replyArticleId = (int)repliesMap.get("replyArticleId");
		this.replyMemberId = (int)repliesMap.get("replyMemberId");
		
		if(repliesMap.containsKey("extra_memberName")) {
			this.extra_memberName = (String)repliesMap.get("extra_memberName");
		}
		
	}
	public int id;
	public String regDate;
	public String updateDate;
	public String replyBody;
    public int replyArticleId;
    public int replyMemberId;
    public String extra_memberName;
	
	
}
