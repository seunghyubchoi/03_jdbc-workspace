package com.pk.controller;

import java.util.ArrayList;

import com.pk.model.dao.PokemonDao;
import com.pk.model.service.PokemonService;
import com.pk.model.vo.Pokemon;
import com.pk.view.PokemonMenu;

public class PokemonController {
//------------------------------------로그인-----------------------------------------------	
	/**
	 * 로그인 메소드
	 * 
	 * @param userId
	 * @param userPwd
	 */
	public void loginMenu(String userId, String userPwd) {
		int result = new PokemonService().loginMenu(userId, userPwd);
		if (result > 0) {
			new PokemonMenu().trainerMenu(userId);
		} else {
			new PokemonMenu().displayFail("로그인에 실패했습니다.");
		}
	}

	/**
	 * 관리자 로그인 메소드
	 * 
	 * @param userId
	 * @param userPwd
	 */
	public void adminLoginMenu(String userId, String userPwd) {
		int result = new PokemonService().adminLoginMenu(userId, userPwd);

		if (result > 0) {
			new PokemonMenu().mainMenu();
		} else {
			new PokemonMenu().displayFail("로그인에 실패했습니다.");
		}
	}

	/**
	 * 로그인한 트레이너의 이름을 화면에 보여주는 메소드
	 * 
	 * @param userId
	 */
	public void displayTrainerName(String userId) {
		String userName = new PokemonService().displayTrainerName(userId);
		if (userName == null) {
			System.out.println("입력된 이름이 없어요..");
		} else {
			System.out.println("환영합니다 " + userName + "님!");
		}
	}

//-----------------------------------------------------------------------------------------
	/**
	 * 트레이너가 소유한 포켓몬을 보여주는 메소드
	 * 
	 * @param userId
	 */
	public void displayMyPokemon(String userId) {
		ArrayList<Pokemon> list = new PokemonService().displayMyPokemon(userId);
		if (list.isEmpty()) {
			new PokemonMenu().displayFail("곁에 있는 친구가 없습니다");
		} else {
			new PokemonMenu().displaySearchAll(list);
		}
	}

	/**
	 * 사용자가 직접 입력하여 포켓몬을 추가하는 메소드
	 * 
	 * @param pkName
	 * @param pkType
	 * @param pkClass
	 * @param pkHeight
	 * @param pkWeight
	 * @param pkDetail
	 */
	public void insertPokemon(String pkName, String pkType, String pkClass, String pkHeight, String pkWeight,
			String pkDetail, String trNo) {
		Pokemon p = new Pokemon(pkName, pkType, pkClass, Double.parseDouble(pkHeight), Double.parseDouble(pkWeight),
				pkDetail, Integer.parseInt(trNo));

		int result = new PokemonService().insertPokemon(p);

		if (result > 0) {
			new PokemonMenu().displaySuccess("insert 요청이 성공적으로 처리됐습니다.");
		} else {
			new PokemonMenu().displayFail("insert 요청이 실패했습니다.");
		}
	}

	/**
	 * 사용자에게 등록된 모든 포켓몬을 보여주는 메소드
	 */
	public void searchAll() {
		ArrayList<Pokemon> list = new PokemonService().searchAll();

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displaySearchAll(list);
		}
	}

	/**
	 * 타입별로 분류하는 메소드
	 */
	public void searchByType() {
		ArrayList<String> list = new PokemonService().searchByType();

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("포켓몬 타입을 불러오는데 실패했습니다.");
		} else {
			new PokemonMenu().displayListByType(list);

		}
	}

	/**
	 * 분류별로 분류하는 메소드
	 */
	public void searchByClass() {
		ArrayList<String> list = new PokemonService().searchByClass();
		if (list.isEmpty()) {
			new PokemonMenu().displayFail("포켓몬 분류를 불러오는데 실패했습니다.");
		} else {
			new PokemonMenu().displayListByClass(list);

		}
	}

	/**
	 * 사용자로부터 타입을 입력받아 입력된 타입만 보여주는 메소드
	 * 
	 * @param type
	 */
	public void displayByType(String type) {
		ArrayList<Pokemon> list = new PokemonService().displayByType(type);

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displayListBySelectedType(list);
		}
	}

	/**
	 * 사용자로부터 클래스를 입력받아 입력된 클래스만 보여주는 메소드
	 * 
	 * @param pkClass
	 */
	public void displayByClass(String pkClass) {
		ArrayList<Pokemon> list = new PokemonService().displayByClass(pkClass);

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displayListBySelectedClass(list);
		}
	}

	/**
	 * 포켓몬의 이름을 입력받는 메소드
	 * 
	 * @param name
	 */
	public void inputPokemonName(String name) {
		ArrayList<Pokemon> list = new PokemonService().inputPokemonName(name);

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displaySearchAll(list);
		}
	}

	/**
	 * 포켓몬 정보 업데이트 메소드
	 * 
	 * @param pkName
	 * @param pkHeight
	 * @param pkWeight
	 * @param pkDetail
	 */
	public void updatePokemon(String pkName, String pkHeight, String pkWeight, String pkDetail) {
		Pokemon p = new Pokemon();
		p.setPkName(pkName);
		p.setPkHeight(Double.parseDouble(pkHeight));
		p.setPkWeight(Double.parseDouble(pkWeight));
		p.setPkDetail(pkDetail);

		int result = new PokemonService().updatePokemon(p);

		if (result > 0) {
			new PokemonMenu().displaySuccess("update 요청이 성공적으로 처리됐습니다.");
		} else {
			new PokemonMenu().displayFail("update 요청이 실패했습니다.");
		}
	}

	/**
	 * 포켓몬 정보 삭제 메소드
	 * 
	 * @param name
	 */
	public void deletePokemon(String name) {
		int result = new PokemonService().deletePokemon(name);
		if (result > 0) {
			new PokemonMenu().displaySuccess("delete 요청이 성공적으로 처리됐습니다.");
		} else {
			new PokemonMenu().displayFail("delete 요청이 실패했습니다.");
		}
	}
	
	public void displayRandomPokemon() {
		ArrayList<Pokemon> list = new PokemonService().displayRandomPokemon();
		if (list.isEmpty()) {
			new PokemonMenu().displayFail("데이터 불러오기 실패");
		} else {
			new PokemonMenu().displaySearchAll(list);
		}
		
	}
}
