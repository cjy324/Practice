package practice.controller;

import java.util.List;
import java.util.Scanner;

import practice.container.Container;
import practice.dto.Article;
import practice.dto.Board;
import practice.dto.Recommand;
import practice.dto.Reply;
import practice.service.ArticleService;

public class ArticleController {

	Scanner sc;
	ArticleService articleService;

	public ArticleController() {
		sc = Container.scanner;
		articleService = Container.articleService;
	}

	public void docmd(String cmd) {
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
		// 댓글 추가
		else if (cmd.startsWith("article reply ")) {
			reply(cmd);
		}
		// 댓글 수정
		else if (cmd.startsWith("article replyModify ")) {
			replyModify(cmd);
		}
		// 댓글 삭제
		else if (cmd.startsWith("article replyDelete ")) {
			replyDelete(cmd);
		}
		// 추천 추가
		else if (cmd.startsWith("article recommand ")) {
			recommand(cmd);
		}
		// 추천 삭제
		else if (cmd.startsWith("article recommandCancel ")) {
			recommandCancel(cmd);
		}

	}

	private void recommandCancel(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}

		int recommandMemberId = Container.session.loginMemberId;

		Recommand recommand = articleService.getRecommand(article.id, recommandMemberId);

		if (recommand == null) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.cancelRecommand(article.id, recommandMemberId);

		System.out.printf("== %d번 게시판 추천 취소 ==\n", article.id);
		
	}

	private void recommand(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}

		int recommandMemberId = Container.session.loginMemberId;

		Recommand recommand = articleService.getRecommand(article.id, recommandMemberId);

		if (recommand != null) {
			System.out.println("이미 추천하셨습니다.");
			return;
		}

		int id = articleService.addRecommand(article.id, recommandMemberId);

		System.out.printf("== %d번 게시판 추천 완료 ==\n", article.id);

	}

	private void replyDelete(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Reply reply = articleService.getReply(inputedId);

		if (reply == null) {
			System.out.println("해당 댓글이 존재하지 않습니다.");
			return;
		}

		if (reply.replyMemberId != Container.session.loginMemberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.replyDelete(reply.id);

		System.out.printf("== %d번 댓글 삭제완료 ==\n", inputedId);

	}

	private void replyModify(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Reply reply = articleService.getReply(inputedId);

		if (reply == null) {
			System.out.println("해당 댓글이 존재하지 않습니다.");
			return;
		}

		if (reply.replyMemberId != Container.session.loginMemberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		System.out.printf("== %d번 댓글 수정 ==\n", reply.id);
		System.out.printf("수정할 내용 입력) ");
		String replyBody = sc.nextLine();

		articleService.replyModify(reply.id, replyBody);

		System.out.printf("== %d번 댓글 수정완료 ==\n", reply.id);

	}

	private void reply(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}

		System.out.printf("댓글 입력) ");
		String replyBody = sc.nextLine();
		int replyMemberId = Container.session.loginMemberId;

		int id = articleService.addReply(article.id, replyBody, replyMemberId);

		System.out.printf("%d번 게시판, %d번 댓글 생성 완료\n", article.id, id);

	}

	private void delete(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}

		if (article.memberId != Container.session.loginMemberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.articleDelete(article.id);

		System.out.printf("== %d번 게시물 삭제완료 ==\n", inputedId);

	}

	private void modify(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}

		if (article.memberId != Container.session.loginMemberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		System.out.printf("== %d번 게시물 수정 ==\n", article.id);
		System.out.printf("수정할 제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 입력) ");
		String body = sc.nextLine();

		articleService.articleModify(article.id, title, body);

		System.out.printf("== %d번 게시물 수정완료 ==\n", article.id);

	}

	private void detail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleForDetail(inputedId);

		if (article == null) {
			System.out.println("해당 게시물이 존재하지 않습니다.");
			return;
		}
		Board board = articleService.getBoard(article.boardId);
		List<Recommand> recommands = articleService.getRecommands(article.id);

		System.out.println("== 게시물 상세보기 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("작성일 : %s\n", article.regDate);
		System.out.printf("수정일 : %s\n", article.updateDate);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("추천수 : %d\n", recommands.size());
		System.out.printf("작성자 : %s\n", article.extra_memberName);
		System.out.printf("등록 게시판 : %s\n", board.name);

		System.out.println("==== 댓글 ====");

		List<Reply> replies = articleService.getRepliesForPrint(article.id);

		if (replies.size() <= 0) {
			System.out.println("(댓글 없음)");
			return;
		}

		for (Reply reply : replies) {
			System.out.printf("%d번 댓글 - %s / 작성자 - %s\n", reply.id, reply.replyBody, reply.extra_memberName);
		}

	}

	private void list(String cmd) {

		Board board = articleService.getBoard(Container.session.selectedBoardId);

		if (board == null) {
			System.out.println("해당 게시판은 존재하지 않습니다.");
			return;
		}

		System.out.printf("%s 게시판 글 리스트\n", board.name);
		System.out.println("번호 / 제목 / 작성일 / 작성자");

		List<Article> articles = articleService.getArticlesForPrint(board.id);

		for (Article article : articles) {
			System.out.printf("%d / %s / %s / %s \n", article.id, article.title, article.regDate,
					article.extra_memberName);
		}

	}

	private void add(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		int boardId = Container.session.selectedBoardId;
		int memberId = Container.session.loginMemberId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시물 생성 완료\n", id);

	}

	private void selectBoard(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoard(inputedId);

		if (board == null) {
			System.out.println("게시판이 없습니다.");
			return;
		}

		Container.session.selectedBoardId = board.id;

		System.out.printf("%s 게시판 선택 완료\n", board.name);

	}

	private void boardAdd(String cmd) {
		System.out.printf("게시판 이름 입력) ");
		String name = sc.nextLine();
		System.out.printf("게시판 코드 입력) ");
		String code = sc.nextLine();

		int id = articleService.addBoard(name, code);

		System.out.printf("%d번 게시판 생성 완료\n", id);
	}

}
