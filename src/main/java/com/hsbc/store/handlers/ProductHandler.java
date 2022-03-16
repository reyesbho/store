package com.hsbc.store.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.store.config.Constants;
import com.hsbc.store.config.Handler;
import com.hsbc.store.config.ResponseEntity;
import com.hsbc.store.config.StatusCode;
import com.hsbc.store.exceptions.ApplicationExceptions;
import com.hsbc.store.exceptions.GlobalExceptionHandler;
import com.hsbc.store.model.Product;
import com.hsbc.store.service.ProductService;
import com.sun.net.httpserver.HttpExchange;

public class ProductHandler extends Handler {

	private final ProductService productService;

	public ProductHandler(ProductService productService, ObjectMapper objectMapper,
			GlobalExceptionHandler exceptionHandler) {
		super(objectMapper, exceptionHandler);
		this.productService = productService;
	}

	@Override
	protected void execute(HttpExchange exchange) throws IOException, SQLException {
		byte[] response = null;
		ResponseEntity<?> e = null;
		if ("POST".equals(exchange.getRequestMethod())) {
			e = doPost(exchange.getRequestBody());
			exchange.getResponseHeaders().putAll(e.getHeaders());
			exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
			response = super.writeResponse(e.getBody());
			
		} else if ("GET".equals(exchange.getRequestMethod())) {
			String endPoint = exchange.getRequestURI().getRawPath();

			if ("list".equals(endPoint.split("/")[endPoint.split("/").length - 1])) {
				e = doGetAll();
			} else if (Pattern.matches("[0-9]*", endPoint.split("/")[endPoint.split("/").length - 1])) {
				e = doGet(splitUrl(exchange.getRequestURI().getRawPath()));
			} else {
				throw ApplicationExceptions.notFound("The url params is not valid").get();
			}

			exchange.getResponseHeaders().putAll(e.getHeaders());
			exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
			response = super.writeResponse(e.getBody());
		} else if ("PUT".equals(exchange.getRequestMethod())) {
			String endPoint = exchange.getRequestURI().getRawPath();
			if (!Pattern.matches("[0-9]*", endPoint.split("/")[endPoint.split("/").length - 1])) {
				throw ApplicationExceptions
				.notFound("Product id not found")
				.get();
			} 
			
			e = doUpdate(exchange.getRequestBody(), splitUrl(exchange.getRequestURI().getRawPath()));
			exchange.getResponseHeaders().putAll(e.getHeaders());
			exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
			response = super.writeResponse(e.getBody());
			
		} else if ("DELETE".equals(exchange.getRequestMethod())) {
			String endPoint = exchange.getRequestURI().getRawPath();
			if (Pattern.matches("[0-9]*", endPoint.split("/")[endPoint.split("/").length - 1])) {
				e = doDelete(splitUrl(exchange.getRequestURI().getRawPath()));
			} else {
				throw ApplicationExceptions.notFound("The url params is not valid").get();
			}

			exchange.getResponseHeaders().putAll(e.getHeaders());
			exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
			response = super.writeResponse(e.getBody());
		} else {
			throw ApplicationExceptions
					.methodNotAllowed(
							"Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI())
					.get();
		}

		OutputStream os = exchange.getResponseBody();
		os.write(response);
		os.close();
	}

	private ResponseEntity<?> doDelete(String idProduct) throws SQLException {
		productService.deleteProduct(Long.valueOf(idProduct));
		return new ResponseEntity<>("", getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON),
				StatusCode.OK);
	}

	private ResponseEntity<?> doUpdate(InputStream is, String idProduct) throws SQLException {
		Product registerRequest = super.readRequest(is, Product.class);
		Product productResponse = productService.updateProduct(registerRequest, Long.valueOf(idProduct));

		return new ResponseEntity<>(productResponse, getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON),
				StatusCode.OK);
	}

	private ResponseEntity<Product> doPost(InputStream is) throws SQLException {
		Product registerRequest = super.readRequest(is, Product.class);
		Product productResponse = productService.addProduct(registerRequest);

		return new ResponseEntity<>(productResponse, getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON),
				StatusCode.OK);
	}

	private ResponseEntity<Product> doGet(String idProduct) throws NumberFormatException, SQLException {
		Product productResponse = productService.getProduct(Long.valueOf(idProduct));
		return new ResponseEntity<>(productResponse, getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON),
				StatusCode.OK);
	}

	private ResponseEntity<List<Product>> doGetAll() throws SQLException {
		List<Product> productResponse = productService.getProducts();
		return new ResponseEntity<>(productResponse, getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON),
				StatusCode.OK);
	}

	public String splitUrl(String query) {
		if (query == null || "".equals(query)) {
			return "0";
		}
		return query.split("/")[query.split("/").length - 1];
	}
}
