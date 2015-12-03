/*
SQL Data Transfer
Source Host: localhost
Source Database: furnitureshopping
Target Host: localhost
Target Database: furnitureshopping
Date: 14/11/2015 09:22:06 PM
*/
insert into taxes(taxes_code,taxes_name,taxes_rate) values('aaa','aaa',10);

insert into taxes values(,'bbb','bbb',20);
insert into taxes values('ccc','ccc',5);

drop table order_details;
drop table taxes;
drop table product_images;
drop table images;
drop table products;
drop table orders;
drop table categories;
drop table shipping_methods;
drop table users;

-- ----------------------------
-- Table structure for taxes
-- ----------------------------
CREATE TABLE taxes (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  taxes_code varchar(10) NOT NULL,
  taxes_name varchar(40) NOT NULL,
  taxes_rate float NOT NULL
);
-- ----------------------------
-- Table structure for shipping_methods
-- ----------------------------

CREATE TABLE shipping_methods (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  shipping_method_name varchar(40) NOT NULL,
  shipping_method_price float NOT NULL
);

-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE users (
  id varchar(20) generated as IDENTITY PRIMARY KEY,
  user_username varchar(50) NOT NULL,
  user_password varchar(20) NOT NULL,
  user_fullname varchar(50),
  user_address varchar(30) NOT NULL,
  user_city_name varchar(25) NOT NULL,
  user_state_name varchar(40) NOT NULL,
  user_state_code varchar(2) NOT NULL,
  user_email varchar(50) NOT NULL,
  user_phone varchar(20) default NULL,
  user_type varchar(1) NOT NULL,
  active char(1) NOT NULL
);

-- ----------------------------
-- Table structure for categories
-- ----------------------------
CREATE TABLE categories (
	id NUMBER(4) generated as IDENTITY PRIMARY KEY,
	cat_name varchar(50) NOT NULL,
	cat_description varchar(100) NULL,
	cat_parent_id NUMBER(4) ,
	CONSTRAINT FK_categories_catparentid FOREIGN KEY (cat_parent_id) REFERENCES categories (id)
);


-- ----------------------------
-- Table structure for orders
-- ----------------------------

CREATE TABLE orders (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  order_num varchar(20) NOT NULL,
  shipping_address varchar(100) NOT NULL, 
  shipped_datetime date default NULL,  
  order_datetime date NOT NULL,
  order_status varchar(1) NOT NULL,
  user_id varchar(20),
  shipping_method_id NUMBER(4)  default NULL,
  CONSTRAINT FK_orders_userid FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FK_orders_shippingmethodid FOREIGN KEY (shipping_method_id) REFERENCES shipping_methods (id)
);

-- ----------------------------
-- Table structure for products
-- ----------------------------

CREATE TABLE products (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  product_num varchar(20) NOT NULL,
  product_short_desc varchar(128) NOT NULL,
  product_desc varchar(500),
  product_cost float default NULL,
  product_price float NOT NULL,
  product_spec_price float default NULL,
  product_rating float NOT NULL,
  product_rating_count NUMBER(4)  NOT NULL,
  product_qty NUMBER(4)  NOT NULL,
  product_publish_on date NOT NULL,
  product_expire_on date NOT NULL,
  cat_id NUMBER(4) ,
  CONSTRAINT FK_products_catid FOREIGN KEY (cat_id) REFERENCES categories (id)
);



-- ----------------------------
-- Table structure for order_details
-- ----------------------------
CREATE TABLE order_details (
  id NUMBER(4) generated as IDENTITY  PRIMARY KEY, 
  product_order_qty NUMBER(4)  NOT NULL,
  product_id NUMBER(4) ,
  order_id NUMBER(4) ,
  taxes_id NUMBER(4) ,
  CONSTRAINT FK_orderdetails_orderid FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT FK_orderdetails_productid FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT FK_orderdetails_taxesid FOREIGN KEY (taxes_id) REFERENCES taxes (id)
); 

-- ----------------------------
-- Table structure for images
-- ----------------------------
CREATE TABLE images (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  image_name varchar(50) NOT NULL,
  content_type varchar(20) NOT NULL
); 



-- ----------------------------
-- Table structure for product_images
-- ----------------------------
CREATE TABLE product_images (
  id NUMBER(4) generated as IDENTITY  PRIMARY KEY,
  product_id NUMBER(4) ,
  image_id NUMBER(4) ,
  CONSTRAINT FK_productimages_productid FOREIGN KEY (product_id) REFERENCES products (id),
  CONSTRAINT FK_productimages_imageid FOREIGN KEY (image_id) REFERENCES images (id)
); 









