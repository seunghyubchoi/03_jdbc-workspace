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
		
		close(conn);
		
		return result;
		
	}

	public void updateProduct() {

	}

	public void deleteProduct() {

	}

	public void inputProductKeyword() {

	}

}
