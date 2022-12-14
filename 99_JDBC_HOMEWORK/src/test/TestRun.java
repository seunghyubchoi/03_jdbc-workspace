package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
		
		
		
		
		
		
		
		
		
		Scanner sc = new Scanner(System.in);
		while(true) {
		
		
	
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
	
	
	// combine 구문
	public static void InsOrDel() {
		boolean trueFalse = true;
		if (trueFalse == true) {
			
		} else {
			
		}
	}
	
	
	
	// select 구문 
	public static void select() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		DecimalFormat decFormat = new DecimalFormat("###,###");
		
		
		String sql = "SELECT * FROM PRODUCT";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while(rset.next()) {
				int pno = rset.getInt("PNO");
				String pname = rset.getString("PNAME");
				int price = rset.getInt("PRICE");
				String newPrice = decFormat.format(price);
				//String lastPrice = String.format("$%,d", newPrice);
				Date pdate = rset.getDate("REG_DATE");
				
				System.out.println(pno + ", " + pname + ", " + newPrice + ", " + pdate);

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
	
	
	// insert 구문
	public static void insert() {
		Scanner sc = new Scanner(System.in);
		
		//System.out.print("PNO : ");
		//int pno = sc.nextInt();

		System.out.print("PNAME : ");
		
		String pname = sc.nextLine();
		System.out.print("PRICE : ");
		int price = sc.nextInt();
		
		sc.nextLine();
		
		
	
		String sql = "INSERT INTO PRODUCT VALUES(SEQ_PNO.NEXTVAL, '" + pname + "'," + price + ", SYSDATE)";

		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		
		
		// jdbc driver 등록
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (result > 0) {
			System.out.println(pname +"(이)가 추가 되었습니다");
		} else {
			System.out.println("insert 실패");
		}
	}
	
	// DELETE 구문
	
	public static void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String pname = sc.nextLine();
		
		String sql = "DELETE FROM PRODUCT WHERE PNAME = '" + pname + "'";
		
		
		
		
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (result > 0) {
			System.out.println("delete 성공");
		} else {
			System.out.println("delete 실패");
		}
		
		
	}
	

	// try~with~resources 구문
	public static void dml() {
		
		
	}
	

}
