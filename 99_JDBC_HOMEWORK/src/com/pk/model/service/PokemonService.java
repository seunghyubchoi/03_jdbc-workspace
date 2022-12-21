package com.pk.model.service;

import static com.pk.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.pk.controller.PokemonController;
import com.pk.model.dao.PokemonDao;
import com.pk.model.vo.Pokemon;

public class PokemonService {

	// ----------------------------로그인
	// 관련-------------------------------------------------

	/**
	 * 로그인 메소드
	 * 
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	public int loginMenu(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = new PokemonDao().loginMenu(conn, userId, userPwd);
		close(conn);
		return result;

	}

	/**
	 * 관리자 로그인 메소드
	 * 
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	public int adminLoginMenu(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = new PokemonDao().adminLoginMenu(conn, userId, userPwd);
		return result;
	}

	/**
	 * 로그인한 트레이너의 이름을 화면에 보여주는 메소드
	 * 
	 * @param userId
	 * @return
	 */
	public String displayTrainerName(String userId) {
		Connection conn = getConnection();
		String userName = new PokemonDao().displayTrainerName(conn, userId);

		close(conn);
		return userName;
	}

	// ------------------------------------------------------------------------------------------

	/**
	 * 트레이너가 소유한 포켓몬을 보여주는 메소드
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList<Pokemon> displayMyPokemon(String userId) {
		Connection conn = getConnection();
		ArrayList<Pokemon> list = new PokemonDao().displayMyPokemon(conn, userId);

		close(conn);
		return list;
	}

	/**
	 * 사용자가 직접 입력하여 포켓몬을 추가하는 메소드
	 * 
	 * @param p
	 * @return
	 */
	public int insertPokemon(Pokemon p) {

		Connection conn = getConnection();

		int result = new PokemonDao().insertPokemon(conn, p);

		close(conn);

		return result;

	}

	/**
	 * 사용자에게 등록된 모든 포켓몬을 보여주는 메소드
	 * 
	 * @return
	 */
	public ArrayList<Pokemon> searchAll() {
		Connection conn = getConnection();

		ArrayList<Pokemon> list = new PokemonDao().searchAll(conn);

		close(conn);

		return list;

	}

	/**
	 * 타입별로 분류하는 메소드
	 * 
	 * @return
	 */
	public ArrayList<String> searchByType() {
		Connection conn = getConnection();
		ArrayList<String> list = new PokemonDao().searchByType(conn);

		close(conn);
		return list;
	}

	/**
	 * 분류별로 분류하는 메소드
	 * 
	 * @return
	 */
	public ArrayList<String> searchByClass() {
		Connection conn = getConnection();
		ArrayList<String> list = new PokemonDao().searchByClass(conn);
		close(conn);
		return list;

	}

	/**
	 * 사용자로부터 타입을 입력받아 입력된 타입만 보여주는 메소드
	 * 
	 * @param type
	 */
	public ArrayList<Pokemon> displayByType(String type) {
		Connection conn = getConnection();
		ArrayList<Pokemon> list = new PokemonDao().displayByType(conn, type);
		close(conn);
		return list;
	}

	/**
	 * 사용자로부터 클래스를 입력받아 입력된 클래스만 보여주는 메소드
	 * 
	 * @param pkClass
	 */
	public ArrayList<Pokemon> displayByClass(String pkClass) {
		Connection conn = getConnection();
		ArrayList<Pokemon> list = new PokemonDao().displayByClass(conn, pkClass);
		close(conn);
		return list;
	}

	/**
	 * 포켓몬의 이름을 입력받는 메소드
	 * @param name
	 * @return
	 */
	public ArrayList<Pokemon> inputPokemonName(String name) {
		Connection conn = getConnection();
		ArrayList<Pokemon> list = new PokemonDao().inputPokemonName(conn, name);
		close(conn);
		return list;
	}

	/**
	 * 포켓몬 정보 업데이트 메소드
	 * @param p
	 * @return
	 */
	public int updatePokemon(Pokemon p) {
		Connection conn = getConnection();
		int result = new PokemonDao().updatePokemon(conn, p);
		close(conn);
		return result;
	}
	
	/**
	 * 포켓몬 정보 삭제 메소드
	 * @param name
	 * @return
	 */
	public int deletePokemon(String name) {
		Connection conn = getConnection();
		int result = new PokemonDao().deletePokemon(conn, name);
		close(conn);
		return result;
	}
}
