package com.pk.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.pk.common.JDBCTemplate.*;
import com.pk.model.vo.Pokemon;
import com.pk.model.vo.Trainer;

public class PokemonDao {

	private Properties prop = new Properties();

	public PokemonDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 트레이너 메뉴로 로그인하는 메소드
	 * 
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	public int loginMenu(Connection conn, String userId, String userPwd) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("loginMenu");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int adminLoginMenu(Connection conn, String userId, String userPwd) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("adminLoginMenu");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "admin");
			pstmt.setString(2, "admin");

			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public String displayTrainerName(Connection conn, String userId) {
		String name = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("displayTrainerName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				name = rset.getString("TRNAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return name;
	}

	public ArrayList<Pokemon> displayMyPokemon(Connection conn, String userId) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("displayMyPokemon");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				Trainer t = new Trainer();
				t.setTrNo(rset.getInt("TRNO"));
				t.setTrId(rset.getString("TRID"));
				t.setTrPwd(rset.getString("TRPWD"));
				t.setTrName(rset.getString("TRNAME"));

				Pokemon p = new Pokemon(rset.getInt("PKNO"), rset.getString("PKNAME"), rset.getString("PKTYPE"),
						rset.getString("PKCLASS"), rset.getDouble("PKHEIGHT"), rset.getDouble("PKWEIGHT"),
						rset.getString("PKDETAIL"), rset.getInt("TRNO"));

				list.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);

		}

		return list;
	}

	/**
	 * 사용자가 직접 입력하여 포켓몬을 추가하는 메소드
	 * 
	 * @param p
	 * @return result
	 */
	public int insertPokemon(Connection conn, Pokemon p) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertPokemon");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPkName());
			pstmt.setString(2, p.getPkType());
			pstmt.setString(3, p.getPkClass());
			pstmt.setDouble(4, p.getPkHeight());
			pstmt.setDouble(5, p.getPkWeight());
			pstmt.setString(6, p.getPkDetail());
			pstmt.setInt(7, p.getTrNo());
			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 사용자에게 등록된 모든 포켓몬을 보여주는 메소드
	 * 
	 * @return list
	 */
	public ArrayList<Pokemon> searchAll(Connection conn) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchAll");

		try {
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
				p.setTrNo(rset.getInt("TRNO"));

				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 데이터에 등록된 전체 타입을 보여주는 메소드
	 */
	public ArrayList<String> searchByType(Connection conn) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchByType");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String pkType = rset.getString("PKTYPE");

				list.add(pkType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	/**
	 * 분류별로 분류하는 메소드
	 * 
	 * @param conn
	 * @return
	 */
	public ArrayList<String> searchByClass(Connection conn) {
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("searchByClass");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String pkClass = rset.getString("PKCLASS");

				list.add(pkClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return list;
	}

	/**
	 * 사용자로부터 타입을 입력받아 입력된 타입만 보여주는 메소드
	 * 
	 * @param type
	 * @return
	 */
	public ArrayList<Pokemon> displayByType(Connection conn, String type) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("displayByType");

		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	/**
	 * 사용자로부터 클래스를 입력받아 입력된 클래스만 보여주는 메소드
	 * 
	 * @param pkClass
	 * @return
	 */
	public ArrayList<Pokemon> displayByClass(Connection conn, String pkClass) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("displayByClass");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pkClass);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	/**
	 * 포켓몬의 이름을 입력받는 메소드
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<Pokemon> inputPokemonName(Connection conn, String name) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("inputPokemonName");
		try {

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	/**
	 * 포켓몬 정보 업데이트 메소드
	 * 
	 * @param p
	 * @return
	 */
	public int updatePokemon(Connection conn, Pokemon p) {
		int result = 0;
		PreparedStatement pstmt = null;
		/*
		 * String sql = "UPDATE POKEMON " + "SET PKHEIGHT = " + p.getPkHeight() +
		 * ", PKWEIGHT = " + p.getPkWeight() + ", PKDETAIL = '" + p.getPkDetail() + "'"
		 * + "WHERE PKNAME = '" + p.getPkName() + "'";
		 */
		String sql = prop.getProperty("updatePokemon");

		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 포켓몬 정보 삭제 메소드
	 * 
	 * @param conn
	 * @param name
	 * @return
	 */
	public int deletePokemon(Connection conn, String name) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deletePokemon");
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public ArrayList<Pokemon> displayRandomPokemon(Connection conn) {
		ArrayList<Pokemon> list = new ArrayList<Pokemon>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("displayRandomPokemon");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Pokemon p = new Pokemon(rset.getInt("PKNO"), rset.getString("PKNAME"), rset.getString("PKTYPE"),
						rset.getString("PKCLASS"), rset.getDouble("PKHEIGHT"), rset.getDouble("PKWEIGHT"),
						rset.getString("PKDETAIL"), rset.getInt("TRNO"));

				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}
}
