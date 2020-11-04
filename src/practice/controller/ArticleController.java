package practice.controller;

import java.util.Scanner;

import practice.container.Container;
import practice.dto.Article;
import practice.dto.Member;
import practice.service.ArticleService;
import practice.service.MemberService;

public class ArticleController extends Controller{

	ArticleService articleService;
	MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	Scanner sc = Container.scanner;

	public void doCommand(String cmd) {
		// 게시물작성
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물리스트
		else if (cmd.equals("article list")) {
			list(cmd);
		}

	}

	private void list(String cmd) {
		System.out.println("== 게시물 리스트 ==");
		System.out.println("== 번호 / 제목 / 작성자 ==");

		for (Article article : articleService.getArticles()) {
			Member member = memberService.getMemberByMemberNum(article.wrticleNum);
			System.out.printf("%d / %s / %s\n", article.aNum, article.title, member.mName);
		}

	}

	private void add(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 먼저 해주세요.");
			return;
		}

		System.out.println("== 새 게시물 등록 ==");

		System.out.printf("제목 입력 : ");
		String title = sc.nextLine();
		System.out.printf("내용 입력 : ");
		String body = sc.nextLine();
		int writerNum = Container.session.loginedMemberNum;

		int aNum = articleService.add(title, body, writerNum);

		System.out.printf("== %d번 게시물 작성완료 ==\n", aNum);
	}

}
