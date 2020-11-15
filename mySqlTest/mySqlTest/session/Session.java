package mySqlTest.session;

public class Session {

	public int selectedBoardId;
	public int loginedMemberId;
	
	public boolean loginstatus() {

		return loginedMemberId != 0;
	}

}
