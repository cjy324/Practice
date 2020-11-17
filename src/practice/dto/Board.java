package practice.dto;

import java.util.Map;

public class Board {

	
	public Board(Map<String, Object> boardMap) {
		this.boardId = (int)boardMap.get("boardId");
		this.boardName = (String)boardMap.get("boardName");
	}
	public int boardId;
	public String boardName;
}
