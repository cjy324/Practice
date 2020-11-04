package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Member;
import practice.service.MemberService;

public class MemberController extends Controller{

	MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	Scanner sc = Container.scanner;

	public void doCommand(String cmd) {

		// 회원가입
		if (cmd.equals("member join")) {
			join(cmd);
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login(cmd);
		}
		// whoami
		else if (cmd.equals("member whoami")) {
			whoami(cmd);
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

		Container.session.loginedMemberNum = 0;
		System.out.println("== 로그아웃 되었습니다. ==");

	}

	private void whoami(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 먼저 해주세요.");
			return;
		}

		int LoginedMNum = Container.session.loginedMemberNum;

		Member member = memberService.getMemberByMemberNum(LoginedMNum);

		System.out.printf("== %s회원님 정보 ==\n", member.mName);
		System.out.printf("회원번호 : %d\n", member.mNum);
		System.out.printf("아이디 : %s\n", member.mId);
		System.out.printf("이름: %s\n", member.mName);

	}

	private void login(String cmd) {

		Member member = null;

		System.out.printf("아이디 입력 : ");
		String inputedLoginId = sc.nextLine();

		member = memberService.getMemberById(inputedLoginId);

		if (member == null) {
			System.out.println("해당 아이디는 없습니다.");
			return;
		}

		System.out.printf("비밀번호 입력 : ");
		String inputedLoginPw = sc.nextLine();

		if (member.mPw.equals(inputedLoginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		Container.session.loginedMemberNum = member.mNum;

		System.out.printf("%s회원님 반갑습니다.\n", member.mName);
	}

	private void join(String cmd) {

		System.out.printf("아이디 입력 : ");
		String mId = sc.nextLine();

		if (memberService.checkJoinableMemberId(mId) == true) {
			System.out.println("이미 사용중입니다.");
			return;
		}

		System.out.printf("비밀번호 입력 : ");
		String mPw = sc.nextLine();

		System.out.printf("이름 입력 : ");
		String mName = sc.nextLine();

		int mNum = memberService.join(mId, mPw, mName);

		System.out.printf("%d번 회원님 회원가입 완료.\n", mNum);

	}

}
