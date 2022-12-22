package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

public class MemberDao2 {

	public int loginMenu(String userId, String userPwd) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND USERPWD = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			result = pstmt.executeUpdate();

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
			conn = JDBCTemplate.getConnection();

			// 3) PreparedStatement
			pstmt = conn.prepareStatement(sql);
			// 애초에 pstmt 객체 생성시 sql문 담은채로 생성! 지금은 미완성
			// 여기서 오류가 나면 pstmt는 여전히 null

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

			// 여기서 sqlException 날수도 있기 때문에 뒤에 커밋과 롤백이 있으면 실행이 안됨!
			// 문제가 생기면 무조건 롤백을 해야함

		}  catch (SQLException e) {
			e.printStackTrace();
		}

		// 원래는 이렇게 했어야 함

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// 여기서 반납
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return result;

	}

	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 * 
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
	 * 
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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
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
	 * 
	 * @param keyword
	 * @return
	 */
	public ArrayList<Member> selectByUserName(String keyword) {

		// 여러 명이 올 수 있으니 list
		ArrayList<Member> list = new ArrayList<Member>();
		// ArrayList<Member> list = null; => 이 방법은 안 됨~~~~

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 해결 방법 1
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";

		// 해결 방법 2 = 내가한 방법
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();

			// 여러 행일 수도 있고 한 행일 수도 있다!!!

			while (rset.next()) {
				// list = new ArrayList<Member>();
				// 위 구문을 넣어주면 마지막에 담기는 정보만 list에 담기게 될 것
				Member m = new Member(rset.getInt("userno"), rset.getString("userid"), rset.getString("userpwd"),

						rset.getString("username"), rset.getString("gender"), rset.getInt("age"),

						rset.getString("email"), rset.getString("phone"), rset.getString("address"),

						rset.getString("hobby"), rset.getDate("enrolldate"));

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
	 * 
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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);

			// 항상 순서를 잘 지키기
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			result = pstmt.executeUpdate();

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

	public void updateMember(Connection conn, Member m) {
	}
}
