package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 해당 메소드로 전달된 데이터(id, pass, name...)를 가공처리 한 후 DAO로 전달하면서 호출
// DAO로 부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정(View 메소드 호출)

// 사용자의 요청 처리
// 사용자의 요청 처리를 Dao 쪽으로 보내는 Controller

public class MemberController {
	
	// View단을 전역변수로 주면 StackOverFlow 오류가 발생할 수 있다......
	// private MemberMenu mm = new MemberMenu();

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * 
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 추가가 성공했습니다.");
		} else {
			new MemberMenu().displayFail("회원 추가가 실패했습니다.");
		}
	}

	/**
	 * 사용자의 회원 전체 조회요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNodata("데이터가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}

	/**
	 * 이름으로 조회 요청 처리 메소드
	 * 
	 * @param name
	 */
	public void inputName(String name) {
	
	}

	/**
	 * 사용자의 아이디로 회원검색 요청 처리해주는 메소드
	 * @param userId
	 */
	public void selectByUserId(String userId) {
	Member m = new MemberDao().selectByUserId(userId);
	if (m == null) {
		new MemberMenu().displayNodata(userId + "에 데이터가 없습니다");
	} else {
		new MemberMenu().displayMember(m);
	}
	
	}

	/**
	 * 사용자의 이름으로 키워드 검색 요청시 처리해주는 메소드
	 * 
	 * @param keyword
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		if(list.isEmpty()) {
			new MemberMenu().displayNodata("데이터가 없습니다.");
		} else { 
			new MemberMenu().displayMemberList(list);
		}
	}

	/**
	 * 사용자 정보를 업데이트 해주는 메소드
	 * @param userId  : 변경하고자 하는 회원 아이디
	 * @param userPwd : 변경할 비밀번호
	 * @param email	  : 변경할 이메일
	 * @param phone   : 변경할 전화번호
	 * @param address : 변경할 주소
	 * @param age     : 변경할 나이
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address, String age) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setAge(Integer.parseInt(age));
		int result  = new MemberDao().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("회원 업데이트가 성공했습니다.");
		} else {
			new MemberMenu().displayFail("회원 업데이트가 실패했습니다.");
		}
	}
	
	public void deleteMember(String userId) {
		//new MemberDao().deleteMember();
	}
	

}
