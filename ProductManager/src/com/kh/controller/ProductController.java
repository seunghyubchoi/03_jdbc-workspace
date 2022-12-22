package com.kh.controller;

import java.io.Closeable;
import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

import oracle.jdbc.OracleConnection.CommitOption;

public class ProductController {

	public void selectProduct() {
		ArrayList<Product> list = new ProductService().selectProduct();
		if (list.isEmpty()) {
			new ProductMenu().displayNodata("상품 목록이 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}

	}

	public void insertProduct(String productId, String productName, String productPrice, String productDescription,
			String productStock) {
		Product p = new Product(productId, productName, Integer.parseInt(productPrice), productDescription,
				Integer.parseInt(productStock));
		int result = new ProductService().insertProduct(p);
		if (result > 0) {
			new ProductMenu().displayProcessResult("성공적으로 데이터가 추가 됐습니다.");
		} else {
			new ProductMenu().displayProcessResult("데이터 추가 실패 했습니다.");
		}
	}

	public void updateProduct(String productId) {

	}

	public void deleteProduct(String productId) {
		int result = new ProductService().deleteProduct(productId);
		if (result > 0) {
			new ProductMenu().displayProcessResult("성공적으로 데이터를 삭제했습니다.");
		} else {
			new ProductMenu().displayProcessResult("데이터 삭제 실패 했습니다.");
		}
	}

	public void inputProductKeyword(String keyword) {
		ArrayList<Product> list = new ProductService().inputProductKeyword(keyword);
		if (list.isEmpty()) {
			new ProductMenu().displayNodata("키워드 검색 결과 없습니다.");
		} else {
			new ProductMenu().displayProductList(list);
		}

	}

	public void updateProductName(String productId, String productName) {
		int result = new ProductService().updateProductName(productId, productName);

		if (result > 0) {
			new ProductMenu().displayProcessResult("update 성공");
		} else {
			new ProductMenu().displayProcessResult("update 실패");
		}
	}

	public void updateProductPrice(String productId, String productPrice) {
		int result = new ProductService().updateProductPrice(productId, productPrice);

		if (result > 0) {
			new ProductMenu().displayProcessResult("update 성공");
		} else {
			new ProductMenu().displayProcessResult("update 실패");
		}
	}

	public void updateProductDescription(String productId, String productDescription) {
		int result = new ProductService().updateProductDescription(productId, productDescription);

		if (result > 0) {
			new ProductMenu().displayProcessResult("update 성공");
		} else {
			new ProductMenu().displayProcessResult("update 실패");
		}
	}

	public void updateProductStock(String productId, String productStock) {
		int result = new ProductService().updateProductStock(productId, productStock);

		if (result > 0) {
			new ProductMenu().displayProcessResult("update 성공");
		} else {
			new ProductMenu().displayProcessResult("update 실패");
		}
	}

	public void updateProductAll(String productName, String productPrice, String productDescription,
			String productStock, String productId) {
		Product p = new Product(productId, productName, Integer.parseInt(productPrice), productDescription,
				Integer.parseInt(productStock));
		int result = new ProductService().updateProductAll(p);

		if (result > 0) {
			new ProductMenu().displayProcessResult("update 성공");
		} else {
			new ProductMenu().displayProcessResult("update 실패");
		}

	}

}
