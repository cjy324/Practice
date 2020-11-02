package practice;

import java.util.Scanner;

import practice.controller.ArticleController;
import practice.controller.MemberController;

public class App {

	MemberController memberController = new MemberController();
	ArticleController articleController = new ArticleController();

	public void run() {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 입력) ");
			String cmd = sc.nextLine();

			if (cmd.startsWith("member ")) {
				memberController.run(sc, cmd);

			} else if (cmd.startsWith("article ")) {
				articleController.run(sc, cmd);

			}

			else if (cmd.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

		}

		sc.close();

	}

}
