package Practice;

import java.util.Scanner;

public class App {
	Article[] articles = new Article[10];

	int lastArticleId = 0; // 현재까지 생성된 게시물의 id
	int articlesSize = 0; // 현재 생성되어있는 게시물의 크기(갯수)

	int articlesSize() { // 현재 생성되어있는 게시물의 크기(갯수)를 알려주는 함수
//		return lastArticleId;  			//함수를 사용하면 불필요한 추가 작업들을 줄일 수 있다
		return articlesSize;

	}

	Article getArticle(int id) {
//		if (id < 1) {
//			return null;
//		}
//
//		if (id > lastArticleId) {
//			return null;
//		}

//		return articles[id - 1]; 	//삭제기능 도입으로 인해 id -1이란 개념이 없어졌으므로 제거
		
		int index = getIndexById(id);  //대신 getIndexById에게 id를 통해 index를 불러오는 것으로 대체

		if(index == -1) {
			return null;
		}
		return articles[index];
		
	}

	public void run() {
//		for (int i = 0; i < articles.length; i++) {
//			articles[i] = new Article();
//		}										//자동으로 Article 칸 채우는 것을 삭제

		Scanner sc = new Scanner(System.in);

		int maxArticlesCount = articles.length;

		while (true) {

			System.out.printf("명령어) ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}

			else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (articlesSize() >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;

				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();

//				Article article = getArticle(id);      //getArticle을 거쳐 미리 생성된 Article칸을 불러와 내용을 저장하는 것을 삭제 

				Article article = new Article(); // article을 생성할 때마다 새로운 Article칸 추가

				article.id = id;
				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

//				articles[0] = article;     //articles 0 칸에 article 정보 추가
//				articles[1] = article;  	 //articles 1 칸에 article 정보 추가
//				articles[2] = article;	 //articles 2 칸에 article 정보 추가

				articles[articlesSize] = article; // articlesSize 칸에 article 정보 추가
													// 새 게시물 등록 전 articlesSize는 0
				articlesSize++; // 새 게시물이 등록될 때마다 +1씩 증가
								// 이를 통해 현재 생성되어 있는 게시물의 갯수 파악이 가능

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

//				for (int i = 1; i <= lastArticleId; i++) {       //이대로 삭제기능을 실행하면 null이 발생
//				 	Article article = getArticle(i);				//lastArticleId는 여전히 마지막 게시물 번호이기 때문에
																		//i=1부터 lastArticleId까지 찾다보면 null값이 있을 수 밖에 없음
						
					
				for	(int i = 0; i < articlesSize(); i++) {
					Article article = articles[i]; 					//getArticle(i)를 거치지 않고 직접적으로 articles[i]를 탐색

					System.out.printf("%d / %s\n", article.id, article.title);
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

			}

		}

		sc.close();
	}

//	private void removeArticle(int inputedId) {      //입력된 inputedId에 해당하는 articles[inputedId]을 찾아서 삭제
//				for(int i = inputedId; i < articlesSize; i++) {   //하지만 삭제개념이 생기고 나서부터 articles[]몇번째 칸에 해당 inputedId가 존재할 지 알 수 없다
//					articles[inputedId-1] = articles[inputedId];   //이 메소드로는 원하는 게시물을 지울 수 없고, 다음 게시물이 순차적으로 당겨지지도 못함
//				}
//				articlesSize--;
//	}
	private void removeArticle(int inputedId) {
		int index = getIndexById(inputedId); // 따라서 입력된 inputedId값에 매칭되는 인덱스, 즉, articles[]칸의 번호를 찾아서 매칭시켜야 한다.
		
		if(index == -1) {							//매칭되는 값이 없다, 종료
			return;
		}
		
		for (int i = index+1; i < articlesSize(); i++) {   //articlesSize() 크기까지 이전 게시물을 지우고 다음 게시물로 덮어씌우기
			articles[i - 1] = articles[i];
		}
		articlesSize--;									//게시물이 하나 삭제되었기 때문에 articlesSize()도 1감소
	}

	private int getIndexById(int inputedId) {
		for (int i = 0; i < articlesSize(); i++) { // articlesSize만큼 반복하며
			if (articles[i].id == inputedId) { // inputedId와 매칭되는 articles[i].id 값을 찾는 것
				return i; // inputedId와 매칭되는 articles[i].id 값을 찾았으면 해당 i값을 리턴
			}
		}
		return -1; // inputedId와 매칭되는 articles[i].id 값을 찾지 못했으면 해당 -1값을 리턴
	}

}