package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.service.ArticleService;

public class ArticleController extends Controller {

	ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void run(Scanner sc, String cmd) {

		if (cmd.equals("article add")) {

			if (Container.session.isLoginStatus() == false) {
				System.out.println("로그인이 필요합니다.");
				return;
			}

			System.out.println("새 게시물 등록");

			String title;
			String body;
			String writer;

			System.out.printf("제목 입력: ");
			title = sc.nextLine();
			System.out.printf("내용 입력: ");
			body = sc.nextLine();
			writer = Container.session.isLoginedMemberName;

			int id = articleService.add(title, body, writer);

			System.out.printf("%d번 게시물 등록완료\n", id);

		} else if (cmd.startsWith("article list ")) {
			int inputedPage = Integer.parseInt(cmd.split(" ")[2]);

			if (inputedPage <= -1) {
				inputedPage = 1;
			}

			System.out.println("== 게시물 리스트 ==");
			System.out.println("번호 / 작성자 / 제목");

			int articlesInAPage = 10;
			int startPoint = articleService.getArticlesSize() - 1;
			startPoint -= (inputedPage - 1) * articlesInAPage;
			int endPoint = startPoint - (articlesInAPage - 1);

			if (startPoint < 0) {
				System.out.println("해당 페이지가 없습니다.");
				return;
			}
			if (endPoint < 0) {
				endPoint = 0;
			}

			for (int i = startPoint; i >= endPoint; i--) {
				System.out.printf("%d / %s / %s\n", articleService.getArticlesByIndex(i).id,
						articleService.getArticlesByIndex(i).writer, articleService.getArticlesByIndex(i).title);

			}

		}

	}

}
