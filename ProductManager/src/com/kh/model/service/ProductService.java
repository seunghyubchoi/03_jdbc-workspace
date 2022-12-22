package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {

	public ArrayList<Product> selectProduct() {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductDao().selectProduct(conn);
		close(conn);

		return list;

	}

	public int insertProduct(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().insertProduct(conn, p);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;

	}

	public void updateProduct() {

	}

	public int deleteProduct(String productId) {
		Connection conn = getConnection();
		int result = new ProductDao().deleteProduct(conn, productId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public ArrayList<Product> inputProductKeyword(String keyword) {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductDao().inputProductKeyword(conn, keyword);
		if (list.isEmpty()) {
			rollback(conn);
		} else {
			commit(conn);
		}
		return list;
	}

	public int updateProductName(String productId, String productName) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProductName(conn, productId, productName);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
		
	}

	public int updateProductPrice(String productId, String productPrice) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProductPrice(conn, productId, productPrice);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
		
	}

	public int updateProductDescription(String productId, String productDescription) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProductDescription(conn, productId, productDescription);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
		
	}

	public int updateProductStock(String productId, String productStock) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProductStock(conn, productId, productStock);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int updateProductAll(Product p) {
		Connection conn = getConnection();
		int result = new ProductDao().updateProductAll(conn, p);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}
}
