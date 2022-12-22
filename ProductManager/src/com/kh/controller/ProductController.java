package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {

	public void selectProduct() {
		ArrayList<Product> list = new ProductService().selectProduct();
		if(list.isEmpty()) {
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
			
		}
	}

	public void updateProduct() {

	}

	public void deleteProduct() {

	}

	public void inputProductKeyword() {

	}

}
