
create table user_accounts(
	id 				bigint ,
	name 			varchar(150) not null,
	phone 			varchar(12) not null,
	email 			varchar(200) not null,
 	address 		varchar(200) ,
	country 		varchar(56) not null,
	department 		varchar(50) not null,
	primary key (id)
);