package practice.session;

public class Session {
	
	public int loginMemberId;
	public int selectedBoardId;

	public boolean loginStatus() {
		return loginMemberId > 0;
	}
	
	public Session() {
		//공지사항을 기본 게시판으로 지정
		selectedBoardId = 1;
	}
}
