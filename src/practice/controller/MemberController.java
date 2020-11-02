package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Member;
import practice.service.MemberService;

public class MemberController extends Controller {

	MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;

	}

	public void run(Scanner sc, String cmd) {
		if (cmd.equals("member login")) {

			Member member = null;
			
			System.out.printf("아이디 입력) ");
			String inputedId = sc.nextLine();
			
			member = memberService.getMemberIdBy(inputedId);

			if (member == null) {
				System.out.println("해당아이디는 존재하지 않습니다.");
				return;
			}
			System.out.printf("비밀번호 입력) ");
			String inputedPw = sc.nextLine();

			if (member.mPw.equals(inputedPw) == false) {
				System.out.println("비밀번호가 틀렸습니다.");
				return;
			}

			
			Container.session.isLoginedMemberNum = member.mNum;
			Container.session.isLoginedMemberId = member.mId;
			Container.session.isLoginedMemberName = member.mName;

			System.out.printf("%s 회원님 로그인\n", member.mName);
			
		}

	}

}
