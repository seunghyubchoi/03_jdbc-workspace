package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.model.vo.Member;

// DAO (Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL 실행 후 결과 받기(JDBC)

//						      결과를 Controller로 다시 리턴

public class MemberDao {

	/*
	 * Statement와 PreparedStatement의 특징 - 둘다 sql문 실행하고 결과를 받아내는 객체(둘 중 하나 쓰면 됨)
	 * 
	 * - Statement와 PreparedStatement의 차이점
	 * 
	 * - Statement 같은 경우는 sql문을 바로 전달하면서 실행시키는 객체 (즉, sql문을 완성 형태로 만들어 줘야함, 사용자가 입력한
	 * 값이 다 채워진 형태로!!!!)
	 * 
	 * 
	 * > 기존의 Statement 방식 1) Connection이라는 객체를 통해 Statement 객체 생성 :
	 * conn.CreateStatement(); 2) Statement 객체를 통해 "완성된 sql문" 실행 및 결과 받기 : 결과 =
	 * stmt.executeUpdate(완성된 sql문 들어갔어야 함)
	 * 
	 * - PreparedStatemet 같은 경우는 미완성된 sql문을 잠시 보관해 둘 수 있는 객체 (즉, 사용자가 입력한 값들을 채워주지
	 * 않고 각각 들어갈 공간을 확보만 미리 해놓아도 된다!!) 단, 해당 sql문 본격적으로 실행하기 전에는 빈 공간을 사용자가 입력한 값으로
	 * 채워서 실행하긴 해야함
	 * 
	 * 
	 * 
	 * > PreparedStatement 방식 
	 * 1) Connection 객체를 통해 PreparedStatemet 객체 생성 : pstmt =
	 * conn.preparedStatement([미완성된] sql문) 
	 * 2) 미완성 상태인 sql문을 완성시켜야 함 :
	 * pstmt.setXXX(1, "대체할 값"); 3) 해당 완성된 sql문 실행 결과 받기 : 결과 = pstmt.executeXXX();
	 */

	/**
	 * 회원을 추가하는 메소드
	 * 
	 * @param m
	 * @return
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된 행수 (int) => 트랜잭션 처리
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		// 실행할 sql문(미완성된 형태로 둘 수 있음)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX,
		// 'XXXX', 'XXXXXXXXXXX', 'XXXX', 'XXXX', SYSDATE)
		String sql = " INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// 1) jdbc 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) connection 객체
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3) PreparedStatement
			pstmt = conn.prepareStatement(sql);
			// 애초에 pstmt 객체 생성시 sql문 담은채로 생성! 지금은 미완성

			// 빈공간을 실제 값(사용자가 입력한 값)으로 채워준 후 실행
			// pstmt.setString(홀더 순번, 대체할 값) => '대체할 값' (홑따옴표 감싸준 상태로 알아서 들어감)
			// pstmt.setInt(홀더순번, 대체할 값) => 대체할 값
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			// 4,5) sql문 실행 및 결과 받기

			result = pstmt.executeUpdate(); // 여기서는 sql문 전달하지 않고 그냥 실행해야함!! 이미 pstmt에 sql이 들어가 있음

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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;

	}

	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 * @return
	 */
	public ArrayList<Member> selectList() {
		// select문 (여러 행) => ResultSet객체 => ArrayList
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql); // 애초에 완성된 sql문 담았음
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 현재 rset이 참조하고 있는 해당 행의 모든 컬럼값 뽑아서 => 한 Member 객체에 담기 => 리스트 차곡차곡 추가
				list.add(new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("email"), rset.getString("phone"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate")));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * 사용자의 아이디로 회원검색 요청 처리해주는 메소드
	 * @param userId
	 * @return
	 */
	public Member selectByUserId(String userId) {
		Member m = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			
			
			
			
			if (rset.next()) {
				m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),
						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),
						rset.getString("phone"), rset.getString("email"), rset.getString("address"),
						rset.getString("hobby"), rset.getDate("enrolldate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
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
		
		// 여러 명이 올 수 있으니 list
		ArrayList<Member> list = new ArrayList<Member>();
		//ArrayList<Member> list = null; => 이 방법은 안 됨~~~~
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		// 해결 방법 1
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		
		// 해결 방법 2 = 내가한 방법
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			
			
			// 여러 행일 수도 있고 한 행일 수도 있다!!!
			
			
			while (rset.next()) {
				// list = new ArrayList<Member>(); 
				// 위 구문을 넣어주면 마지막에 담기는 정보만 list에 담기게 될 것
				Member m = new Member(rset.getInt("userno"),
									  rset.getString("userid"), 
									  rset.getString("userpwd"),
						
									  rset.getString("username"), 
									  rset.getString("gender"), 
									  rset.getInt("age"),
						
									 
									  rset.getString("email"), 
									  rset.getString("phone"), 
									  rset.getString("address"),
						
									  rset.getString("hobby"), 
									  rset.getDate("enrolldate"));
		
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}

	
	/**
	 * 사용자 정보를 업데이트 해주는 메소드
	 * @param m
	 * @return
	 */
	public int updateMember(Member m) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			
			
			// 항상 순서를 잘 지키기
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			result = pstmt.executeUpdate();
			
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
}
