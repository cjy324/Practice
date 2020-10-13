package Practice;

import java.util.Scanner;

public class Practice201013_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		
		  int lastArticleId = 0; //반복문 밖으로 꺼냄으로써 반복문의 영향을 받지 않고 고정
		  
		  while (true) {
		  
		  System.out.printf("명령어) "); String command = scanner.nextLine();
		  
		  
		  if (command.equals("article add")) { System.out.println("== 새로운 게시판 생성 ==");
		  
		  int id = lastArticleId + 1; String title; String body;
		  
		  lastArticleId = id;
		  
		  System.out.printf("제목 입력 : "); title = scanner.nextLine();
		  System.out.printf("내용 입력 : "); body = scanner.nextLine();
		  
		  System.out.printf("%d번 게시물이 생성되었습니다. \n", id); } }
		  
		 
	}

}
