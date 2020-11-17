package practice;

import java.util.Scanner;

import practice.container.Container;
import practice.controller.ArticleController;
import practice.controller.Controller;
import practice.controller.MemberController;
import practice.mysqlutil.MysqlUtil;
import practice.service.ArticleService;

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

		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardId = articleService.getDefultBoardNum();

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
			// 명령어를 작성할 때마다 controller를 선택해야하니 Controller라는 상위개념이 필요
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
			} // 만약, DB 연결이 종료됐으면 프로그램도 종료

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