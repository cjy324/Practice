package practice.session;

public class Sesseion {

	public int isLoginedMemberNum;
	public String isLoginedMemberId;
	public String isLoginedMemberName;
	
	public boolean isLoginStatus() {
		return isLoginedMemberNum != 0;
	}

}
