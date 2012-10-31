--code to make a danger zone table

create table tbl_danger_zone
(
	pk_id int(11) not null primary key auto_increment,
	fld_latitude float,
	fld_longitude float,
	fld_time_stamp int(11),
	fld_category int(11)
);
