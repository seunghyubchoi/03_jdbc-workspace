package com.pk.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.pk.controller.PokemonController;
import com.pk.model.vo.Pokemon;

public class PokemonMenu {
	Scanner sc = new Scanner(System.in);
	PokemonController pc = new PokemonController();

	public void mainMenu() {
		while (true) {
			System.out.println("1. 포켓몬 추가");
			System.out.println("2. 포켓몬 전체 조회");
			System.out.println("3. 타입별 검색");
			System.out.println("4. 분류별 검색");
			System.out.println("5. 이름 키워드로 검색");
			System.out.println("6. 정보 변경");
			System.out.println("7. 포켓몬 지우기");
			System.out.println("0. 프로그램 종료");

			int menu = sc.nextInt();
			sc.nextLine();
			switch (menu) {
			case 1:
				System.out.println("\n === 포켓몬 추가 ===");
				System.out.print("이름 입력 : ");
				String pkName = sc.nextLine();
				System.out.print("타입 입력 : ");
				String pkType = sc.nextLine();
				System.out.print("분류 입력 : ");
				String pkClass = sc.nextLine();
				System.out.print("키 입력 : ");
				String pkHeight = sc.nextLine();
				System.out.print("몸무게 입력 : ");
				String pkWeight = sc.nextLine();
				System.out.print("설명 입력 : ");
				String pkDetail = sc.nextLine();

				pc.insertPokemon(pkName, pkType, pkClass, pkHeight, pkWeight, pkDetail);
				break;
			case 2:
				pc.searchAll();
				break;

			case 3:
				pc.searchByType();
				break;

			case 4:

				break;

			case 5:

				break;

			case 6:

				break;

			case 7:

				break;

			case 8:

				break;

			default:
				break;
			}
		}
	}

	/**
	 * 요청 성공시 사용자에게 보여지는 문구
	 * 
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}

	/**
	 * 요청 실패시 사용자에게 보여지는 문구
	 * 
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}

	public void displaySearchAll(ArrayList<Pokemon> list) {
		for (Pokemon p : list) {
			System.out.println(p);
		}
		System.out.println();
	}

	/*
	 * public void signUp() { System.out.println("1. 계정생성");
	 * System.out.println("0. 프로그램 종료"); int menu = sc.nextInt(); sc.nextLine();
	 * 
	 * switch (menu) { case 1: System.out.print("아이디 입력 : "); String userId =
	 * sc.nextLine(); System.out.print("비밀번호 입력 : "); String userPwd =
	 * sc.nextLine(); pc.SignUp(); break;
	 * 
	 * default: break; } }
	 */

}
