package com.hsbc.store.repository;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.store.model.Product;

public interface ProductDao {

	public Product add(Product product) throws SQLException;

	public Product update(Product product, long idProduct) throws SQLException;

	public List<Product> getProducts() throws SQLException;

	public Product getProduct(long id) throws SQLException;

	public void delete(long idProduct) throws SQLException;

}
