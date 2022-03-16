package com.hsbc.store.model;

public class Product {
	private long idProduct;
	private String clave;
	private String nombre;
	private String descripcion;
	private double precio;
	private long stock;
	private String proveedor;

	public Product() {
		super();
	}

	public Product(long idProduct, String clave, String nombre, String descripcion, double precio, long stock,
			String proveedor) {
		super();
		this.idProduct = idProduct;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.proveedor = proveedor;
	}

	public Product(String clave, String nombre, String descripcion, double precio, long stock, String proveedor) {
		super();
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.proveedor = proveedor;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	
	public long getIdProduct() {
		return idProduct;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public long getStock() {
		return stock;
	}

	public String getProveedor() {
		return proveedor;
	}

}
