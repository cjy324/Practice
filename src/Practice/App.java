package Practice;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[10];   // articles라는 이름의 Article배열 10칸을 만듬

	public Article getArticle(int id) {
		if (id > articles.length) {
			return null;
		}
		return articles[id - 1];   // 만약 1번 게시물 틀을 불러오고 싶으면 [0]번째 칸에 있는 정보를 가져와야하므로 id-1

	}

	public void run() { // class 필드에선 변수 선언까지만 가능
						// 기능(메소드) 구현을 위해 void 필드에 기능 구현
//		articles[0]=article1;    //articles 배열 첫번째 칸에 article1을 채워넣음

		for (int i = 0; i < articles.length; i++) { // articles 배열 i번째 칸에 Article객체를 채워넣는 것을
													// articles.length(배열의 길이)까지 반복
			articles[i] = new Article(); // 만약 articles.length가 100이면 i는 99까지 증가하지만
											// []칸은 100칸이 채워지는 것임
		}

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;
				System.out.printf("제목 입력: ");
				title = sc.nextLine();
				System.out.printf("내용 입력: ");
				body = sc.nextLine();
				System.out.printf("%d번글이 생성되었습니다.\n", id);

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

			}

			if (command.equals("article list")) {
				if (lastArticleId == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("== 게시물 리스트 ==");
				System.out.println("번호 / 제목");

				for (int i = 1; i <= lastArticleId; i++) {
					Article article = getArticle(i);
					System.out.printf("%d / %s\n", article.id, article.title);
				}
			}
			if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle == null || selectedArticle.id == 0) {
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
					continue;
				}
				System.out.printf("번호 : %d\n", selectedArticle.id);
				System.out.printf("제목 : %s\n", selectedArticle.title);
				System.out.printf("내용 : %s\n", selectedArticle.body);

			}

			else if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}

		}
		sc.close();

	}

}
