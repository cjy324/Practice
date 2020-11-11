package mySqlTest2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mySqlTest2.dto.Article;

public class App {
	List<Article> articles = new ArrayList<>();
	Scanner sc = new Scanner(System.in);


	public void run() {

		Connection connection = null;
		Statement statment = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost/a1", "sbsst", "sbs123414");
			System.out.println("== 연결성공 ==");

			statment = connection.createStatement();

			String sql = "SELECT * FROM article";
			ResultSet rs = statment.executeQuery(sql);

			
			while (rs.next()) {
				String id = rs.getString("id");
				String regDate = rs.getString("regDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				String hit = rs.getString("hit");
				
				Article article = new Article();
				article.id = id;
				article.regDate = regDate;
				article.title = title;
				article.body = body;
				article.nickname = nickname;
				article.hit = hit;
				articles.add(article);
				
			}

			connection.close();
			statment.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			System.out.println("연결실패");
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		}

		while(true) {
			System.out.printf("명령어 입력) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("article list")) {
				list(cmd);
			}
			if(cmd.equals("exit")) {
				break;
			}
		}
		
		sc.close();
	}

	private void list(String cmd) {
		System.out.println("==게시물 리스트==");		
		System.out.println("번호 / 제목 / 작성자");

		for(Article article : articles) {
			System.out.printf("%s / %s / %s\n",article.id, article.title,article.nickname);
		}
		
		
	}

}