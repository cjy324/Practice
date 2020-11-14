package mySqlTest.container;

import java.util.Scanner;

import mySqlTest.controller.ArticleController;
import mySqlTest.controller.MemberController;
import mySqlTest.dao.MemberDao;
import mySqlTest.service.MemberService;

public class Container {

	public static Scanner scanner;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static MemberDao memberDao;
	public static MemberService memberService;


	static {
		scanner = new Scanner(System.in);
		
		memberDao = new MemberDao();
		
		memberService = new MemberService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}

