create database buchalter;
user buchalter;

create table reports (
	id int(12) not null unique,
	parent_id int(12),
	code varchar(16),
	type varchar(16),
	year int(4),
	quarter int(4),
	month int(4),
	primary key (id)
);

alter table reports change id id int(12) not null auto_increment;

create table expense_records (
	id int(12) not null unique,
	report_id int(12) not null,
	type varchar(16) not null,
	date varchar(16),
	title varchar(64),
	net_value decimal(12, 2),
	vat_rate varchar(8),
	vat_value decimal(12, 2),
	gross_value decimal(12, 2),
	pit_value decimal(12, 2),
	vat_deduct_rate varchar(8),
	vat_deduct_value decimal(12, 2),
	primary key (id)
);

alter table expense_records change id id int(64) not null auto_increment;

create table income_records (
	id int(12) not null unique,
	report_id int(12) not null,
	type varchar(16) not null,
	date varchar(16),
	title varchar(64),
	net_value decimal(12, 2),
	vat_rate varchar(8),
	vat_value decimal(12, 2),
	gross_value decimal(12, 2),
	pit_value decimal(12, 2),
	vat_deduct_rate varchar(8),
	vat_deduct_value decimal(12, 2),
	primary key (id)
);

alter table income_records change id id int(64) not null auto_increment;