package mySqlTest.controller;

import java.util.Scanner;

import mySqlTest.container.Container;
import mySqlTest.dto.Member;
import mySqlTest.service.MemberService;

public class MemberController {

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = Container.memberService;
	}

	public void doCmd(String cmd) {

		if (cmd.equals("member join")) {
			join(cmd);
		}
		else if (cmd.equals("member login")) {
			login(cmd);
		}

	}

	private void login(String cmd) {
		System.out.printf("아이디) ");
		String loginId = sc.nextLine();
		
		Member member = memberService.getMemberByloginId(loginId);
		
		if(member == null) {
			System.out.println("해당 아이디는 없습니다.");
			return;
		}
				
		System.out.printf("비밀번호) ");
		String loginPw = sc.nextLine();
		
		if(member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}
		
		System.out.printf("%s님 로그인 완료\n",member.name);
		Container.session.loginedMemberId = member.memberId;
		
	}

	private void join(String cmd) {
		System.out.printf("아이디) ");
		String loginId = sc.nextLine();
		
		//Member member = memberService.getMemberByloginId(loginId);
		System.out.printf("비밀번호) ");
		String loginPw = sc.nextLine();
		System.out.printf("이름) ");
		String name = sc.nextLine();
		
		int memberId = memberService.join(loginId,loginPw,name);
		
		System.out.printf("%d번 회원 회원가입 완료\n",memberId);
	
	}

}
