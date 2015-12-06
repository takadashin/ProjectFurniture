/*
SQL Data Transfer
Source Host: localhost
Source Database: furnitureshopping
Target Host: localhost
Target Database: furnitureshopping
Date: 14/11/2015 09:22:06 PM
*/
Insert Into PRODUCTS(cat_id,product_code,product_cost,product_desc,product_name,product_price,product_qty,product_short_desc,product_spec_price,product_post_date) Values('2','QQ','1','SSS','NAME','2','3','SSS','3','2015-12-04')
--Tax table
insert into taxes (taxes_code,taxes_name,taxes_rate) values('aaa','aaa',10);
insert into taxes (taxes_code,taxes_name,taxes_rate) values('bbb','bbb',20);
insert into taxes (taxes_code,taxes_name,taxes_rate) values('ccc','ccc',5);
-------------


--User Table
insert into users (user_username,user_password,user_fullname, user_address, user_city_name, user_state_name, user_state_code, user_email, user_phone, user_type, active) 
    values ('admin_001','admin_001','admin 001', 'at company', '', '','ON','admin001@yahoo.com','','admin','1');
insert into users (user_username,user_password,user_fullname, user_address, user_city_name, user_state_name, user_state_code, user_email, user_phone, user_type, active) 
    values ('user_001','user_001','user 001', '102 Dufferin St.', 'Toronto', 'Ontario','ON','user001@gmail.com','','user','1');
----------------------

--Shipping Method Table
insert into shipping_methods (shipping_method_name, shipping_method_price, shipping_method_description, shipping_method_duration)
    values ('Regular','0', 'This medthod is our regular delivery. There is no fee charge on it.', '14');
insert into shipping_methods (shipping_method_name, shipping_method_price, shipping_method_description, shipping_method_duration)
    values ('EXP','10', 'This medthod is our express delivery. You can get your orders sooner than regular.','5');
-----------


--Order Table
insert into orders (order_num,order_datetime,shipping_address, shipping_datetime, shipped_datetime, order_status, user_id, shipping_method_id) 
    values ('Order_001','2015-12-04','102 Dufferin St', '2015-12-11', '', 'Pending', '2', '2');
insert into orders (order_num,order_datetime,shipping_address, shipping_datetime, shipped_datetime, order_status, user_id, shipping_method_id) 
    values ('Order_002','2015-12-04','54 St.Clair', '2015-12-11', '', 'Pending', '2', '1');
insert into orders (order_num,order_datetime,shipping_address, shipping_datetime, shipped_datetime, order_status, user_id, shipping_method_id) 
    values ('Order_003','2015-12-04','84 Ascot Avenue', '2015-12-11', '', 'Pending', '2', '1');
insert into orders (order_num,order_datetime,shipping_address, shipping_datetime, shipped_datetime, order_status, user_id, shipping_method_id) 
    values ('Order_004','2015-12-04','32 Roger Road', '2015-12-19', '', 'Pending', '2', '2');
insert into orders (order_num,order_datetime,shipping_address, shipping_datetime, shipped_datetime, order_status, user_id, shipping_method_id) 
    values ('Order_005',SYSDATE,'90 Roger Road', '2015-12-19', '', 'Pending', '2', '2');
--------------------------

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
  shipping_method_price float NOT NULL,
  shipping_method_description varchar(200),
  shipping_method_duration NUMBER(4) NOT NULL
);

-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE users (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  user_username varchar(50) NOT NULL,
  user_password varchar(20) NOT NULL,
  user_fullname varchar(50),
  user_address varchar(30) NOT NULL,
  user_city_name varchar(25),
  user_state_name varchar(40),
  user_state_code varchar(2) NOT NULL,
  user_email varchar(50) NOT NULL,
  user_phone varchar(20) default NULL,
  user_type varchar(5) NOT NULL,
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
  order_datetime date default SYSDATE,
  shipping_address varchar(100) NOT NULL, 
  shipping_datetime date NOT NULL,
  shipped_datetime date default NULL,  
  order_status varchar(10) NOT NULL,
  user_id NUMBER(4),
  shipping_method_id NUMBER(4),
  CONSTRAINT FK_orders_userid FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FK_orders_shippingmethodid FOREIGN KEY (shipping_method_id) REFERENCES shipping_methods (id)
);

-- ----------------------------
-- Table structure for products
-- ----------------------------

CREATE TABLE products (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  cat_id NUMBER(4),
  product_code varchar(20) NOT NULL UNIQUE,
  product_name varchar(20) NULL,
  product_short_desc varchar(255) NOT NULL,
  product_desc varchar(500),
  product_cost float default NULL,
  product_price float NOT NULL,
  product_spec_price float default NULL,
  product_qty NUMBER(4)  NOT NULL,
  product_post_date Date default null,
  cat_id NUMBER(4),
  CONSTRAINT FK_products_catid FOREIGN KEY (cat_id) REFERENCES categories (id)
);




-- ----------------------------
-- Table structure for order_details
-- ----------------------------
CREATE TABLE order_details (
  id NUMBER(4) generated as IDENTITY  PRIMARY KEY,   
  product_id NUMBER(4),
  order_id NUMBER(4),
  product_order_qty NUMBER(4) NOT NULL,
  sales_product_price float NOT NULL,
  product_taxes_rate float,
  CONSTRAINT FK_orderdetails_orderid FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT FK_orderdetails_productid FOREIGN KEY (product_id) REFERENCES products (id)
); 

-- ----------------------------
-- Table structure for images
-- ----------------------------
CREATE TABLE images (
  id NUMBER(4) generated as IDENTITY PRIMARY KEY,
  image_name varchar(50) NOT NULL,
  content_type varchar(20) NOT NULL,
  product_id NUMBER(4) NOT NULL,
  CONSTRAINT FK_productimages_productid FOREIGN KEY (product_id) REFERENCES products (id)
); 










