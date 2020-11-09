package practice.container;

import java.util.Scanner;

import practice.controller.ArticleController;
import practice.controller.MemberController;
import practice.dao.ArticleDao;
import practice.dao.MemberDao;
import practice.service.ArticleService;
import practice.service.MemberService;
import practice.session.Session;

public class Container {

	public static Scanner scanner;
	
	public static Session session;

	public static MemberDao memberDao;
	public static MemberService memberService;

	public static MemberController memberController;
	public static ArticleController articleController;

	public static ArticleDao articleDao;

	public static ArticleService articleService;
	
	
	static {
		scanner = new Scanner(System.in);
		
		session = new Session();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
		
		
	}

}
