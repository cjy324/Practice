package practice.dto;

import java.util.Map;

public class Reply {

	public Reply(Map<String, Object> repliseMap) {
		this.replyId = (int) repliseMap.get("replyId");
		this.replyBody = (String) repliseMap.get("replyBody");
		this.replyArticleId = (int) repliseMap.get("replyArticleId");
		this.replyWriterId = (int) repliseMap.get("replyWriterId");

	}

	public int replyId;
	public String replyBody;
	public int replyArticleId;
	public int replyWriterId;

}
