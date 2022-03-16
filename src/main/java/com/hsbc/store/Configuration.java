package com.hsbc.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.store.exceptions.GlobalExceptionHandler;
import com.hsbc.store.repository.ProductDao;
import com.hsbc.store.repository.ProductDaoImpl;
import com.hsbc.store.service.ProductService;
import com.hsbc.store.service.ProductServiceImpl;

class Configuration {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final ProductDao PRODUCT_REPOSITORY = new ProductDaoImpl();
	private static final ProductService USER_SERVICE = new ProductServiceImpl(PRODUCT_REPOSITORY);
	private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);

	static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}

	public static ProductDao getProductRepository() {
		return PRODUCT_REPOSITORY;
	}

	public static ProductService getUserService() {
		return USER_SERVICE;
	}

	public static GlobalExceptionHandler getErrorHandler() {
		return GLOBAL_ERROR_HANDLER;
	}
}