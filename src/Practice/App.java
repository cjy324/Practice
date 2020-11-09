package practice;

import java.util.Scanner;

import practice.container.Container;
import practice.controller.ArticleController;
import practice.controller.Controller;
import practice.controller.MemberController;
import practice.service.ArticleService;
import practice.service.MemberService;

public class App {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;

	public App() {
		sc = Container.scanner;
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();

		init();
	}

	private void init() {
		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardNum = articleService.getDefultBoardNum();
		// App초기 실행 시 게시판 초기 defult값을 공지사항으로 지정
		// Container.session.selectedBoardId = 1; 로 직접적으로 지정해도 되지만
		// 수정이 필요할 수도 있기때문에 함수값으로 설정해 놓는 것이 좋음
		// 13:20 추가
		// getFirstBoardNum을 getDefultBoardNum으로 변경
		// 초기 값을 자유게시판으로 변경
	}

	private void makeTestData() {
		// App실행시 테스트데이터들을 한번에 생성
		// 테스트 멤버 1,2
		MemberService memberService = Container.memberService;
		memberService.join("aaa", "aaa", "aaa");
		memberService.join("bbb", "bbb", "bbb");

		// 테스트 게시판 1,2 및 테스트 게시물 10
		ArticleService articleService = Container.articleService;
		articleService.makeBoard("공지사항");
		articleService.makeBoard("자유");
		
		for (int i = 1; i < 6; i++) {
			articleService.add(1, "title" + i, "body" + i, 1);
		}
		for (int i = 6; i < 11; i++) {
			articleService.add(2, "title" + i, "body" + i, 2);
		}

	}

	public void run() {
		while (true) {
			System.out.printf("명령어 입력) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			
			Controller controller = getControllerByCmd(cmd);
			if (controller != null) {
				controller.doCmd(cmd);
			}// cmd 입력시 null값이 나오면 doCmd 자체를 실시하지 않음
			 // 입력 오류시 nullpoint 문제 해결
		}

		sc.close();
	}

	private Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;
		} else if (cmd.startsWith("article ")) {
			return articleController;
		}
		return null;
	}

}