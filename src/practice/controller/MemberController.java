package practice.controller;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import practice.dto.Member;


public class MemberController extends Controller {

	List<Member> members;
	int lastMemberNum;

	public MemberController() {

		members = new ArrayList<>();
		lastMemberNum = 0;
		
		for(int i = 1; i < 3; i++ ) {
			i = join("아이디"+i, "비번"+ i, "이름"+ i);
		}

	}

	private int join(String memberId, String memberPw, String memberName) {
		Member member = new Member();

		member.memberNum = lastMemberNum + 1;
		member.memberId = memberId;
		member.memberPw = memberPw;
		member.memberName = memberName;
		members.add(member);
		lastMemberNum = member.memberNum;

		return member.memberNum;
	}

	private boolean memberIdIsAvilable(String memberId) {
		for (Member member : members) {
			if (member.memberId.equals(memberId)) {
				return false;
			}
		}
		return true;
	}
	private Member getMemberByinputedId(String inputedId) {
		for (Member member : members) {
			if (member.memberId.equals(inputedId)) {
				return member;
			}
		}
		return null;
	}


	public void run(Scanner sc, String command) {
		// 회원가입
		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			String memberId = "";
			String memberPw = "";
			String memberName = "";

			int tryCount = 0;
			int maxTryCount = 3;
			boolean thisMemberIdIsValid = false;

			while (true) {
				if (tryCount >= maxTryCount) {
					System.out.println("== 회원가입 취소 ==");
					break;
				}
				System.out.printf("아이디 입력: ");
				memberId = sc.nextLine();

				if (memberId.length() == 0) {
					System.out.println("아이디를 입력하세요.");
					tryCount++;
					continue;
				}
				if (memberIdIsAvilable(memberId) == false) {
					System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", memberId);
					tryCount++;
					continue;
				}
				thisMemberIdIsValid = true;
				break;
			}
			if (thisMemberIdIsValid == false) {
				return;
			}

			while (true) {
				System.out.printf("비밀번호 입력: ");
				memberPw = sc.nextLine();
				if (memberPw.length() == 0) {
					System.out.println("비밀번호를 입력하세요.");
					continue;
				}
				break;
			}

			while (true) {
				System.out.printf("이름 입력: ");
				memberName = sc.nextLine();
				if (memberName.length() == 0) {
					System.out.println("이름을 입력하세요.");
					continue;
				}
				break;
			}

			int memberNum = join(memberId, memberPw, memberName);

			System.out.printf("%d번 회원 회원가입 완료\n", memberNum);

		}
		// 로그인
		else if (command.equals("member login")) {

			if (Container.session.isLogined()) {
				System.out.println("이미 로그인 되었습니다.");
				return;
			}
			
			
			System.out.println("== 로그인 ==");
			
			
			
			

			String inputedId = "";
			String inputedPw = "";
			
			
			int tryIdCount = 0;
			int maxTryIdCount = 3;
			boolean thisLoginIdIsValid = false;
			
			Member member = null;


			while (true) {
				if (tryIdCount >= maxTryIdCount) {
					System.out.println("== 로그인 취소 ==");
					break;
				}
				System.out.printf("아이디 입력: ");
				inputedId = sc.nextLine();
				
				member = getMemberByinputedId(inputedId);

				if (member == null) {
					System.out.printf("%s(은)는 없는 아이디 입니다.\n", inputedId);
					tryIdCount++;
					continue;
				}
				if (inputedId.length() == 0) {
					System.out.println("아이디를 입력하세요.");
					tryIdCount++;
					continue;
				}
				thisLoginIdIsValid = true;
				break;
			}
			if (thisLoginIdIsValid == false) {
				return;
			}
			
			
			int tryPwCount = 0;
			int maxTryPwCount = 3;
			boolean thisLoginPwIsValid = false;
			
			while (true) {
				if (tryPwCount >= maxTryPwCount) {
					System.out.println("== 로그인 취소 ==");
					break;
				}
				System.out.printf("비밀번호 입력: ");
				inputedPw = sc.nextLine();
				
				
				
				if (member.memberPw.equals(inputedPw) == false) {
					System.out.printf("비밀번호가 틀렸습니다.\n", inputedPw);
					tryPwCount++;
					continue;
				}
				if (inputedPw.length() == 0) {
					System.out.println("비밀번호를 입력하세요.");
					tryPwCount++;
					continue;
				}
				thisLoginIdIsValid = true;
				break;
			}
			if (thisLoginIdIsValid == false) {
				return;
			}

			System.out.printf("로그인 되었습니다. %s님 반갑습니다.\n",member.memberName);
			
//			Container container = new Container();
//			Session session = Container.session;  //Container라는 공간에 포함된 session이라는 곳을 불러온다?
//			session.loginedMemberId = member.memberId;  //session에게 현재 로그인된 회원의 정보를 담는다
			
// 		static을 붙임으로써 위 코드들처럼 일일이 객체를 생성할 필요없고
			Container.session.loginedMemberId = member.memberId;

		}

	}

	



}
