package com.hsbc.store;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.store.exceptions.GlobalExceptionHandler;
import com.hsbc.store.handlers.ProductHandler;
import com.hsbc.store.repository.ProductDaoImpl;
import com.hsbc.store.service.ProductServiceImpl;
import com.sun.net.httpserver.HttpServer;

public class StoreApplication {
	public static void main(String[] args) throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

		server.createContext("/product", new ProductHandler(Configuration.getUserService(),
				Configuration.getObjectMapper(), Configuration.getErrorHandler())::handle);

		server.setExecutor(null);
		server.start();
	}
}
