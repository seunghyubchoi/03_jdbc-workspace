package com.pk.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.pk.model.vo.Pokemon;

public class PokemonDao {
	/**
	 * 사용자가 직접 입력하여 포켓몬을 추가하는 메소드
	 * 
	 * @param p
	 * @return result
	 */
	public int insertPokemon(Pokemon p) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
/*
		String sql = "INSERT INTO POKEMON VALUES (SEQ_PKNO.NEXTVAL, '" + p.getPkName() + "'" + ", '" + p.getPkType()
				+ "'" + ", '" + p.getPkClass() + "'" + ", " + p.getPkHeight() + ", " + p.getPkWeight() + ", '"
				+ p.getPkDetail() + "')";
*/
		
		String sql = "INSERT INTO POKEMON VALUES (SEQ_PKNO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPkName());
			pstmt.setString(2, p.getPkType());
			pstmt.setString(3, p.getPkClass());
			pstmt.setDouble(4, p.getPkHeight());
			pstmt.setDouble(5, p.getPkWeight());
			pstmt.setString(6, p.getPkDetail());
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
	 * 사용자에게 등록된 모든 포켓몬을 보여주는 메소드
	 * 
	 * @return list
	 */
	public ArrayList<Pokemon> searchAll() {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM POKEMON";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Pokemon p = new Pokemon();
				p.setPkNo(rset.getInt("PKNO"));
				p.setPkName(rset.getString("PKNAME"));
				p.setPkType(rset.getString("PKTYPE"));
				p.setPkClass(rset.getString("PKCLASS"));
				p.setPkHeight(rset.getDouble("PKHEIGHT"));
				p.setPkWeight(rset.getDouble("PKWEIGHT"));
				p.setPkDetail(rset.getString("PKDETAIL"));

				list.add(p);
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
	 * 데이터에 등록된 전체 타입을 보여주는 메소드
	 */
	public ArrayList<String> searchByType() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT DISTINCT PKTYPE FROM POKEMON";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String pkType = rset.getString("PKTYPE");

				list.add(pkType);
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
	
	public ArrayList<String> searchByClass() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT DISTINCT PKCLASS FROM POKEMON";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String pkClass = rset.getString("PKCLASS");

				list.add(pkClass);
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
	
	
	public ArrayList<Pokemon> displayByClass(String pkClass) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM POKEMON WHERE PKClass = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pkClass);
			rset = pstmt.executeQuery();
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
	 * 사용자가 선택한 타입만 보여주는 메소드
	 * 
	 * @param type
	 * @return
	 */
	public ArrayList<Pokemon> displayByType(String type) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM POKEMON WHERE PKCLASS = ?";

		
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Pokemon p = new Pokemon();
				p.setPkNo(rset.getInt("PKNO"));
				p.setPkName(rset.getString("PKNAME"));
				p.setPkType(rset.getString("PKTYPE"));
				p.setPkClass(rset.getString("PKCLASS"));
				p.setPkHeight(rset.getDouble("PKHEIGHT"));
				p.setPkWeight(rset.getDouble("PKWEIGHT"));
				p.setPkDetail(rset.getString("PKDETAIL"));

				list.add(p);
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
	 * 포켓몬의 이름을 입력받는 메소드
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<Pokemon> inputPokemonName(String name) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM POKEMON WHERE PKNAME LIKE ?";
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Pokemon p = new Pokemon();
				p.setPkNo(rset.getInt("PKNO"));
				p.setPkName(rset.getString("PKNAME"));
				p.setPkType(rset.getString("PKTYPE"));
				p.setPkClass(rset.getString("PKCLASS"));
				p.setPkHeight(rset.getDouble("PKHEIGHT"));
				p.setPkWeight(rset.getDouble("PKWEIGHT"));
				p.setPkDetail(rset.getString("PKDETAIL"));

				list.add(p);
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
	 * 포켓몬 정보 업데이트 메소드
	 * 
	 * @param p
	 * @return
	 */
	public int updatePokemon(Pokemon p) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
/*
		String sql = "UPDATE POKEMON " + "SET PKHEIGHT = " + p.getPkHeight() + ", PKWEIGHT = " + p.getPkWeight()
				+ ", PKDETAIL = '" + p.getPkDetail() + "'" + "WHERE PKNAME = '" + p.getPkName() + "'";
*/
		String sql = "UPDATE POKEMON SET PKHEIGHT = ?, PKWEIGHT = ?, PKDETAIL = ? WHERE PKNAME = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);

			pstmt.setDouble(1, p.getPkHeight());
			pstmt.setDouble(2, p.getPkWeight());
			pstmt.setString(3, p.getPkDetail());
			pstmt.setString(4, p.getPkName());
			
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

	public int deletePokemon(String name) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM POKEMON WHERE PKNAME = ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
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
