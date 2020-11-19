package practice.container;

import java.util.Scanner;

import practice.controller.ArticleController;
import practice.controller.MemberController;
import practice.dao.ArticleDao;
import practice.dao.MemebrDao;
import practice.service.ArticleService;
import practice.service.MemberService;
import practice.session.Session;

public class Container {

	public static Scanner scanner;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static MemberService memberService;
	public static ArticleService articleService;
	public static MemebrDao memebrDao;
	public static ArticleDao articleDao;
	public static Session session;

	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memebrDao = new MemebrDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}
