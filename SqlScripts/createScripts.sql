create database Cinema 
go

use Cinema
go

drop table if exists Schedule
go

drop table if exists Movie
go

create table Movie (
	Id int not null identity(1,1) primary key,
	Name varchar(55) not null,
	Genre varchar(55) not null
)

create table Schedule (
	Id int not null identity(1, 1) primary key,
	Time datetime2(7) not null,
	MovieId int foreign key references Movie (Id),
	Price decimal not null,
	HallId int not null
)
go
