package practice;

import java.util.Scanner;

import practice.container.Container;
import practice.controller.ArticleController;
import practice.controller.Controller;
import practice.controller.MemberController;

public class App {

	MemberController memberController = new MemberController();
	ArticleController articleController = new ArticleController();

	public App() {
		memberController = new MemberController();
		articleController = new ArticleController();
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어 입력) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

			Controller controller = getControllerByCmd(cmd);
			controller.doCommand(cmd);

		}

		sc.close();

	}

	private Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;

		}

		else if (cmd.startsWith("article ")) {
			return articleController;

		}
		return null;
	}

}
