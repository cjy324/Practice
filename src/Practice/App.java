package Practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class App {

	private Article[] articles;       //지저분한 내용들은 정리해놓는 것이 좋음

	private int lastArticleId;       //지저분한 내용들은 정리해놓는 것이 좋음
	private int articlesSize;		 //지저분한 내용들은 정리해놓는 것이 좋음
	
	public void init() {        //run()이 실행되기 전 초기 설정값(defult값)을 지정해놓는 함수
		articles = new Article[32];
		lastArticleId = 0;
		articlesSize = 0;
		
		//defult 게시물 32개
		for (int i = 0; i < 32; i++) {
			add(i + 1 + "번 제목", i + 1 + "번 내용","yyyy / MM / dd / HH:mm:ss");
//			articles[i] = new Article();
//			articles[i].id = i + 1;
//			articles[i].title = i + 1 + "번 제목";
//			articles[i].body = i + 1 + "번 내용";
//			articles[i].regDate = "yyyy / MM / dd / HH:mm:ss";
		}
	}
	
	

	public int articlesSize() {
		return articlesSize;
	}

	public Article getArticle(int id) {

		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles[index];

	}

	public void run() {
	
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			// 새 게시물 등록

			if (command.equals("add")) {
				System.out.println("== 게시물 등록 ==");
				
				
				String title;
				String body;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				
				String regDate = sdf.format(cal.getTime());

				
				System.out.printf("제목 입력) \n");
				title = sc.nextLine();
				System.out.printf("내용 입력) \n");
				body = sc.nextLine();

				int id = add(title, body, regDate);

				System.out.printf("== %d번 게시물 등록 완료 ==\n", id);

			}

			// 게시물 리스트

			else if (command.equals("list")) {

				if (articlesSize() == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
					continue;
				}

				System.out.println("== 게시물 리스트 ==");
				System.out.println("== 번호 / 제목 / 작성 날짜 ==");

				for (int i = articlesSize() - 1; i >= 0; i--) {
					Article article = articles[i];
					System.out.printf("%d / %s / %s\n", article.id, article.title, article.regDate);

				}

			}

			// 게시물 상세보기

			else if (command.startsWith("detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[1]);

				if (articlesSize() == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
					continue;
				}

				System.out.println("== 게시물 상세보기 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle.id != inputedId) {
					System.out.printf("== %d번 게시물이 존재하지 않습니다. ==\n", inputedId);
					continue;
				}
				System.out.printf("번호: %d\n", selectedArticle.id);
				System.out.printf("제목: %s\n", selectedArticle.title);
				System.out.printf("내용: %s\n", selectedArticle.body);
				System.out.printf("작성 날짜: %s\n", selectedArticle.regDate);
				

			}

			// 게시물 삭제

			else if (command.startsWith("delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[1]);

				if (articlesSize() == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
					continue;
				}

				System.out.println("== 게시물 삭제 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle.id != inputedId) {
					System.out.printf("== %d번 게시물이 존재하지 않습니다. ==\n", inputedId);
					continue;
				}

				removeArticle(inputedId);
				System.out.printf("== %d번 게시물이 삭제되었습니다. ==\n", inputedId);

			}

			// 게시물 수정

			else if (command.startsWith("modify ")) {
				int inputedId = Integer.parseInt(command.split(" ")[1]);

				if (articlesSize() == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
					continue;
				}

				System.out.println("== 게시물 수정 ==");

				Article selectedArticle = getArticle(inputedId);

				if (selectedArticle.id != inputedId) {
					System.out.printf("== %d번 게시물이 존재하지 않습니다. ==\n", inputedId);
					continue;
				}

				System.out.printf("수정할 제목 입력) \n");
				selectedArticle.title = sc.nextLine();
				System.out.printf("수정할 내용 입력) \n");
				selectedArticle.body = sc.nextLine();

				System.out.printf("== %d번 게시물 수정 완료 ==\n", inputedId);

			}

			// 게시물 내용 검색

			else if (command.startsWith("search ")) {
				String inputedKeword = command.split(" ")[1];

				if (articlesSize() == 0) {
					System.out.println("== 게시물이 존재하지 않습니다. ==");
					continue;
				}

				System.out.println("== 게시물 검색 결과 ==");
				System.out.println("== 번호 / 제목 / 내용==");

				for (int i = 0; i <= articlesSize() - 1; i++) {
					Article article = articles[i];
					if (articles[i].body.contains(inputedKeword)) {
						System.out.printf("%d / %s / %s\n", article.id, article.title, article.body);

					}
				}

				System.out.println("== 검색 종료==");

			}

			// 프로그램 종료

			else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

		}

		sc.close();

	}




	private boolean isArticlesFull() {
		return articlesSize() == articles.length;
	}



	private int add(String title, String body, String regDate) {

		if(isArticlesFull()) {
			System.out.printf("== 배열 공간 확장 %d -> %d ==\n",articles.length, articles.length*2);
			Article[] expandArticles = new Article[articles.length*2];
			for(int i = 0; i < articles.length; i++){
				expandArticles[i] = articles[i];
			}
			articles = expandArticles;		
		}
		
	
		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		article.regDate = regDate;

		articles[articlesSize] = article;
		articlesSize++;
		
		lastArticleId = article.id;
		return article.id;
		
	}

	private void removeArticle(int inputedId) {
		int index = getIndexById(inputedId);

		if (index == -1) {
			return;
		}

		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}

		articlesSize--;
	}

	private int getIndexById(int inputedId) {
		for (int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == inputedId) {
				return i;
			}
		}

		return -1;
	}

}
