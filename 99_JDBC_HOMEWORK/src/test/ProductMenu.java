package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ProductMenu {

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rset = null;
	static int result = 0;
	static boolean flag = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.println("1. SELECT");
			System.out.println("2. INSERT");
			System.out.println("3. DELETE");
			System.out.println("4. 종료");
			System.out.print("메뉴 입력 : ");

			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				select();
				break;

			case 2:
				insert();
				break;

			case 3:
				delete();
				break;

			case 4:
				System.out.println("바이바이");
				return;

			default:

			}
		}
	}

	// select 구문
	public static void select() {

		flag = true;
		String sql = "SELECT * FROM PRODUCT";
		trywith(sql);

	}

	// insert 구문
	public static void insert() {
		Scanner sc = new Scanner(System.in);

		// System.out.print("PNO : ");
		// int pno = sc.nextInt();

		System.out.print("PNAME : ");

		String pname = sc.nextLine();
		System.out.print("PRICE : ");
		int price = sc.nextInt();

		sc.nextLine();

		String sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, '" + pname + "'," + price + ", SYSDATE)";
		trywith(sql);
	}

	// DELETE 구문
	public static void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String pname = sc.nextLine();

		String sql = "DELETE FROM PRODUCT WHERE PNAME = '" + pname + "'";
		trywith(sql);
	}

	// UPDATE, DELETE 예외
	public static void trywith(String sql) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// Statement 객체 생성
			stmt = conn.createStatement();

			// sql문 전달
			result = stmt.executeUpdate(sql);

			// 트랜젝션 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

			if (flag == true) {
				rset = stmt.executeQuery(sql);

				while (rset.next()) {
					int pno = rset.getInt("PNO");
					String pname = rset.getString("PNAME");
					int price = rset.getInt("PRICE");
					Date pdate = rset.getDate("REG_DATE");

					System.out.println(pno + ", " + pname + ", " + price + ", " + pdate);

				}

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (flag == true) {
					rset.close();
				}
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
}