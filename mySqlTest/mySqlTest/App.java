package mySqlTest;

import java.util.Scanner;

import mySqlTest.container.Container;
import mySqlTest.controller.ArticleController;
import mySqlTest.controller.MemberController;

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
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if(cmd.startsWith("member ")) {
				memberController.doCmd(cmd);
				
			}
			else if(cmd.startsWith("article ")) {
				articleController.doCmd(cmd);
				
			}
			
			
			
			
			if(cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
		}
		
		
		sc.close();
	}

}
