package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu { // View
	// 사용자에게 보여줄 메뉴 : 입출력, 사용자가 보는 시각적요소(화면), 우리한테는 console
	// view : print, Scanner 입력 받는 곳
	// controller 쪽으로 요청을 보내는 곳
	// ex.USERID : User01 입력 - Controller - Dao

	// Scanner 객체 생성 (전역으로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);

	// MemberController 객체 생성(전역에서 바로 요청할 수 있게 끔)
	// (여기서 Controller 호출!!!! = mc)
	private MemberController mc = new MemberController();

	/**
	 * 사용자가 보게될 첫 화면(메인 화면)
	 */
	public void mainMenu() { // mainMenu
		while (true) { // while start
			System.out.println("\n== 회원 관리 프로그램 ==");
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│   1. 회원 추가                │");
			System.out.println("│   2. 회원전체조회             │");
			System.out.println("│   3. 회원 아이디 검색         │");
			System.out.println("│   4. 회원 이름으로 키워드 검색│");
			System.out.println("│   5. 회원 정보 변경           │");
			System.out.println("│   6. 회원탈퇴                 │");
			System.out.println("│   0. 프로그램 종료            │");
			System.out.println("│   9. 이름으로 정보조회        │");
			System.out.println("└───────────────────────────────┘");

			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				inputMember();
				break;
			case 2:
				mc.selectList(); // 입력 받을 거 없으면 바로 Controller 호출
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
				
			case 9:
				inputName();
				break;
			case 0:
				System.out.println("이용해주셔서 감사합니다.");
				return;

			// default : case 숫자 아닌 숫자 입력시
			default:
				System.out.println("메뉴를 잘 못 입력하셨습니다, 다시 입력해주세요.");
				break;
			}

		} // while end
	} // mainMenu end

	/**
	 * 회원 추가 화면(서브화면) 즉, 추가하고자 하는 회원의 정보를 입력 받아서 회원 추가요청하는 창
	 */
	public void inputMember() {
		System.out.println("\n==== 회원추가 ====");
		// 아이디~취미까지 입력 받기

		System.out.print("아이디 : ");
		String userId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();

		System.out.print("이름 : ");
		String userName = sc.nextLine();

		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine();

		System.out.print("나이 : "); // 사용자가 숫자를 입력해도 문자열로 넘어오기 때문에 String 으로 변수 생성
		String age = sc.nextLine();

		System.out.print("이메일 : ");
		String email = sc.nextLine();

		System.out.print("전화번호(- 빼고 입력) : ");
		String phone = sc.nextLine();

		System.out.print("주소 : ");
		String address = sc.nextLine();

		System.out.print("취미(,로 공백 없이 작성) : ");
		String hobby = sc.nextLine();

		// 회원 추가 요청 == Controller 메소드 호출

		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);

	}

	// ---------------------------------------------------------------------------------
	// 응답화면
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답화면
	 * 
	 * @param message 성공메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
	}

	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게 될 응답화면
	 * 
	 * @param message
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
	}

	/**
	 * 조회 서비스 요청시 조회 결과가 없을 경우 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayNodata(String message) {
		System.out.println("\n" + message);
	}
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 데이터는 다음과 같습니다.");
	
		/*
		// 단순 for문
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		*/
		// 향상된 for문
		for(Member m : list) { // m : list.get(0)....get(1)
			System.out.println(m);
			
		}
	}
	/**
	 * 이름을 입력 받는 곳
	 */
	public void inputName() {
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine();
		mc.inputName(name);
	}
	
	/**
	 * 이름 입력 후 조회 요청시 사용자가 보게 될 응답화면
	 * @param list
	 */
	public void displayInfoByName(ArrayList<Member> list) {
		for(Member m : list) { 
			System.out.println(m);
			
		}
		
	}
}
