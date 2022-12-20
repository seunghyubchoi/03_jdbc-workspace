package com.pk.controller;

import java.util.ArrayList;

import com.pk.model.dao.PokemonDao;
import com.pk.model.vo.Pokemon;
import com.pk.view.PokemonMenu;

public class PokemonController {
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
			String pkDetail) {
		Pokemon p = new Pokemon(pkName, pkType, pkClass, Double.parseDouble(pkHeight), Double.parseDouble(pkWeight),
				pkDetail);
		int result = new PokemonDao().insertPokemon(p);

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
		ArrayList<Pokemon> list = new PokemonDao().searchAll();

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
		ArrayList<String> list = new PokemonDao().searchByType();

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("포켓몬 타입을 불러오는데 실패했습니다.");
		} else {
			new PokemonMenu().displayListByType(list);

		}
	}
	
	public void searchByClass() {
		ArrayList<String> list = new PokemonDao().searchByClass();
		if (list.isEmpty()) {
			new PokemonMenu().displayFail("포켓몬 분류를 불러오는데 실패했습니다.");
		} else {
			new PokemonMenu().displayListByClass(list);

		}
	}

	/**
	 * 데이터에 등록된 전체 타입을 보여주는 메소드
	 * 
	 * @param type
	 */
	public void displayByType(String type) {
		ArrayList<Pokemon> list = new PokemonDao().displayByType(type);

		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displayListBySelectedType(list);
		}
	}
	
	/**
	 * @param pkClass
	 */
	public void displayByClass(String pkClass) {
		ArrayList<Pokemon> list = new PokemonDao().displayByType(pkClass);
		
		if (list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displayListBySelectedClass(list);
		}
	}

	/**
	 * 포켓몬의 이름을 입력받는 메소드
	 * @param name
	 */
	public void inputPokemonName(String name) {
		ArrayList<Pokemon> list = new PokemonDao().inputPokemonName(name);
		
		if(list.isEmpty()) {
			new PokemonMenu().displayFail("select 요청이 실패했습니다.");
		} else {
			new PokemonMenu().displaySearchAll(list);
		}
	}

	/**
	 * 포켓몬 정보 업데이트 메소드
	 * @param pkName
	 * @param pkHeight
	 * @param pkWeight
	 * @param pkDetail
	 */
	public void updatePokemon(String pkName,String pkHeight,String pkWeight,String pkDetail) {
		Pokemon p = new Pokemon();
		p.setPkName(pkName);
		p.setPkHeight(Double.parseDouble(pkHeight));
		p.setPkWeight(Double.parseDouble(pkWeight));
		p.setPkDetail(pkDetail);
		
		int result = new PokemonDao().updatePokemon(p);
		
		if (result > 0) {
			new PokemonMenu().displaySuccess("update 요청이 성공적으로 처리됐습니다.");
		} else { 
			new PokemonMenu().displayFail("update 요청이 실패했습니다.");
		}
	}
	
	/**
	 * 포켓몬 정보 삭제 메소드
	 * @param name
	 */
	public void deletePokemon(String name) {
		int result = new PokemonDao().deletePokemon(name);
		if (result > 0) {
			new PokemonMenu().displaySuccess("delete 요청이 성공적으로 처리됐습니다.");
		} else { 
			new PokemonMenu().displayFail("delete 요청이 실패했습니다.");
		}
	}
}
