package Practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class App {
	Article[] articles = new Article[3];

	int lastArticleId = 0;
	int articlesSize = 0;

	public int articlesSize() {
		return articlesSize;
	}

	Article getArticle(int id) {

		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles[index];

	}

	private int add(String title, String body, String regDate) {
		
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

	public void run() {
		for (int i = 0; i < articles.length; i++) {
			articles[i] = new Article();
		}

		Scanner sc = new Scanner(System.in);

//		int maxArticlesCount = articles.length;

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				
				
				if (articlesSize() >= articles.length) {   // 만약에 배열 공간이 꽉 차 있다면 새 업체과 계약한다.
					
					System.out.printf("== Article 배열 공간 확장 %d -> %d ==\n",articles.length, articles.length*2);
					
					Article[] newArticles = new Article[articles.length * 2];
																										// newArticles라는 새 업체과 계약
						for (int i = 0; i < articles.length; i++) {                   // 기존 articles배열의 정보를 newArticles 새 배열 공간에 덮어씌우기
								newArticles[i] = articles[i];
						}
					articles = newArticles; 									// newArticles라는 새 배열을 articles배열이라고 바꿔치기
																							// 이를 통해 다음 절차 진행시 articles라고 인식하고 기존과 동일한 진행 가능
				}

//				if (articlesSize() >= maxArticlesCount) {                        //배열 공간이 무한히 확장되므로 maxArticleCount는 더이상 무용지물
//					System.out.println("더 이상 생성할 수 없습니다.");
//					continue;
//				}

//				int id = lastArticleId + 1;
				String title;
				String body;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String regDate = sdf.format(cal.getTime());

//				lastArticleId = id;

				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();

				
				int id = add(title, body, regDate);     //add라는 함수에 값을 입력받아서 새 id(번호)를 부여    
				
				
//				Article article = new Article();

//				article.id = id;
//				article.title = title;
//				article.body = body;
//				article.regDate = regDate;
				
//				articles[articlesSize] = article;

//				articlesSize++;

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);


			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 제목 / 작성날짜");

				for (int i = articlesSize() - 1; i >= 0; i--) {
					Article article = articles[i];

					System.out.printf("%d / %s / %s\n", article.id, article.title, article.regDate);
				}
			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			} else if (command.startsWith("article delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				removeArticle(inputedId);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			} else if (command.startsWith("article modify ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 수정 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				article.title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				article.body = sc.nextLine();

				System.out.printf("%d번 게시물 수정 결과\n", inputedId);
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			} else if (command.startsWith("article search ")) {
				String inputedWord = command.split(" ")[2];
				System.out.println("== 게시물 검색 ==");

				System.out.println("번호 / 제목 / 내용");

				for (int i = 0; i < articlesSize(); i++) {
					if (articles[i].body.contains(inputedWord)) {
						Article article = articles[i];
						System.out.printf("%d / %s / %s\n", article.id, article.title, article.body);
					}
				}

				System.out.println("검색 종료");
			}

		}

		sc.close();
	}

}