package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.model.vo.Member;

// DAO (Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL 실행 후 결과 받기(JDBC)

//						      결과를 Controller로 다시 리턴

public class MemberDao {
	// JDBC
	// Controller에서 요청을 받음

	/*
	 * * JDBC용 객체 - Connection : DB의 연결 정보를 담고 있는 객체 - [Prepared]Statement : 연결된 DB에
	 * SQL문 전달해서 실행하고 그 결과를 받아내는 객체 ********** - ResultSet : SELECT문 실행 후 조회된 결과물들이
	 * 담겨 있는 객체
	 * 
	 * * JDBC 과정 (순서가 매우 매우 중요) 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록 2)
	 * Connection 생성 : 연결하고자 하는 DB정보 입력해서 해당 DB와 연결하면서 생성 3) Statement 생성 :
	 * Connection 객체를 이용해서 생성 (sql문 실행 및 결과를 받는 객체) 4) sql문 전달하면서 실행 : Statement 객체를
	 * 이용해서 sql문 실행 5) 결과받기 > SELECT문 실행 => ResultSet 객체 (조회된 데이터들이 담겨 있음) => 6-1 >
	 * DML문 실행 => int (처리된 행 수) => 6-2
	 * 
	 * 6-1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 vo 객체에 주섬주섬 옮겨 담기 [ + 여러행 조회시에는 ArrayList에
	 * 차곡차곡 담기] 6-2) 트랜젝션 처리 (성공적으로 수행했으면 commit, 실패했으면 rollback)
	 * 
	 * 7) 다 사용한 JDBC 객체를 반드시!! 자원 반납!! 안하면 DB 락걸림! (close) => 생성된 역순으로!
	 */

	/**
	 * 사용자가 입력한 정보들을 추가 시켜주는 메소드
	 * 
	 * @param m : 사용자가 입력한 값들이 주섬주섬 담겨있는 Member 객체
	 * @return : insert문 수행 후 처리된 행수
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된 행수 (int) => 트랜잭션 처리

		// 필요한 변수를 먼저 셋팅
		int result = 0; // 처리된 결과, 처리된 행 수를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결 정보를 담는 객체
		Statement stmt = null; // "완성된 SQL(실제 값이 다 채워진 상태로)"문 전달해서 곧바로 실행 후 결과 받는 객체, SEQ문 주의하기

		// 실행할 SQL문 (!!!완성된 형태로 만들어두기!!!)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX,
		// 'XXXX', 'XXXXXXXXXXX', 'XXXX', 'XXXX', SYSDATE)

		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, " + "'" + m.getUserId() + "', " + "'"
				+ m.getUserPwd() + "', " + "'" + m.getUserName() + "', " + "'" + m.getGender() + "', " + m.getAge()
				+ ", " + "'" + m.getEmail() + "', " + "'" + m.getPhone() + "', " + "'" + m.getAddress() + "', " + "'"
				+ m.getHobby() + "', SYSDATE)";

		// System.out.println(sql);
		// 콘솔창에 나온 sql을 복사해서 sql developer에 붙여넣기하고 오타 및 따옴표 검사

		// 1) jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4,5) sql문 전달하면서 실행 후 결과 받기
			result = stmt.executeUpdate(sql);

			// 6) 트랜잭션 처리
			if (result > 0) { // 성공시 커밋
				conn.commit();
			} else { // 실패시 롤백
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7) 다 사용한 jdbc 객체 반납
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	/**
	 * 전체 조회
	 * 
	 * @return
	 */
	public ArrayList<Member> selectList() {
		// select문(여러행 조회) => 각각 ResultSet 객체 => ArrayList에 차곡차곡 담기

		// 필요한 변수들 셋팅
		ArrayList<Member> list = new ArrayList<Member>(); // 현재 상태는 텅 비어있는 상태
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // ResultSet은 Select문 실행시 조회된 결과값들이 최초로 담기는 객체

		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) Statement 생성
			stmt = conn.createStatement();

			// 4,5) sql 실행 및 결과 받기
			rset = stmt.executeQuery(sql);

			// 6) ResultSet으로 부터 데이터 하나씩 뽑아서 vo 객체에 주섬주섬 담고 +
			// list에 vo객체 추가

			while (rset.next()) {
				// 현재 rset의 커서가 가리키고 있는
				// 한 행의 데이터들을 싹다 뽑아서 Member 객체 주섬주섬 담기

				Member m = new Member();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m); // 리스트에 해당 회원 객체 차곡차곡 담기

			}

			// 반복문이 다 끝난 시점에
			// 만약에 조회된 데이터가 없었다면 list가 텅 빈 상태일 것
			// 만약에 조회된 데이터가 있었다면 list에 뭐라도 담겨 있을 것

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 자원 반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * 이름으로 조회 메소드
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<Member> selectList(String name) {
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER WHERE USERNAME = '" + name + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m);
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

		return list;

	}

	/**
	 * selectByUserId(String userId) 사용자의 아이디로 회원검색 요청 처리해주는 메소드
	 * 
	 * @param userId = 사용자가 입력한 검색하고자 하는 회원 아이디 값
	 * @return 검색된 결과가 있으면 Member 객체 | 결과가 없으면 null
	 */
	public Member selectByUserId(String userId) {
		Member m = null; // 조회 결과가 있을수도 있고 없을수도 있으니까

		// 사용할 변수 및 객체 값 셋팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		// 쿼리
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";

		// 드라이버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			if (rset.next()) { // rset.next()가 true가 됐다는 의미는 한 행이라도 존재한다는 뜻
				// 조회됐다면 해당 조회된 컬럼 값들을 쏙쏙 뽑아서 한 Member 객체의 각 필드에 담기
				// m에는 userId의 정보를 싹 다 가지고 있는 상태
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("phone"), rset.getString("email"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate"));

			}

			// 위의 조건문 다 끝난 시점에
			// 만약에 조회된 데이터가 없었을 경우 = m은 null
			// 만약에 조회된 데이터가 있었을 경우 = m은 생성 후 뭐라도 담겨있음

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
		return m;
	}

	/**
	 * 사용자의 이름으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 * @return
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		// select문 수행(여러행이 나올 수 있다고 생각해야됨) => ResultSet
		// 따라서 ArrayList<Member>로 짜야함
		ArrayList<Member> list = new ArrayList<Member>();

		// 변수 셋팅
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" + keyword + "%'";

		// 드라이버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();

			// 쿼리 실행 담기
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				
				/*
				 
				이전까지의 방식 
				 
				Member m = new Member();
				m.setUserNo(rset.getInt("userno"));
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				list.add(m);
				
				*/
				
				list.add(new Member(
						rset.getInt("userno"),
						rset.getString("userid"),
						rset.getString("userpwd"),
						rset.getString("username"),
						rset.getString("gender"),
						rset.getInt("age"),
						rset.getString("email"),
						rset.getString("phone"),
						rset.getString("address"),
						rset.getString("hobby"),
						rset.getDate("enrolldate")							
						));
				
				
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
		return list;

	}
	
	/**
	 * 사용자가 입력한 아이디의 정보 변경 요청 처리해주는 메소드
	 * @param m
	 * @return result : 처리된 행수
	 */
	public int updateMember(Member m) {
		// update문  => 처리된 행의 수가 리턴됨(int) => 트랜잭션 처리
		
		// 변수 셋팅
		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		// sql문
		String sql = "UPDATE MEMBER "
				   + "SET USERPWD = '" + m.getUserPwd()  + "'"
				   +     ", EMAIL = '" + m.getEmail()    + "'"
				   +     ", PHONE = '" + m.getPhone()    + "'"
				   +   ", ADDRESS = '" + m.getAddress()  + "'"
				   +   ", AGE = " 	   + m.getAge() 
				   +"WHERE USERID = '" + m.getUserId()   + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
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
		
		return result;
	}
	
	public int deleteMember(String userId) {
		// delete문 = > 처리된 행수(int) 반환, 반드시 트랜잭션 처리
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID ='" + userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
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
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
