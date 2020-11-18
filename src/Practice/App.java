package practice;

import java.util.Scanner;

import practice.container.Container;
import practice.controller.ArticleController;
import practice.controller.Controller;
import practice.controller.MemberController;
import practice.mysqlutil.MysqlUtil;
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

		init();
	}

	private void init() {

		// DB연결
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
		MysqlUtil.setDevMode(true);  //어떤 쿼리가 실행되었는지 확인할 수 있는 모드
		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardId = articleService.getDefultBoardNum();
		
		MemberService memberService = Container.memberService;
		memberService.getMemberByMemberId(1);
		Container.session.loginMemberId = 1;
	}

	public void run() {
		while (true) {
			System.out.printf("명령어 입력) ");
			String cmd = sc.nextLine();

			// DB 연결 종료 필요한지 확인
			boolean needToExit = false;

			if (cmd.equals("exit")) {
				System.out.println("종료");
				needToExit = true;
			}

			else {
				Controller controller = getControllerByCmd(cmd);
				if (controller != null) {
					controller.doCmd(cmd);
					
				}
			}

			// DB 연결 종료 필요
			MysqlUtil.closeConnection();

			//
			if (needToExit) {
				break;
			} 

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