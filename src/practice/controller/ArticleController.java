package practice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import practice.dto.Article;

public class ArticleController extends Controller {

	List<Article> articles;
	int lastArticleId;

	public ArticleController() {

		articles = new ArrayList<>();
		lastArticleId = 0;
		
		for(int i = 1; i < 15; i++ ) {
			i = add(i + "제목", i + "내용");
		}

	}

	private int add(String title, String body) {
		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		articles.add(article);
		lastArticleId = article.id;

		return article.id;
	}

	public void run(Scanner sc, String command) {

		// 게시물 등록
		if (command.equals("article add")) {
			System.out.println("== 새 게시물 등록 ==");

			String title = "";
			String body = "";

			System.out.printf("제목 입력: ");
			title = sc.nextLine();
			System.out.printf("내용 입력: ");
			body = sc.nextLine();

			int id = add(title, body);

			System.out.printf("%d번 게시물 등록 완료\n", id);

		}
		// 게시물 리스트
		else if (command.startsWith("article list ")) {
			int inputedPage = Integer.parseInt(command.split(" ")[2]);

			if (inputedPage <= -1) {
				inputedPage = 1;
			}

			if (articles.size() == 0) {
				System.out.println("현재 등록된 게시물이 없습니다.");
				return;
			}

			int articlesInAPage = 10;
			int startPoint = articles.size() - 1;
			startPoint -= (inputedPage - 1) * articlesInAPage;
			int endPoint = startPoint - (articlesInAPage - 1);

			if (startPoint < 0) {
				System.out.println("해당 페이지가 없습니다.");
				return;
			}
			if (endPoint < 0) {
				endPoint = 0;
			}

			System.out.println("== 게시물 리스트 ==");
			System.out.println("번호 / 제목");
			for (int i = startPoint; i >= endPoint; i--) {
				System.out.printf("%d / %s\n", articles.get(i).id, articles.get(i).title);
			}

		}
		// 게시물 상세보기
		else if (command.startsWith("article detail ")) {
			int inputedId = Integer.parseInt(command.split(" ")[2]);

			if (articles.size() == 0) {
				System.out.println("현재 등록된 게시물이 없습니다.");
				return;
			}
			if (articles.get(inputedId - 1) == null) {
				System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
				return;
			}

			System.out.printf("== %d번 게시물 상세보기 ==\n", inputedId);
			System.out.printf("번호 : %d\n", articles.get(inputedId - 1).id);
			System.out.printf("제목 : %s\n", articles.get(inputedId - 1).title);
			System.out.printf("내용 : %s\n", articles.get(inputedId - 1).body);

		}
		// 게시물 수정
		else if (command.startsWith("article modify ")) {
			int inputedId = Integer.parseInt(command.split(" ")[2]);

			if (articles.size() == 0) {
				System.out.println("현재 등록된 게시물이 없습니다.");
				return;
			}
			if (articles.get(inputedId - 1) == null) {
				System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
				return;
			}

			System.out.printf("== %d번 게시물 수정 ==\n", inputedId);
			System.out.printf("제목 입력: ");
			articles.get(inputedId - 1).title = sc.nextLine();
			System.out.printf("내용 입력: ");
			articles.get(inputedId - 1).body = sc.nextLine();

			System.out.printf("== %d번 게시물 수정 완료==\n", inputedId);

		}
		// 게시물 삭제
		else if (command.startsWith("article delete ")) {
			int inputedId = Integer.parseInt(command.split(" ")[2]);

			if (articles.size() == 0) {
				System.out.println("현재 등록된 게시물이 없습니다.");
				return;
			}
			if (articles.get(inputedId - 1) == null) {
				System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
				return;
			}

			articles.remove(inputedId - 1);

			System.out.printf("== %d번 게시물 삭제 완료==\n", inputedId);

		}
		// 게시물 제목 검색
		else if (command.startsWith("article search ")) {
			String[] commandBits = command.split(" ");
			String inputedKeyword = commandBits[2];

			int inputedPage = 1;

			if (commandBits.length >= 4) {
				inputedPage = Integer.parseInt(commandBits[3]);

			}

			if (articles.size() == 0) {
				System.out.println("현재 등록된 게시물이 없습니다.");
				return;
			}

			List<Article> searchedArticles = new ArrayList<>();
			
			for(Article article : articles) {
				if(article.title.contains(inputedKeyword)) {
					searchedArticles.add(article);
				}
			}
			
			if (searchedArticles.size() == 0) {
				System.out.println("해당 키워드 관련 게시물이 없습니다.");
				return;
			}
			
			int articlesInAPage = 10;
			int startPoint = searchedArticles.size() - 1;
			startPoint -= (inputedPage - 1) * articlesInAPage;
			int endPoint = startPoint - (articlesInAPage - 1);

			if (startPoint < 0) {
				System.out.println("해당 페이지가 없습니다.");
				return;
			}
			if (endPoint < 0) {
				endPoint = 0;
			}

			System.out.println("== 게시물 리스트 ==");
			System.out.println("번호 / 제목");
			for (int i = startPoint; i >= endPoint; i--) {
				System.out.printf("%d / %s\n", searchedArticles.get(i).id, searchedArticles.get(i).title);
			}

		}

	}
}