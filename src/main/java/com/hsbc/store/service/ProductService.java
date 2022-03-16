package com.hsbc.store.service;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.store.model.Product;

public interface ProductService {

	public Product addProduct(Product product) throws SQLException;

	public Product updateProduct(Product product, long idProduct) throws SQLException;

	public List<Product> getProducts() throws SQLException;

	public Product getProduct(long idProduct) throws SQLException;

	public void deleteProduct(long idProduct) throws SQLException;

}
