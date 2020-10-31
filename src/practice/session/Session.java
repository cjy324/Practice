package practice.session;

public class Session {   //로그인 여부를 기억하는 Container 보조 공간?
									//현재 로그인 현황을 파악
									
		public int  loginedMemberId; 
												//만약 loginedMemberId = 0;이면 현재 로그인 인원은 0명
												//만약 loginedMemberId = 3;이면 현재 3번 회원이 로그인중이라는 의미
		
		public boolean isLogined() {  //현재 로그인된 상황인지 판단
			return loginedMemberId != 0;
		}
		
		
}
