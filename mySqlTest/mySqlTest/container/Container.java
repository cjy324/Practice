package mySqlTest.container;

import java.util.Scanner;

import mySqlTest.controller.ArticleController;
import mySqlTest.controller.MemberController;
import mySqlTest.dao.ArticleDao;
import mySqlTest.dao.MemberDao;
import mySqlTest.service.ArticleService;
import mySqlTest.service.MemberService;
import mySqlTest.session.Session;

public class Container {

	public static Scanner scanner;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static Session session;


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

