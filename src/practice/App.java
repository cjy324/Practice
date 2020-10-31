package practice;

import java.util.Scanner;

import practice.controller.ArticleController;
import practice.controller.MemberController;

public class App {

	MemberController memberController = new MemberController();
	ArticleController articleController = new ArticleController();
	
	public void run() {

		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어: ");
			String command = sc.nextLine();

			if (command.startsWith("member ")) {
				memberController.run(sc,command);
			}
			if (command.startsWith("article ")) {
				articleController.run(sc,command);
			}
			else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

		}

		sc.close();

	}

}