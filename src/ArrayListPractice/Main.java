package ArrayListPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Practice.Article;

public class Main {

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);

		
		
		List<Article> articles = new ArrayList<Article>();
		
		int lastArticleId = 0;
		
		while (true) {
			System.out.printf("명령어 입력) \n");
			String command = sc.nextLine();

			if(command.equals("article add")) {
				System.out.println("게시물 등록");
				
				int id = lastArticleId + 1;
				String title;
				String body;
				
				lastArticleId = id;
				System.out.println("제목 입력) ");
				title = sc.nextLine();
				System.out.println("내용 입력) ");
				body = sc.nextLine();
				
				
				
				System.out.printf("%d번 게시물 등록 완료\n", id);
				
			}
			
			
			
			
			
			
			
			if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
		}
		sc.close();

	}

}
