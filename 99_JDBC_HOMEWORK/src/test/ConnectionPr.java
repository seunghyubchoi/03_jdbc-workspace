package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectionPr {

	public static void main(String[] args) {
		// 사용할 객체 및 변수 셋팅

		// 스캐너
		Scanner sc = new Scanner(System.in);

		// JDBC 객체 (Connection, Statement, ResultSet 모두 java.sql)
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		// 일반변수
		String pname = null;
		String sql = null;
		int result = 0;
		boolean dml = true;
		String type = null;
		int price = 0;

		// 반복문
		while (true) {
			// 메뉴 출력 및 선택
			System.out.println("JDBC 메뉴");
			System.out.println("1.SELECT 조회");
			System.out.println("2.INSERT 추가");
			System.out.println("3.DELETE 삭제");
			System.out.println("4.종료");

			System.out.print(">> 메뉴를 선택해주세요 : ");
			int menu = sc.nextInt(); // 메뉴 선택할 때만 쓰니 전역변수로 만들 필요 없음, 1번만 쓰니까

			sc.nextLine();

			try {
				// 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
				Class.forName("oralce.jdbc.driver.OracleDriver");
				// 2) Connection 생성 : 연결하고자 하는 DB정보 입력해서 해당 DB와 연결하면서 생성
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "");
				// 3) Statement 생성 : Connection 객체를 이용해서 생성 (sql문 실행 및 결과를 받는 객체)
				stmt = conn.createStatement();

				if (menu == 1) {

				} else if (menu == 2) {
					dml = true;
					type = "insert";
					System.out.print("상품명 입력 : ");
					pname = sc.nextLine();

					System.out.print("상품가격 입력 : ");
					price = sc.nextInt();
					sc.nextLine();

					sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, '" + pname + "'," + price + ", SYSDATE)";
				} else if (menu == 3) {

				} else if (menu == 4) {

				}

				if (dml) {
					result = stmt.executeUpdate(sql);
					if (result > 0) {
						conn.commit();
						System.out.println(type + " 성공");
					} else {
						conn.rollback();
						System.out.println(type + " 실패");
					}
				} else {
					rset = stmt.executeQuery(sql);

					while (rset.next()) {
						int pno = rset.getInt("PNO");
						pname = rset.getString("PNAME");
						price = rset.getInt("PRICE");
						Date regdate = rset.getDate("REG_DATE"); // Sql Date 형식 불러와야합니다!

						System.out.println(pno + ", " + pname + ", " + price + ", " + regdate);
					}
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rset.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
