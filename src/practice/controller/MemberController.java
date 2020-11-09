package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Member;
import practice.service.MemberService;

public class MemberController extends Controller{

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = Container.memberService;

	}

	public void doCmd(String cmd) {
		// 회원가입
		if (cmd.equals("member join")) {
			join();
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login();
		}

	}

	private void login() {

		System.out.printf("아이디 입력) ");
		String inputedMId = sc.nextLine();

		Member member = memberService.getMemberByInputedMId(inputedMId);

		if (member == null) {
			System.out.println("해당 아이디 없음");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String inputedMPw = sc.nextLine();

		if (member.mPw.equals(inputedMPw) == false) {
			System.out.println("비밀번호 틀림");
			return;
		}

		System.out.printf("%s님, 로그인 완료\n", member.mName);
		
		Container.session.loginMemberNum = member.mNum;
	}

	private void join() {

		System.out.printf("아이디 입력) ");
		String mId = sc.nextLine();

		boolean checkUsableMemberIdByMId = memberService.checkUsableMemberIdByMId(mId);

		if (checkUsableMemberIdByMId == false) {
			System.out.println("이미 사용중");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String mPw = sc.nextLine();
		System.out.printf("이름 입력) ");
		String mName = sc.nextLine();

		int mNum = memberService.join(mId, mPw, mName);

		System.out.printf("%d번 회원 회원가입 완료\n", mNum);
	}

}
