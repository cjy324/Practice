package ArrayListPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public void run() {
		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<Article>();

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어 입력) \n");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("게시물 등록");

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;
				System.out.println("제목 입력) ");
				title = sc.nextLine();
				System.out.println("내용 입력) ");
				body = sc.nextLine();

				articles.add(new Article(id, title, body));
				System.out.printf("게시물 등록 현황 : %d\n", articles.size());
				System.out.printf("%d번 게시물 등록 완료\n", id);
				System.out.println("번호 : " + articles.get(id - 1).id);
				System.out.println("제목 : " + articles.get(id - 1).title);
				System.out.println("내용 : " + articles.get(id - 1).body);

			}

			// 게시물 리스트
			else if (command.startsWith("article list ")) {
				int inputedPage = Integer.parseInt(command.split(" ")[2]);

				if (inputedPage <= -1) {
					inputedPage = 1;
				}

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				int articlesInAPage = 10;
				int startPoint = articles.size() - 1;
				startPoint -= (inputedPage - 1) * articlesInAPage;
				int endPoint = startPoint - (articlesInAPage - 1);

				if (startPoint < 0) {
					System.out.printf("%d 페이지가 없습니다.\n", inputedPage);
					continue;
				}
				if (endPoint < 0) {
					endPoint = 0;
				}

				System.out.println("게시물 리스트");
				System.out.println("번호 / 제목");
				for (int i = startPoint; i >= endPoint; i--) {
					System.out.printf("%d / %s\n", articles.get(i).id, articles.get(i).title);
				}

			}

			// 게시물 상세보기
			else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				if (articles.get(inputedId - 1) == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				System.out.println("게시물 상세보기");
				System.out.printf("번호 : %d\n", articles.get(inputedId - 1).id);
				System.out.printf("제목 : %s\n", articles.get(inputedId - 1).title);
				System.out.printf("내용 : %s\n", articles.get(inputedId - 1).body);

			}

			// 게시물 삭제
			else if (command.startsWith("article delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				if (articles.get(inputedId - 1) == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				articles.remove(inputedId - 1);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			}

			// 게시물 수정
			else if (command.startsWith("article modify ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				if (articles.get(inputedId - 1) == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				System.out.println("변경할 제목 입력) ");
				articles.get(inputedId - 1).title = sc.nextLine();
				System.out.println("변경할 내용 입력) ");
				articles.get(inputedId - 1).body = sc.nextLine();

				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

			}
			// 게시물 검색
			else if (command.startsWith("article search ")) {
				String[] commandBits = command.split(" ");
				String inputedKeyword = commandBits[2];

				int inputedPage = 1;

				if (commandBits.length >= 4) {
					inputedPage = Integer.parseInt(command.split(" ")[3]);
				}

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				//검색된 articles의 크기?
				
				int searchedArticlesLen = 0;
				
				for(Article article : articles) {
					if(article == null) {
						break;
					}
					if(article.title.contains(inputedKeyword)) {
						searchedArticlesLen++;
					}
				}
				
				List<Article> searchedArticles = new ArrayList<Article>();
				
				for(Article article : articles) {
					if(article.title.contains(inputedKeyword)) {
						searchedArticles.add(article);
					}
				}
		
				
				
				int articlesInAPage = 10;
				int startPoint = searchedArticlesLen - 1;
				startPoint -= (inputedPage - 1) * articlesInAPage;
				int endPoint = startPoint - (articlesInAPage - 1);

				if (startPoint < 0) {
					System.out.printf("%d 페이지가 없습니다.\n", inputedPage);
					continue;
				}
				if (endPoint < 0) {
					endPoint = 0;
				}

				System.out.println("게시물 리스트");
				System.out.println("번호 / 제목");
				for (int i = startPoint; i >= endPoint; i--) {
					System.out.printf("%d / %s\n", searchedArticles.get(i).id,searchedArticles.get(i).title);
				}

			}

			// 프로그램 종료
			else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
		}
		sc.close();
		
	}

}
