package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Member;
import practice.service.MemberService;

public class MemberController extends Controller {

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = Container.memberService;

	}

	public void doCmd(String cmd) {
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
		// whoami
		else if (cmd.equals("member whoami")) {
			whoami(cmd);
		}

	}

	private void whoami(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}
		
		int memberId = Container.session.loginMemberId;
		
		Member member = memberService.getMemberByMemberId(memberId);
		
		System.out.println("== 회원 정보 ==");
		System.out.printf("회원번호: %d\n",member.memberId);
		System.out.printf("회원아이디: %s\n",member.loginId);
		System.out.printf("회원이름: %s\n",member.name);
		
	}

	private void logout(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		Container.session.loginMemberId = 0;
		System.out.println("== 로그 아웃 ==");

	}

	private void login(String cmd) {

		if (Container.session.loginStatus() == true) {
			System.out.println("먼저 로그아웃 해주세요.");
			return;
		}

		System.out.printf("아이디 입력) ");
		String inputedLoginId = sc.nextLine();

		Member member = memberService.getMemberByInputedLoginId(inputedLoginId);

		if (member == null) {
			System.out.println("해당 아이디 없음");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String inputedLoginPw = sc.nextLine();

		if (member.loginPw.equals(inputedLoginPw) == false) {
			System.out.println("비밀번호 틀림");
			return;
		}

		System.out.printf("%s님, 로그인 완료\n", member.name);

		Container.session.loginMemberId = member.memberId;
	}

	private void join(String cmd) {

		if (Container.session.loginStatus() == true) {
			System.out.println("먼저 로그아웃 해주세요.");
			return;
		}

		System.out.printf("아이디 입력) ");
		String loginId = sc.nextLine();

		boolean checkUsableMemberIdByMId = memberService.checkUsableMemberIdByLoginId(loginId);

		if (checkUsableMemberIdByMId == false) {
			System.out.println("이미 사용중");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String loginPw = sc.nextLine();
		System.out.printf("이름 입력) ");
		String name = sc.nextLine();

		int memberId = memberService.join(loginId, loginPw, name);

		System.out.printf("%d번 회원 회원가입 완료\n", memberId);
	}

}
