package mySqlTest.controller;

import java.util.List;
import java.util.Scanner;

import mySqlTest.container.Container;
import mySqlTest.dto.Article;
import mySqlTest.dto.Board;
import mySqlTest.dto.Member;
import mySqlTest.service.ArticleService;
import mySqlTest.service.MemberService;

public class ArticleController {

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
		if (cmd.equals("article boardAdd")) {
			boardAdd(cmd);
		}
		// 게시판 선택
		else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}
		// 게시물 생성
		else if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스팅
		else if (cmd.equals("article list")) {
			list(cmd);
		}

	}

	private void list(String cmd) {

		Board board = articleService.getBoard(Container.session.selectedBoardId);
		
		System.out.printf("== %s 게시판 게시물 리스트 ==\n",board.boardName);
		
		System.out.println("번호 / 제목 / 작성자 / 작성날짜");
		
		List<Article> articles = articleService.getBoardArticles(board.boardId);
		
		if(articles.size() <= 0) {
			System.out.printf("%s 게시판에 게시물이  존재하지 않습니다.\n", board.boardName);
		}
		
		for(Article article : articles) {
			Member member = memberService.getMemberByMemberId(article.memberId);
			System.out.printf("%d / %s / %s / %s\n", article.id, article.title, member.name, article.regDate);
		}
		
	}

	private void add(String cmd) {
		
		if(Container.session.loginstatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}
		
		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		int boardId = Container.session.selectedBoardId;
		int memberId = Container.session.loginedMemberId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시물 생성 완료\n", id);

	}

	private void selectBoard(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoard(inputedId);

		System.out.printf("%s 게시판 선택 완료\n", board.boardName);
		Container.session.selectedBoardId = board.boardId;

	}

	private void boardAdd(String cmd) {
		System.out.printf("게시판 이름 입력) ");
		String boardName = sc.nextLine();

		int boardId = articleService.boardAdd(boardName);

		System.out.printf("%d(%s) 게시판 생성 완료\n", boardId, boardName);

	}

}
