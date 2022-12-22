package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;

public class ProductDao {

	private Properties prop = new Properties();

	public ProductDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> selectProduct(Connection conn) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setProductName(rset.getString("P_NAME"));
				p.setProductPrice(rset.getInt("PRICE"));
				p.setProductDescription(rset.getString("DESCRIPTION"));
				p.setProductStock(rset.getInt("STOCK"));

				list.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public int insertProduct(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getProductName());
			pstmt.setInt(3, p.getProductPrice());
			pstmt.setString(4, p.getProductDescription());
			pstmt.setInt(5, p.getProductStock());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public void updateProduct() {

	}

	public int deleteProduct(Connection conn, String productId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteProduct");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Product> inputProductKeyword(Connection conn, String keyword) {
		ArrayList<Product> list = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("inputProductKeyword");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product(rset.getString("PRODUCT_ID"), rset.getString("P_NAME"), rset.getInt("PRICE"),
						rset.getString("DESCRIPTION"), rset.getInt("STOCK"));
				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int updateProductName(Connection conn, String productId, String productName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			pstmt.setString(2, productId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateProductPrice(Connection conn, String productId, String productPrice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductPrice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productPrice);
			pstmt.setString(2, productId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProductDescription(Connection conn, String productId, String productDescription) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductDescription");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productDescription);
			pstmt.setString(2, productId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateProductStock(Connection conn, String productId, String productStock) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductStock");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productStock);
			pstmt.setString(2, productId);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateProductAll(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateProductAll");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getProductName());
			pstmt.setInt(2, p.getProductPrice());
			pstmt.setString(3, p.getProductDescription());
			pstmt.setInt(4, p.getProductPrice());
			pstmt.setString(5, p.getProductId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

}
