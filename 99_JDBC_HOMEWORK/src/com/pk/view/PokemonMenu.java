package com.pk.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.pk.controller.PokemonController;
import com.pk.model.vo.Pokemon;

public class PokemonMenu {
	Scanner sc = new Scanner(System.in);
	PokemonController pc = new PokemonController();

	public void loginMenu() {
		System.out.println("기어와요 포켓몬의 숲");
		System.out.println("1. 로그인하기");
		System.out.println("2. 회원가입하기");
		System.out.print(">> 메뉴 입력 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		switch (menu) {
		case 1:
			String userId = InsertUserId();
			String userPwd = InsertUserPwd();
			if (userId.equals("admin") && userPwd.equals("admin")) {
				pc.adminLoginMenu(userId, userPwd);
			} else {
				pc.loginMenu(userId, userPwd);
			}
			break;
		case 2:

			break;

		default:
			System.out.println("메뉴 상의 숫자를 입력해주세요");
			break;
		}

	}

	/**
	 * 로그인 성공시 트레이너 메뉴 조회
	 * 
	 * @param userId
	 */
	public void trainerMenu(String userId) {
		pc.displayTrainerName(userId);
		while (true) {
			System.out.println("1. 나의 포켓몬 조회");
			System.out.println("2. 포켓몬 잡기");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				pc.displayMyPokemon(userId);
				break;
			case 2:
				pc.displayRandomPokemon();

				System.out.println("1. 포켓몬 다시 찾기");
				System.out.println("2. 몬스터볼 사용");
				menu = sc.nextInt();
				sc.nextLine();

				switch (menu) {
				case 1:
					pc.displayRandomPokemon();
					break;

				default:
					break;
				}
				break;

			default:
				break;
			}

		}
	}

	public void signUp() {

	}

	/**
	 * 포켓몬 메인 메뉴 메소드
	 */
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
			System.out.print(">> 메뉴 입력 : ");
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
				System.out.print("트레이너 번호 입력 : ");
				String trNo = sc.nextLine();

				pc.insertPokemon(pkName, pkType, pkClass, pkHeight, pkWeight, pkDetail, trNo);
				break;
			case 2:
				pc.searchAll();
				break;

			case 3:
				pc.searchByType();
				break;

			case 4:
				pc.searchByClass();
				break;

			case 5:
				pc.inputPokemonName(inputPokemonName());
				break;

			case 6:
				updatePokemon();
				break;

			case 7:
				pc.deletePokemon(deletePokemon());
				break;

			case 0:
				System.out.println("\n프로그램을 종료합니다.\n");
				return;

			default:
				System.out.println("\n메뉴 상의 숫자를 입력해주세요.\n");
				break;
			}
		}
	}
//------------------------------------------------------------------------------------

	public String InsertUserId() {
		System.out.println("아이디를 입력해주세요.");
		return sc.nextLine();
	}

	public String InsertUserPwd() {
		System.out.println("비밀번호를 입력해주세요.");
		return sc.nextLine();
	}

	/**
	 * 요청 성공시 사용자에게 보여지는 문구
	 * 
	 * @param message
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message + "\n");
	}

	/**
	 * 요청 실패시 사용자에게 보여지는 문구
	 * 
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message + "\n");
	}

	/**
	 * 등록된 모든 포켓몬을 보여주는 리스트
	 * 
	 * @param list
	 */
	public void displaySearchAll(ArrayList<Pokemon> list) {
		for (Pokemon p : list) {
			System.out.println(p);
		}
		System.out.println();
	}

	/**
	 * 데이터에 등록된 전체 타입을 보여주는 메소드
	 * 
	 * @param list
	 */
	public void displayListByType(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");

		}
		System.out.print("\n타입 입력 : ");
		String type = sc.nextLine();
		System.out.println();
		pc.displayByType(type);

	}

	/**
	 * 사용자가 선택한 타입만 보여주는 메소드
	 * 
	 * @param list
	 */
	public void displayListBySelectedType(ArrayList<Pokemon> list) {
		for (Pokemon p : list) {
			System.out.println(p);
		}
		System.out.println();
	}

	public void displayListBySelectedClass(ArrayList<Pokemon> list) {
		for (Pokemon p : list) {
			System.out.println(p);
		}
		System.out.println();
	}

	public void displayListByClass(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i) + " ");
		}
		System.out.print("\n분류 입력 : ");
		String pkClass = sc.nextLine();
		System.out.println();
		pc.displayByClass(pkClass);
	}

	/**
	 * 포켓몬의 이름을 입력받는 메소드
	 * 
	 * @return
	 */
	public String inputPokemonName() {
		System.out.print("\n포켓몬 이름(키워드) 입력 : ");
		return sc.nextLine();
	}

	/**
	 * 포켓몬 정보 업데이트 메소드
	 */
	public void updatePokemon() {
		System.out.println("\n === 포켓몬 정보 업데이트 ===");
		System.out.print("이름 입력 : ");
		String pkName = sc.nextLine();

		System.out.print("키 입력 : ");
		String pkHeight = sc.nextLine();

		System.out.print("몸무게 입력 : ");
		String pkWeight = sc.nextLine();

		System.out.print("설명 입력 : ");
		String pkDetail = sc.nextLine();

		pc.updatePokemon(pkName, pkHeight, pkWeight, pkDetail);
	}

	public String deletePokemon() {
		System.out.print("이름 입력 : ");
		return sc.nextLine();
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
