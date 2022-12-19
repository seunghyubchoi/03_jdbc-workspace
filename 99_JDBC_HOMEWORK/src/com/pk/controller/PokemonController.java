package com.pk.controller;

import java.util.ArrayList;

import com.pk.model.dao.PokemonDao;
import com.pk.model.vo.Pokemon;
import com.pk.view.PokemonMenu;

public class PokemonController {
	/**
	 * 사용자가 직접 입력하여 포켓몬을 추가하는 메소드
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
	
	public void searchByType() {
		new PokemonDao().searchByType();
	}
}
