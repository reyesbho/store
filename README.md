# store
Store practice
--- Creation database in POSTGRESQL -- 

create database store;

create table product (
	id_product serial unique not null primary key,
	clave varchar(20) unique not null,
	nombre varchar(100) not null, 
	descripcion varchar(300),
	precio numeric(10,2) not null,
	stock numeric(10,0) not null, 
	proveedor varchar(100)
);

NOTA: Para ejecutar el proyecto ejecutar la clase StoreApplication.java 

-- Crear producto --- 
ULR:  localhost:8080/product
METOD: POST 
JSON_BODY: 

{
    "clave":"P-0002",
    "nombre":"Mochilas",
    "descripcion":"Mochila roja, piel natural",
    "precio":200.20,
    "stock":2,
    "proveedor":"adidas"
}

-- Actualizar producto
ULR:   localhost:8080/product/{idProduct} Ejemplo ->  localhost:8080/product/1
METOD: PUT 
JSON_BODY:
	{
        "nombre": "Zapato",
        "descripcion": "Zapatos de la marca adidas",
        "precio": 19.0,
        "stock": 20,
        "proveedor": "adidas"
    }
	
-- obtener producto
ULR:    localhost:8080/product/{idProduct} Ejemplo ->  localhost:8080/product/1
METOD: GET 



-- Obtener productos 
ULR:    localhost:8080/product/list
METOD: GET 
