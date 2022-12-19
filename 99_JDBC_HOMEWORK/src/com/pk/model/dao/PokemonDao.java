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
	 * @param p
	 * @return result
	 */
	public int insertPokemon(Pokemon p) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO POKEMON VALUES (SEQ_PKNO.NEXTVAL, '" + p.getPkName()   + "'"
											 + ", '" + p.getPkType()   + "'"
											 + ", '" + p.getPkClass()  + "'"
											 + ", " + p.getPkHeight()  
											 + ", " + p.getPkWeight()   
											 + ", '" + p.getPkDetail() + "')";
		System.out.println(sql);
											   
				
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
	
	
	/**
	 * 사용자에게 등록된 모든 포켓몬을 보여주는 메소드
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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Pokemon p = new Pokemon();
				p.setPkNo(rset.getInt("PKNO"));
				p.setPkName(rset.getString("PKNAME"));
				p.setPkType(rset.getString("PKTYPE"));
				p.setPkClass(rset.getString("PKCLASS"));
				p.setPkHeight(rset.getInt("PKHEIGHT"));
				p.setPkWeight(rset.getInt("PKWEIGHT"));
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
	
	public void searchByType() {
		
	}
}
