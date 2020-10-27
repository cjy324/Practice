package Test;

import java.util.Calendar;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int count = sc.nextInt();

		캐릭터[] 캐릭터들 = new 캐릭터[count]; // 캐릭터 클래스의 캐릭터들이라는 배열을 생성
										// 배열 길이는 입력값

		for (int i = 0; i < count; i++) { // 배열 길이까지 반복
			int 번호 = i + 1;
			String 이름 = sc.next();
			String 태어난해 = sc.next();
			String 직업 = sc.next();

			태어난해 = 태어난해.replace("년", ""); // String replace(CharSequnce target, CharSequence replacement)
											// Replace 함수는 자신이 바꾸고싶은 문자로 문자열을 치환시켜주는 기능을 함

			캐릭터 a캐릭터 = null; // a캐릭터라는 객체 생성

			if (직업.equals("의적")) {
				a캐릭터 = new 의적();
			} else if (직업.equals("도적")) {
				a캐릭터 = new 도적();
			} else if (직업.equals("상인")) {
				a캐릭터 = new 상인();
			}

			a캐릭터.번호 = 번호;
			a캐릭터.이름 = 이름;
			a캐릭터.태어난해 = Integer.parseInt(태어난해);
			캐릭터들[i] = a캐릭터; // 캐릭터들[] 배열에 a캐릭터라는 객체 정보 배정

		}

		for (int i = 0; i < count; i++) {
			String 능력치_문장 = sc.next();
			String[] 능력치들 = 능력치_문장.split(","); // ,쉼표로 구분된 능력치값을 받기위한 사전 조치
												// 능력치들이라는 배열 생성

			int 힘 = Integer.parseInt(능력치들[0]);
			int 지능 = Integer.parseInt(능력치들[1]);
			int 민첩 = Integer.parseInt(능력치들[2]);

			캐릭터들[i].힘 = 힘;
			캐릭터들[i].지능 = 지능;
			캐릭터들[i].민첩 = 민첩;

		}

		sc.close();

		for (int i = 0; i < 캐릭터들.length; i++) {
			캐릭터들[i].자기소개();
			캐릭터들[i].공격();
		}

	}

}

abstract class 캐릭터 { // 추상 클래스(abstract class)란 하나 이상의 추상 메소드(abstract method)를 포함하는 클래스이다.
						// 추상 메소드는 선언만 있고 본체는 없는 함수이며 선언부에 ‘abstract’ 라는 키워드를 붙인다.
						// 추상 메소드가 포함되었다면 클래스도 추상 클래스이므로 클래스명 앞에도 ‘abstract’키워드를 붙여야 한다.
						// 추상 클래스는 추상 메서드를 포함하고 객체화 할 수 없다는 점만 제외하고 일반 클래스와 다르지 않으며
						// 생성자, 멤버변수와 일반 메서드도 가질 수 있다.
						// 추상 클래스 자체로는 클래스로의 역할을 하지 못하며 객체를 생성할 수 없지만
						// 새로운 클래스를 작성하는데 있어서 부모 클래스로서 중요한 역할을 갖는다.
						// 이를 상속받는 자식 클래스에서는 추상 메소드의 구체적인 본체를 가질 수 있다.
	int 번호;
	String 이름;
	int 태어난해;
	String 직업;
	int 힘;
	int 지능;
	int 민첩;

	무기 a무기;

	void 자기소개() {
		System.out.printf("== 자기소개 시작 ==\n");
		System.out.printf("번호 : %d번\n", 번호);
		System.out.printf("이름 : %s\n", 이름);
		System.out.printf("캐릭터 종류 : %s\n", 직업);
		System.out.printf("태어난 해 : %04d년\n", 태어난해);
		System.out.printf("나이 : %d살\n", get나이());
		System.out.printf("힘 : %d\n", 힘);
		System.out.printf("지능 : %d\n", 지능);
		System.out.printf("민첩 : %d\n", 민첩);
		System.out.printf("== 자기소개 끝 ==\n");

	}

	int get나이() {
		int 올해 = Calendar.getInstance().get(Calendar.YEAR);
		return 올해 - 태어난해;
	}

	void 공격() {
		System.out.println("== 공격 시작 ==");
		a무기.작동(이름, 직업, 힘, 민첩);
		System.out.println("== 공격 끝 ==");
	}

}

class 의적 extends 캐릭터 {
	의적() {
		직업 = "의적";
		a무기 = new 검();
	}

}

class 도적 extends 캐릭터 {
	도적() {
		직업 = "도적";
		a무기 = new 도끼();

	}

}

class 상인 extends 캐릭터 {
	상인() {
		직업 = "상인";
		a무기 = new 지팡이();

	}

}

class 무기 {

	String 이름;
	int 공격력;

	void 작동(String 사용자_이름, String 사용자_직업, int 사용자_힘, int 사용자_민첩) {
		System.out.printf("- 직업이 %s인 %s(이)가 %s(으)로 공격합니다.\n", 사용자_직업, 사용자_이름, 이름);
		System.out.printf("-무기공격력 : %d\n", 공격력);
		System.out.printf("-데미지 : %d\n", 공격력 * 사용자_힘 * 사용자_민첩);

	}

}

class 검 extends 무기 {
	검() {
		이름 = "검";
		공격력 = 10;
	}
}

class 도끼 extends 무기 {
	도끼() {
		이름 = "도끼";
		공격력 = 15;
	}
}

class 지팡이 extends 무기 {
	지팡이() {
		이름 = "지팡이";
		공격력 = 2;
	}
}