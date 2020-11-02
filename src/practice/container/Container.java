package practice.container;

import practice.dao.ArticleDao;
import practice.dao.MemberDao;
import practice.service.ArticleService;
import practice.service.MemberService;
import practice.session.Sesseion;

public class Container {
	public static Sesseion session;
	public static MemberDao memberDao;
	public static ArticleDao articeDao;
	public static MemberService memberService;
	public static ArticleService articleService;

	
	
	static {
		session = new Sesseion();
		memberDao = new MemberDao();
		articeDao = new ArticleDao();
		memberService = new MemberService();
		articleService = new ArticleService();

	}

}
