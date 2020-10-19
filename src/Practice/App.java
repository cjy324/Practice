package Practice;

import java.util.Scanner;

public class App {

	Article[] articles = new Article[10];

	Article getArticle(int id) {

		if (id > articles.length) {
			return null;
		}
		return articles[id - 1];

	}

	public void run() {

		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어 입력) ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;
				System.out.printf("제목 입력 : ");
				title = sc.nextLine();
				System.out.printf("제목 : %s\n", title);
				System.out.printf("내용 입력 : ");
				body = sc.nextLine();
				System.out.printf("내용 : %s\n", body);
				System.out.printf("%d번 글이 생성되었습니다.\n", id);

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleId == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= lastArticleId; i++) {
					Article article = getArticle(i);
					System.out.printf("%d / %s\n", article.id, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 상세 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle == null || selectedArticle.id != inputedId) {
					System.out.printf("%d번 글이 존재하지 않습니다.\n", inputedId);
					continue;
				}
				System.out.printf("번호 : %d\n", selectedArticle.id);
				System.out.printf("제목 : %s\n", selectedArticle.title);
				System.out.printf("내용 : %s\n", selectedArticle.body);

			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
		}
		sc.close();

	}

}
