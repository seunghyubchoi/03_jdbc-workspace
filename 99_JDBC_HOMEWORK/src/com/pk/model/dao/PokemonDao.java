package com.pk.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
		Statement stmt = null;

		String sql = "INSERT INTO POKEMON VALUES (SEQ_PKNO.NEXTVAL, '" + p.getPkName() + "'" + ", '" + p.getPkType()
				+ "'" + ", '" + p.getPkClass() + "'" + ", " + p.getPkHeight() + ", " + p.getPkWeight() + ", '"
				+ p.getPkDetail() + "')";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

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
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM POKEMON";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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
				stmt.close();
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
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT DISTINCT PKTYPE FROM POKEMON";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

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
				stmt.close();
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
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM POKEMON WHERE PKTYPE = '" + type + "'";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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
				stmt.close();
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
		Statement stmt = null;
		ResultSet rset = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();

			String sql = "SELECT * FROM POKEMON WHERE PKNAME LIKE '%" + name + "%'";

			rset = stmt.executeQuery(sql);

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
				stmt.close();
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
		Statement stmt = null;

		String sql = "UPDATE POKEMON " + "SET PKHEIGHT = " + p.getPkHeight() + ", PKWEIGHT = " + p.getPkWeight()
				+ ", PKDETAIL = '" + p.getPkDetail() + "'" + "WHERE PKNAME = '" + p.getPkName() + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

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
		return result;
	}

	public int deletePokemon(String name) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		String sql = "DELETE FROM POKEMON WHERE PKNAME = '" + name + "'";
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
			return result;
		}
		

		
	}
}
