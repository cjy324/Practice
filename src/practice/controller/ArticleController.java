package practice.controller;

import java.util.List;
import java.util.Scanner;

import practice.container.Container;
import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Member;
import practice.service.ArticleService;
import practice.service.MemberService;

public class ArticleController extends Controller {

	Scanner sc;
	ArticleService articleService;
	MemberService memberService;

	public ArticleController() {
		sc = Container.scanner;
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCmd(String cmd) {
		// 게시판 생성
		if (cmd.equals("article makeBoard")) {
			makeBoard(cmd);
		}
		// 게시판 선택
		else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}
		// 게시물 생성
		else if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스트
		else if (cmd.equals("article list")) {
			list(cmd);
		}
		// 게시물 상세보기
		else if (cmd.startsWith("article detail ")) {
			detail(cmd);
		}
		// 게시물 수정
		else if (cmd.startsWith("article modify ")) {
			modify(cmd);
		}
		// 게시물 삭제
		else if (cmd.startsWith("article delete ")) {
			delete(cmd);
		}

	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
			return;
		}
		
		articleService.articleDelete(inputedId);
		
		System.out.printf("%d번 게시물 삭제 완료\n", inputedId);
	}

	private void modify(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
			return;
		}

		System.out.println("수정할 제목: ");
		String title = sc.nextLine();
		System.out.println("수정할 내용: ");
		String body = sc.nextLine();

		articleService.articleModify(inputedId, title, body);

		System.out.printf("%d번 게시물 수정 완료\n", inputedId);

	}

	private void detail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(inputedId);

		if (article == null) {
			System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
			return;
		}
		Board board = articleService.getBoard(article.boardId);
		Member member = memberService.getMemberByMemberId(article.memberId);

		System.out.printf("== %d번 게시물 상세보기 ==\n", inputedId);
		System.out.printf("게시판: %s 게시판\n", board.boardName);
		System.out.printf("제목: %s\n", article.title);
		System.out.printf("내용: %s\n", article.body);
		System.out.printf("작성일시: %s\n", article.regDate);
		System.out.printf("최종수정일시: %s\n", article.updateDate);
		System.out.printf("작성자: %s\n", member.name);
	}

	private void list(String cmd) {

		Board board = articleService.getBoard(Container.session.selectedBoardId);
		List<Article> boardArticles = articleService.getArticlesByBoardId(board.boardId);

		System.out.printf("== %s 게시판 글 리스트 ==\n", board.boardName);
		System.out.println("번호 / 제목 / 작성자 / 작성날짜");

		for (Article article : boardArticles) {
			Member member = memberService.getMemberByMemberId(article.memberId);
			System.out.printf("%d / %s / %s / %s\n", article.id, article.title, member.name, article.regDate);
		}

	}

	private void add(String cmd) {

		System.out.printf("제목) ");
		String title = sc.nextLine();
		System.out.printf("내용) ");
		String body = sc.nextLine();
		int memberId = Container.session.loginMemberId;
		int boardId = Container.session.selectedBoardId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시물 생성 완료\n", id);

	}

	private void selectBoard(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoard(inputedId);

		if (board == null) {
			System.out.println("해당 게시판이 존재하지 않습니다.");
			return;
		}

		System.out.printf("%s 게시판이 선택되었습니다.\n", board.boardName);

		Container.session.selectedBoardId = board.boardId;

	}

	private void makeBoard(String cmd) {
		System.out.printf("게시판 이름) ");
		String boardName = sc.nextLine();

		int boardId = articleService.makeBoard(boardName);

		System.out.printf("%s(%d번) 게시판 생성 완료\n", boardName, boardId);

	}

}
