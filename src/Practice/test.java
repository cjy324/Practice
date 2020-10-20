package Practice;

public class test {

	public static void main(String[] args) {
		new practice().run2();
	}
}

class practice {

	int[] nums = new int[5]; // nums배열의 크기는 5으로 지정되어 있음
								// 하지만 배열 내 몇개의 칸에 내용이 들어있는지 알 수 없음
	int numsSize = 0; // 배열 내 현재 들어간 내용이 몇개인지 알 수 있는 지표 생성

	public void add(int num) {
		System.out.printf("숫자 %d 추가\n", num);
		if (numsSize >= nums.length) { // 만약, 새로 생성을 요청하는 시기에
			System.out.println("Array is full !!!"); // 배열 내 칸 수가 모두 찼다면 경고문
			return; // return으로 더 이상의 진행을 차단
		}
		// num이라는 숫자 입력 시 nums 배열 numsSize칸에 입력값(num)이 들어감
		nums[numsSize] = num;
		numsSize++; // numsSize가 1씩 증가하면서 배열의 다음 칸으로 입력 칸이 지정됨

	}

	public void run2() {

		add(10);
		add(20);
		add(30);
		add(40);
		add(50);
		printArrayDump();

		remove(1); // 20이란 값을 삭제하기 위해 1번 배열을 선택하는 것
		int indexOf20 = getIndexOf(20); // 20의 인덱스가 뭔지 알려주는 변수
		removeByValue(30); // 30값의 인덱스를 찾고 지우는 것까지 자동으로 실행하는 메소드
		printArrayDump();
		remove(1);
		// add(60);
		printArrayDump();

	}

	public void removeByValue(int value) {
		int index = getIndexOf(value);  

		if (index != -1) {  //index값이 -1이 아닐때만 remove를 실행
			remove(index);

		}
	}

	public int getIndexOf(int num) {
		for (int i = num; i < nums.length; i++) {
			if (num == nums[i]) { // 입력값(num)과 일치하는 nums 배열 안의 i번 nums를 찾겠다
				return i; // 만약 입력값이 nums[i]값과 일치하면 그때의 i값을 리턴
			}
		}
		return -1; // nums.length까지 찾아봐도 일차하는 값이 없으면 -1값을 리턴
	} // 리턴값이 -1인 이유 : 배열은 0부터 시작하고 끝날때 칸 수가 뭔진 정확히 알 수 없지만 무조건 양수일 것이기 때문

	public void remove(int index) {
		System.out.printf("%d번 배열 삭제\n", index);
		for (int i = index + 1; i < numsSize; i++) { // 범위를 numsSize(현재까지 생성된 칸 수)까지 설정해주어야 함
														// 범위를 nums.length까지로 설정하면 ??????
			nums[i - 1] = nums[i]; // 입력된 값(index) 칸에 있던 내용을 그 다음 칸 배열로 덮어써서 지우는 개념

		}
		numsSize--;
	}

	public void printArrayDump() { // 현재 배열을 상태를 보고 싶을때
		System.out.println("== 배열 덤프 시작 ==");
		for (int i = 0; i < nums.length; i++) {
			System.out.printf("nums[%d] : %d\n", i, nums[i]);
		}
		System.out.println("== 배열 덤프 종료 ==");

	}

}