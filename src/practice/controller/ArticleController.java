package practice.controller;

import java.util.List;
import java.util.Scanner;

import practice.container.Container;
import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Member;
import practice.service.ArticleService;
import practice.service.MemberService;

public class ArticleController  extends Controller{

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
			makeBoard();
		}
		// 게시판 선택
		else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}
		// 게시물 생성
		else if (cmd.equals("article add")) {
			add();
		}
		// 게시물 리스트
		else if (cmd.equals("article list")) {
			list();
		}

	}

	private void list() {
		
		
		Board board = articleService.getBoardByInputedNum(Container.session.selectedBoardNum);
		List<Article> newArticles = articleService.getArticlesByBoardNum(Container.session.selectedBoardNum);
		
		
		System.out.printf("== %s 게시판 글 리스트 ==\n",board.bName);
		System.out.println("번호 / 제목 / 작성자");

		
		for(Article article : newArticles) {
			Member member = memberService.getMemberByNum(article.writerNum);
			System.out.printf("%d / %s / %s\n",article.aNum, article.title, member.mName);
		}
		
		
	}

	private void add() {

		/*
		 * //App의 init에서 defult값을 설정해서 이제 필요 없음 
		 * if
		 * (Container.session.boardStatus() == false) {
		 * Container.session.selectedBoardId = 1;
		 * 
		 * System.out.println("게시물을 등록할 게시판을 먼저 선택해주세요."); return;
		 * 
		 * }
		 */
		
		
		
		
		
		System.out.printf("제목) ");
		String title = sc.nextLine();
		System.out.printf("내용) ");
		String body = sc.nextLine();
		int writerNum = Container.session.loginMemberNum;
		int boardNum = Container.session.selectedBoardNum;

		int aNum = articleService.add(boardNum, title, body, writerNum);

		System.out.printf("%d번 게시물 생성 완료\n", aNum);

	}

	private void selectBoard(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoardByInputedNum(inputedNum);

		System.out.printf("%s 게시물이 선택되었습니다.\n", board.bName);

		Container.session.selectedBoardNum = board.bNum;

	}

	private void makeBoard() {
		System.out.printf("게시판 이름) ");
		String bName = sc.nextLine();

		int bNum = articleService.makeBoard(bName);

		System.out.printf("%s(%d번) 게시판 생성 완료\n", bName, bNum);

	}

}
