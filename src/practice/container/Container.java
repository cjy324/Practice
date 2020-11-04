package practice.container;

import java.util.Scanner;

import practice.dao.ArticleDao;
import practice.dao.MemberDao;
import practice.service.ArticleService;
import practice.service.MemberService;
import practice.session.Session;

public class Container {

	public static Scanner scanner;
	public static Session session;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		scanner = new Scanner(System.in);
		session= new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
	}

}
