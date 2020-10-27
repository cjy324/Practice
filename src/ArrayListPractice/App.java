package ArrayListPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public void run() {

		List<Article> articles = new ArrayList<Article>();
		List<Member> members = new ArrayList<Member>();
		List<Member> loginedMembers = new ArrayList<Member>();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;
		int lastMamberNumber = 0;
		int loginedMembersIndex = 0;

		while (true) {
			System.out.printf("명령어 입력) ");
			String command = sc.nextLine();

			// 추가
			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;
				System.out.printf("제목 입력) ");
				title = sc.nextLine();
				System.out.printf("내용 입력) ");
				body = sc.nextLine();

				articles.add(new Article(id, title, body));

				System.out.printf("%d번 게시물 등록 완료\n", id);
				System.out.printf("번호 : %d\n", articles.get(id - 1).id);
				System.out.printf("제목 : %s\n", articles.get(id - 1).title);
				System.out.printf("내용 : %s\n", articles.get(id - 1).body);
				System.out.printf("작성날짜 : %s\n", articles.get(id - 1).regDate);

			}
			// 리스트
			else if (command.startsWith("article list ")) {

				int inputedPage = 0;

				try { // Integer.parseInt는 숫자화 할 만한 문자들은 원할하게 변화 가능
						// 하지만 숫자화가 어려운 문자들이 나열되면 오류가 발생할 수 있음
						// 위험성이 있는 코드는 try로 감싸 한번 시도해 보는 개념
					inputedPage = Integer.parseInt(command.split(" ")[2]);
				}

				catch (NumberFormatException e) {
					// 위험성이 있는 코드에 대한 대비책(catch)를 준비해 try를 실행해보는 개념
					// 내부적으로 작은 문제 발생시 대비책을 통해 문제를 해결하는 개념
					System.out.println("숫자로 입력하세요.");
				}

				if (inputedPage <= 1) {
					inputedPage = 1;
				}

				if (articles.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
					continue;
				}

				int articlesInAPage = 10;
				int startPoint = articles.size() - 1;
				startPoint -= (inputedPage - 1) * articlesInAPage;
				int endPoint = startPoint - (articlesInAPage - 1);

				if (startPoint < 0) {
					System.out.printf("%d 페이지가 없습니다.\n", inputedPage);
					continue;
				}
				if (endPoint < 0) {
					endPoint = 0;
				}

				System.out.println("== 게시물 리스트 ==");
				System.out.println("번호 / 제목");

				for (int i = startPoint; i >= endPoint; i--) {
					System.out.printf("%d / %s\n", articles.get(i).id, articles.get(i).title);
				}

			}
			// 상세보기
			else if (command.startsWith("article detail ")) {

				int inputedId = 0;
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("숫자로 입력하세요.");
				}

				if (articles.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
					continue;
				}

				if (articles.get(inputedId - 1).id != inputedId) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				System.out.println("== 게시물 상세보기 ==");
				System.out.printf("번호 : %d\n", articles.get(inputedId - 1).id);
				System.out.printf("제목 : %s\n", articles.get(inputedId - 1).title);
				System.out.printf("내용 : %s\n", articles.get(inputedId - 1).body);
				System.out.printf("작성날짜 : %s\n", articles.get(inputedId - 1).regDate);

			}
			// 게시물 삭제
			else if (command.startsWith("article delete ")) {
				
				int inputedId = 0;
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("숫자로 입력하세요.");
				}

				if (articles.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
					continue;
				}

				if (articles.get(inputedId - 1).id != inputedId) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				articles.remove(inputedId - 1);

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			}

			// 게시물 수정
			else if (command.startsWith("article modify ")) {
				
				int inputedId = 0;
				try {
					inputedId = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("숫자로 입력하세요.");
				}

				if (articles.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
					continue;
				}

				if (articles.get(inputedId - 1) == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", inputedId);
					continue;
				}

				System.out.println("== 게시물 수정 ==");

				System.out.printf("수정할 제목 입력) ");
				articles.get(inputedId - 1).title = sc.nextLine();
				System.out.printf("수정할 내용 입력) ");
				articles.get(inputedId - 1).body = sc.nextLine();

				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

				System.out.println("== 수정된 내용 ==");
				System.out.printf("번호 : %d\n", articles.get(inputedId - 1).id);
				System.out.printf("제목 : %s\n", articles.get(inputedId - 1).title);
				System.out.printf("내용 : %s\n", articles.get(inputedId - 1).body);

			}
			// 검색
			else if (command.startsWith("article search ")) {
				String[] commandBits = command.split(" ");
				String inputedKeyword = commandBits[2];

				int inputedPage = 1;

				if (commandBits.length >= 4) {
					inputedPage = Integer.parseInt(commandBits[3]);
				}

				if (articles.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
					continue;
				}

				List<Article> searchedArticles = new ArrayList<Article>();

				for (Article article : articles) {
					if (article == null) {
						break;
					}
					if (article.title.contains(inputedKeyword)) {
						searchedArticles.add(article);
					}
				}

				int articlesInAPage = 10;
				int startPoint = searchedArticles.size() - 1;
				startPoint -= (inputedPage - 1) * articlesInAPage;
				int endPoint = startPoint - (articlesInAPage - 1);

				if (startPoint < 0) {
					System.out.printf("%d 페이지가 없습니다.\n", inputedPage);
					continue;
				}
				if (endPoint < 0) {
					endPoint = 0;
				}

				System.out.println("== 검색된 게시물 리스트 ==");
				System.out.println("번호 / 제목");

				for (int i = startPoint; i >= endPoint; i--) {
					System.out.printf("%d / %s\n", searchedArticles.get(i).id, searchedArticles.get(i).title);
				}
			}
			// 멤버 추가
			else if (command.equals("sign up")) {
				System.out.println("== 신규 멤버 가입 ==");

				int memberNumber = lastMamberNumber + 1;

				String memberName;
				String memberAge;
				String memberId;
				String memberPw;

				lastMamberNumber = memberNumber;
				System.out.printf("이름 입력) ");
				memberName = sc.nextLine();
				System.out.printf("나이 입력) ");
				memberAge = sc.nextLine();
				System.out.printf("ID 입력) ");
				memberId = sc.nextLine();
				System.out.printf("PW 입력) ");
				memberPw = sc.nextLine();

				members.add(new Member(memberNumber, memberName, memberAge, memberId, memberPw));

				System.out.printf("%s님 회원가입 완료\n", memberId);
				System.out.printf("이름 : %s\n", members.get(memberNumber - 1).memberName);
				System.out.printf("나이 : %s\n", members.get(memberNumber - 1).memberAge);
				System.out.printf("ID : %s\n", members.get(memberNumber - 1).memberId);
				System.out.printf("PW : %s\n", members.get(memberNumber - 1).memberPw);

			}
			// 멤버 로그인
			else if (command.equals("login")) {

				if (loginedMembersIndex == 1) {
					System.out.println("로그아웃 먼저 해주세요.");
					continue;
				}

				if (members.size() == 0) {
					System.out.println("현재 등록된 회원이 없습니다.");
					continue;
				}

				System.out.println("== 로그인 ==");

				System.out.printf("ID : ");
				String inputedID = sc.nextLine();

				System.out.printf("PW : ");
				String inputedPW = sc.nextLine();

				for (Member member : members) {
					if (member.memberId.equals(inputedID) && member.memberPw.equals(inputedPW)) {
						loginedMembers.add(member);
						loginedMembersIndex = 1;
						System.out.println("로그인 되었습니다.");
					} else {
						System.out.println("로그인 정보가 일치하지 않습니다.");
						continue;
					}
				}
			}
			// 로그인 회원 정보 조회

			else if (command.equals("whoami")) {

				if (loginedMembersIndex == 0) {
					System.out.println("로그인 해주세요");
					continue;
				}

				System.out.printf("ID : %s\n", loginedMembers.get(loginedMembersIndex - 1).memberId);
				System.out.printf("이름 : %s\n", loginedMembers.get(loginedMembersIndex - 1).memberName);

			}
			// 로그아웃

			else if (command.equals("logout")) {
				loginedMembersIndex = 0;
				System.out.println("로그아웃 되었습니다.");
			}

			// 종료
			else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

		}
		sc.close();

	}

}
