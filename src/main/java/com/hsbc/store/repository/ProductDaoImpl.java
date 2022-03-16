package com.hsbc.store.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.store.config.ConfigConnection;
import com.hsbc.store.model.Product;

public class ProductDaoImpl implements ProductDao {

	static Connection con = ConfigConnection.getConnection();

	@Override
	public Product add(Product product) throws SQLException {
		String query = "insert into product(clave, nombre, descripcion, precio, stock, proveedor) VALUES (?, ?, ?, ? ,? , ?)";
		PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.clearParameters();
		ps.setString(1, product.getClave());
		ps.setString(2, product.getNombre());
		ps.setString(3, product.getDescripcion());
		ps.setDouble(4, product.getPrecio());
		ps.setLong(5, product.getStock());
		ps.setString(6, product.getProveedor());

		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				product.setIdProduct(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
		return product;
	}

	@Override
	public Product update(Product product, long idProduct) throws SQLException {
		String query = "update product set nombre = ?, descripcion = ?, precio = ?, stock = ?, proveedor = ? where id_product = ?";

		PreparedStatement ps = con.prepareStatement(query);
		ps.clearParameters();
		ps.setString(1, product.getNombre());
		ps.setString(2, product.getDescripcion());
		ps.setDouble(3, product.getPrecio());
		ps.setLong(4, product.getStock());
		ps.setString(5, product.getProveedor());
		ps.setLong(6, idProduct); 

		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("Update user failed, no rows affected.");
		}

		return product;
	}

	@Override
	public List<Product> getProducts() throws SQLException {
		String query = "select * from product";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Product> ls = new ArrayList<>();

		while (rs.next()) {
			Product product = new Product(
						rs.getLong("id_product"),
						rs.getString("clave"),
						rs.getString("nombre"),
						rs.getString("descripcion"),
						rs.getLong("precio"),
						rs.getLong("stock"),
						rs.getString("proveedor")
					); 
			ls.add(product);
		}

		return ls;
	}

	@Override
	public Product getProduct(long id) throws SQLException {
		String query = "select * from product where id_product = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.clearParameters();
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		Product product = null;
		while (rs.next()) {
			 product = new Product(
						rs.getLong("id_product"),
						rs.getString("clave"),
						rs.getString("nombre"),
						rs.getString("descripcion"),
						rs.getLong("precio"),
						rs.getLong("stock"),
						rs.getString("proveedor")
					); 
		}

		return product;
	}

	@Override
	public void delete(long idProduct) throws SQLException {

		String query = "delete from product where id_product = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.clearParameters();
		ps.setLong(1, idProduct);
		ps.executeUpdate();
	}

}
