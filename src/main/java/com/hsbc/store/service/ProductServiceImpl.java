package com.hsbc.store.service;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.store.model.Product;
import com.hsbc.store.repository.ProductDao;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public Product addProduct(Product product) throws SQLException {
		
		return this.productDao.add(product);
	}

	@Override
	public Product updateProduct(Product product, long idProduct) throws SQLException {
		Product productSaved = this.productDao.update(product, idProduct);
		return productSaved;
	}

	@Override
	public List<Product> getProducts() throws SQLException {
		return this.productDao.getProducts();
	}

	@Override
	public Product getProduct(long idProduct) throws SQLException {
		return this.productDao.getProduct(idProduct);
	}

	@Override
	public void deleteProduct(long idProduct) throws SQLException {
		this.productDao.delete(idProduct);

	}

}
