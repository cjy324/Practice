package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Member;
import practice.service.MemberService;

public class MemberController {

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = Container.memberService;
	}

	public void docmd(String cmd) {
		// 회원가입
		if (cmd.equals("member join")) {
			join(cmd);
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login(cmd);
		}
		// 로그아웃
		else if (cmd.equals("member logout")) {
			logout(cmd);
		}

	}

	private void logout(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		Container.session.loginMemberId = 0;
		System.out.println("== 로그아웃 ==");
	}

	private void login(String cmd) {
		System.out.printf("아이디 입력) ");
		String loginId = sc.nextLine();

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			System.out.println("해당 아이디는 존재하지 않습니다.");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String loginPw = sc.nextLine();
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호 틀림");
			return;
		}

		System.out.printf("%s님, 로그인 완료\n", member.name);
		Container.session.loginMemberId = member.id;

	}

	private void join(String cmd) {

		System.out.printf("아이디 입력) ");
		String loginId = sc.nextLine();

		Member member = memberService.getMemberByloginId(loginId);

		if (member != null) {
			System.out.println("해당 아이디는 이미 사용중");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String loginPw = sc.nextLine();
		System.out.printf("이름 입력) ");
		String name = sc.nextLine();

		int id = memberService.join(loginId, loginPw, name);

		System.out.printf("%d번 회원 회원가입 완료\n", id);
	}

}
