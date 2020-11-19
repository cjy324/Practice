package practice;

import java.util.Scanner;

import MysqlUtil.MysqlUtil;
import practice.container.Container;
import practice.controller.ArticleController;
import practice.controller.MemberController;


public class App {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;
	
	public App() {

		sc = Container.scanner;
		memberController = Container.memberController;
		articleController = Container.articleController;
	}
	

	public void run() {

		while (true) {
			System.out.printf("cmd 입력) ");
			String cmd = sc.nextLine();

			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
			
			if (cmd.startsWith("member ")) {
					memberController.docmd(cmd);
			}
			else if (cmd.startsWith("article ")) {
				articleController.docmd(cmd);
		}
			

			if (cmd.equals("exit")) {
				System.out.println("== 종료 ==");
				MysqlUtil.closeConnection();
				break;
			}
		}

		sc.close();
	}

}
