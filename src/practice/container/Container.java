package practice.container;

import practice.session.Session;

public class Container {   //프로그램이 종료될때까지 정보를 기억하는? 공간
	public static Session session; //static을 붙임으로써 session은 container 객체가 생성되도 거기에는 포함되지 않고
												//오로지 여기에만 고정된다??
												//즉, 클래스로 접근을 하게되는 것이기때문에 어디서든 접근이 가능...??
	static {
		session = new Session();
	}
}

