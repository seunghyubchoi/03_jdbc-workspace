package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();

	public void mainMenu() {
		System.out.println("1. 전체 조회 하기");
		System.out.println("2. 상품 추가 하기");
		System.out.println("3. 상품 수정 하기");
		System.out.println("4. 상품 삭제 하기");
		System.out.println("5. 상품 검색 하기");
		System.out.println("0. 프로그램 종료하기");
		int menu = sc.nextInt();
		sc.nextLine();
		switch (menu) {
		case 1:
			pc.selectProduct();
			break;
		case 2:
			insertProduct();
			break;
		case 3:
			updateProduct();
			break;
		case 4:
			deleteProduct();
			break;
		case 5:
			inputProductKeyword();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");

			return;
		default: System.out.println("메뉴 상의 숫자를 입력해주세요.");
			break;
		}
	}
	
	
	// 사용자에게 입력 받는 메소드 ------------------------------------------------------------------------------------------------------------------
	
	public void insertProduct() {
		System.out.print("상품 아이디 입력 : ");
		String productId = sc.nextLine();
		System.out.print("상품 이름 입력 : ");
		String productName = sc.nextLine();
		System.out.print("상품 가격 입력 : ");
		String productPrice = sc.nextLine();
		System.out.print("상품 정보 입력 : ");
		String productDescription = sc.nextLine();
		System.out.print("상품 재고 입력 : ");
		String productStock = sc.nextLine();
		
		pc.insertProduct(productId, productName, productPrice, productDescription, productStock);
		
	}
	
	public void updateProduct() {
		
		
	}
	
	public void deleteProduct() {
		
	}
	
	public void inputProductKeyword() {
		
	}
	
	
 //	display 메소드
	
	public void displayNodata(String message) {
		System.out.println("데이터 요청 처리 실패");
		System.out.println(": " + message);
	}
	
	public void displayProductList(ArrayList<Product> list) {
		for(Product x : list) {
			System.out.println("상품 목록입니다.");
			System.out.println(x);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
 }
