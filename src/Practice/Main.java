package Practice;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		// 1 게시물 정보
		Article article1 = new Article();
		/*
		 * int article1_id = 0; 
		 * String article1_title = ""; 
		 * String article1_body = "";
		 */

		// 2 게시물 정보
		Article article2 = new Article();
		/*
		 * int article2_id = 0; 
		 * String article2_title = ""; 
		 * String article2_body = "";
		 */
		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 새 게시물 등록 ==");

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;
				System.out.printf("번호 : %d\n", id);
				System.out.printf("제목 : ");
				title = scanner.nextLine();
				System.out.printf("제목 : %s\n", title);
				System.out.printf("내용 : ");
				body = scanner.nextLine();
				System.out.printf("내용 : %s\n", body);

				if (id == 1) {
					article1.id = id;
					article1.title = title;
					article1.body = body;
				}
				if (id == 2) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}

				System.out.println("등록되었습니다.");

			} else if (command.equals("article list")) {

				if (lastArticleId == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("== 게시물 리스트 ==");
				System.out.printf("번호 / 제목\n");

				if (lastArticleId >= 1) {
					System.out.printf("%d / %s\n", article1.id, article1.title);

				}
				if (lastArticleId >= 2) {
					System.out.printf("%d / %s\n", article2.id, article2.title);

				}

			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (inputedId == 1) {
					if (inputedId != article1.id) {
						System.out.println("게시물이 없습니다.");
						continue;
					}
					System.out.printf("번호 : %d\n", article1.id);
					System.out.printf("제목 : %s\n", article1.title);
					System.out.printf("내용 : %s\n", article1.body);

				}
				if (inputedId == 2) {
					if (inputedId != article2.id) {
						System.out.println("게시물이 없습니다.");
						continue;
					}
					System.out.printf("번호 : %d\n", article2.id);
					System.out.printf("제목 : %s\n", article2.title);
					System.out.printf("내용 : %s\n", article2.body);

				}
			}

			else if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}

		}
		scanner.close();

	}

}
