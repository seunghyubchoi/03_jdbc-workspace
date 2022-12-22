package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	Scanner sc = new Scanner(System.in);
	ProductController pc = new ProductController();

	public void mainMenu() {
		while (true) {
			System.out.println("1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기");
			System.out.println("4. 상품 삭제 하기");
			System.out.println("5. 상품 검색 하기");
			System.out.println("0. 프로그램 종료하기");
			System.out.print(">> 메뉴 입력 : ");
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
			default:
				System.out.println("메뉴 상의 숫자를 입력해주세요.");
				break;
			}
		}
	}

	// 사용자에게 입력 받는 메소드
	// ------------------------------------------------------------------------------------------------------------------

	public void insertProduct() {

		String productId = inputProductId();

		String productName = inputProductName();

		String productPrice = inputProductPrice();

		String productDescription = inputProductDescription();

		String productStock = inputProductStock();

		pc.insertProduct(productId, productName, productPrice, productDescription, productStock);

	}

	public void updateProduct() {
		String productId = inputProductId();
		System.out.println("어떤 정보를 바꾸시겠습니까?");
		System.out.println("1. 상품 이름");
		System.out.println("2. 상품 가격");
		System.out.println("3. 상품 정보");
		System.out.println("4. 상품 재고");
		System.out.println("5. 전체 내용");
		System.out.print(">> 메뉴 입력 : ");
		int menu = sc.nextInt();
		sc.nextLine();

		switch (menu) {
		case 1:
			String productName = inputProductName();
			pc.updateProductName(productId, productName);
			break;
		case 2:
			String productPrice = inputProductPrice();
			pc.updateProductPrice(productId, productPrice);
			break;
		case 3:
			String productDescription = inputProductDescription();
			pc.updateProductDescription(productId, productDescription);
			break;
		case 4:
			String productStock = inputProductStock();
			pc.updateProductStock(productId, productStock);
			break;
		case 5:
			productName = inputProductName();
			productPrice = inputProductPrice();
			productDescription = inputProductDescription();
			productStock = inputProductStock();
			pc.updateProductAll(productName, productPrice, productDescription, productStock, productId);

			break;

		default:
			break;
		}

	}

	public void deleteProduct() {
		String productId = inputProductId();
		pc.deleteProduct(productId);

	}

	public void inputProductKeyword() {
		System.out.print("이름 키워드 입력 : ");
		String keyword = sc.nextLine();
		pc.inputProductKeyword(keyword);
	}

	/**
	 * 상품 아이디 입력 메소드
	 * 
	 * @return
	 */
	public String inputProductId() {
		System.out.print("상품 아이디 입력 : ");
		return sc.nextLine();
	}

	public String inputProductName() {
		System.out.print("상품 이름 입력 : ");
		return sc.nextLine();
	}

	public String inputProductPrice() {
		System.out.print("상품 가격 입력 : ");
		return sc.nextLine();
	}

	public String inputProductDescription() {
		System.out.print("상품 정보 입력 : ");
		return sc.nextLine();
	}

	public String inputProductStock() {
		System.out.print("상품 재고 입력 : ");
		return sc.nextLine();
	}

	// display 메소드
	// ------------------------------------------------------------------------------------------------------------------

	public void displayNodata(String message) {
		System.out.println("\n데이터 요청 처리 실패");
		System.out.println(": " + message + "\n");
	}

	public void displayProductList(ArrayList<Product> list) {
		System.out.println("\n상품 목록입니다.");
		for (Product x : list) {
			System.out.println(x);
		}
		System.out.println();
	}

	public void displayProcessResult(String message) {
		System.out.println("\n요청 결과 : " + message + "\n");
	}

}
