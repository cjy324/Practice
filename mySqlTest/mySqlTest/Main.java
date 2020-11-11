package mySqlTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
			
		Connection conn = null;
		Statement state = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");   //드라이버
			//미리 선언해둔 드라이버 경로를 전달, 해당 클래스(드라이버)가 없으면 
			//ClassNotFoundException을 Throw한다. 
			//그래서 try~catch로 핸들링해줘야 한다.
			//성공하면 DriverManager에 해당 드라이버가 등록되어
			//DB를 사용할 수 있다.
			
			String url = "jdbc:mysql://localhost/a1";  //접속할 DB서버 주소

			conn = DriverManager.getConnection(url, "sbsst", "sbs123414");   //DB url, 사용자명, 사용자비번
			//getConnection 메소드에 DB url, 사용자명, 비밀번호를 전달하여
			//해당 경로의 DB에 해당 사용자로 접속한 정보를 conn에 저장
			//여기에도 연결에  실패하면 SQLException이  발생
			System.out.println("=== 연결성공 ===");

			state = conn.createStatement();
			//SQL문을 실행하기 위해서는 conn 연결정보를 state로 생성해야한다.
			// Statement 객체를 반환
			//여기에도 SQLException이 발생할 수 있음
			//state 변환에 성공하면
			//Statement의 executeQuery() 메소드로 SQL문을 실행 할 수 있다!

			String sql;
			sql = "SELECT * FROM article";
			ResultSet rs = state.executeQuery(sql);
			//statement 객체의 executeQuery 메소드를 통해 SQL문을 실행시킬 수 있음
			//위 에서는 sql 이라는 문자열 변수를 생성하여 사용했지만
			//executeQuery("SELECT * FROM student");
			//이런식으로 바로 입력해도 무방
			
			//테이블의 모든 데이터를 ResultSet형 rs 변수에 저장
			//질의의 결과가 rs에 저장

			//while문을 통해 결과의 갯수만큼 반복하며
			//데이터를 하나씩 가져오는 작업을 진행

			
			System.out.println("=== 로딩결과 ===");
			while (rs.next()) {
				String id = rs.getString("id");
				String regDate = rs.getString("regDate");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				String hit = rs.getString("hit");
				System.out.printf("id : %s\nregDate : %s\ntitle : %s\nbody : %s\nnickname : %s\nhit : %s\n----------\n",
						id, regDate, title, body, nickname, hit);
				
				//rs.getString("id");
				//SELETE로 가져온 데이터 중 속성이 id 라는 데이터를
				//String 데이터로 반환하는 문장

				//getString() 메소드는
				//매개변수로 선택 할 속성을 문자열로 전달하면 String 형태로 반환해줌				
				
			}
			rs.close();
			state.close();
			conn.close();
			//while문이 끝나면 rs와 state, conn을
			//close 메소드로 꼭 닫아줘야 함
			//자원해제 개념
			

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			//Class.forName("com.mysql.cj.jdbc.Driver") 경로에 드라이버가 없다면
			//DB를 연결할 수 없다.
			//try ~ catch로 설정해줘야 실패를 확인할 수 있는 사전장치
			
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
			//getConnection 메소드에 DB url, 사용자명, 비밀번호를 전달시
			//연결이 불가하면 에러 메시지 확인하는 사전장치
			
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//그리고 finally 블럭 안에서 혹시모를 상황을 대비하여
		//conn이 NULL 이 아니면 close로 닫아주고
		//state가 NULL이 아니면 close로 닫아준다
		
		//이는 확실하게 닫아주기 위해 필요한 로직

		//만약
		//getConnection은 성공하여 conn에 데이터를 저장하였는데
		//getStatement에서 예외가 발생하여 중간에서 흐름이 끊기게 되면
		//conn은 닫혀지지 않은 상태가 되기 때문에
		//마지막으로 한번 더 확인하여 닫아주어야 함

	}

}