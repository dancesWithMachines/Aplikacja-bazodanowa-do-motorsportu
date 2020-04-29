--tworzenie bazy
create database pojazdyIKierowcy
go
--dodanie 2 loginów
create login regularUser with password='regular'
go
create login masterUser with password='zaq1@WSX'
go
use pojazdyIKierowcy
go
--dodanie użytkowników
create user regularUser for login regularUser
create user masterUser for login masterUser
--przydzielenie roli
go
sp_addrolemember @rolename='db_datawriter',@membername='regularUser'
go
sp_addrolemember @rolename='db_denydatareader',@membername='regularUser'
go
sp_addrolemember @rolename='db_datawriter',@membername='masterUser'
go
sp_addrolemember @rolename='db_datareader',@membername='masterUser'
go
--tabela z informacjami o pojeździe
create table pojazd (
	numerRejPojazdu varchar(10) primary key,
	marka varchar(50) not null,
	model varchar(50) not null,
	pojemnoscWCm3 numeric(5) not null,
	rodzajDoladowania varchar(1),
	mocWKm numeric(5) not null,
	kodSilnika varchar(20) not null
)
--tabela z informacjami o użytkownikach
create table zawodnik (
	id int identity(1,1) primary key,
	imie varchar(50) not null,
	nazwisko varchar(50) not null,
	plec varchar(1) not null,
	wiek numeric(3) not null,
	zespol varchar(50),
	numerRejPojazdu varchar(10) not null foreign key (numerRejPojazdu) references pojazd(numerRejPojazdu)
)
go
--tabela z wynikami czasowymi
create table czas (
id int identity(1,1) primary key,
idZawodnika int not null foreign key (idzawodnika) references zawodnik(id),
najlepszyCzas time(0)
)
go
--Dodanie jakiegoś tam użytkownika
insert into pojazd values ('GDA0001','Vauxhall','Vectra C', 1998,'T',150,'Z19DTH')
insert into zawodnik values ('Mateusz','Kusiak','M',21,null,'GDA0001')
select * from zawodnik
go
----procedura dodawania pojazdu
--create procedure dodajPojazd
--@numerRejPojazdu varchar(10),
--@marka varchar(50),
--@model varchar(50),
--@pojemnoscWCm3 numeric(5), 
--@rodzajDoladowania varchar(1),
--@mocWKm numeric(5),
--@kodSilnika varchar(20) 
--as
--	insert into pojazd values(@numerRejPojazdu,@marka,@model,@pojemnoscWCm3,@rodzajDoladowania,@mocWKm,@kodSilnika)
--go
----procedura dodawania zawodnika
--create procedure dodajZawodnika
--@imie varchar(50),
--@nazwisko varchar(50), 
--@plec varchar(1), 
--@wiek numeric(3), 
--@zespol varchar(50),
--@numerRejPojazdu varchar(10)
--as
--	insert into zawodnik values(@imie,@nazwisko,@plec,@wiek,@zespol,@numerRejPojazdu);
--	insert into czas values(
--	(select id from zawodnik where numerRejPojazdu=@numerRejPojazdu),'00:00:00'
--	)
--go
--procedura dodawania zawodnika i samochodu
create procedure dodajPojazdIZawodnika
@numerRejPojazdu varchar(10),
@marka varchar(50),
@model varchar(50),
@pojemnoscWCm3 numeric(5), 
@rodzajDoladowania varchar(1),
@mocWKm numeric(5),
@kodSilnika varchar(20),
@imie varchar(50),
@nazwisko varchar(50), 
@plec varchar(1), 
@wiek numeric(3), 
@zespol varchar(50) 
as
begin
	insert into pojazd values(@numerRejPojazdu,@marka,@model,@pojemnoscWCm3,@rodzajDoladowania,@mocWKm,@kodSilnika)
	insert into zawodnik values(@imie,@nazwisko,@plec,@wiek,@zespol,@numerRejPojazdu);
	insert into czas values(
	(select id from zawodnik where numerRejPojazdu=@numerRejPojazdu),'00:00:00'
	)
end
go
-- przyznanie uprawnień zwykłemu użytkownikowi
grant execute on dodajPojazd to regularUser
grant execute on dodajZawodnika to regularUser
grant execute on dodajPojazdIZawodnika to regularUser
exec dodajPojazdIZawodnika 'GDA0005','OPL','CORS',999,'T',54,'ZWD23','Janusz','Petarda','M',19,''
--procedura pobrania pojazdu
go
create procedure pobierzDanePojazdu
@numerRejPojazdu varchar(10)
as
	select * from pojazd where numerRejPojazdu=@numerRejPojazdu
go
--procedura pobrania zawodnika
create procedure pobierzDaneZawodnika
@numerRejPojazdu varchar(10)
as
	select * from zawodnik where numerRejPojazdu=@numerRejPojazdu
go
--procedura pobrania czasu
create procedure pobierzCzas
@numerRejPojazdu varchar(10)
as
begin
	select idZawodnika, najlepszyCzas from czas 
	where idZawodnika = (select id from zawodnik where numerRejPojazdu = @numerRejPojazdu)
end
go
--procedura modyfikuj czas
create procedure modyfikujCzas
@numerRejPojazdu varchar(10), 
@najlepszyCzas time(0)
as
begin
	update czas set najlepszyCzas=@najlepszyCzas
	where idZawodnika = (select id from zawodnik where numerRejPojazdu = @numerRejPojazdu)
end
go
----procedura modyfikacji pojazdu
--create procedure modyfikujPojazd
--@numerRejPojazdu varchar(10),
--@marka varchar(50),
--@model varchar(50),
--@pojemnoscWCm3 numeric(5), 
--@rodzajDoladowania varchar(1),
--@mocWKm numeric(5),
--@kodSilnika varchar(20) 
--as
--begin
--	update pojazd set numerRejPojazdu=@numerRejPojazdu, marka=@marka, model=@model, pojemnoscWCm3=@pojemnoscWCm3, rodzajDoladowania=@rodzajDoladowania, mocWKm=@mocWKm, kodSilnika=@kodSilnika
--	where numerRejPojazdu=@numerRejPojazdu
--end
--go
----procedura modyfikacji zawodnika
--create procedure modyfikujZawodnika
--@numerRejPojazdu varchar(10),
--@imie varchar(50),
--@nazwisko varchar(50), 
--@plec varchar(1), 
--@wiek numeric(3), 
--@zespol varchar(50)
--as
--begin
--	update zawodnik set imie=@imie, nazwisko=@nazwisko, plec=@plec, wiek=@wiek, zespol=@zespol
--	where numerRejPojazdu = @numerRejPojazdu
--end
--go
-- procedura modyfikacji PojazduIZawodnika
create procedure modyfikujPojazdIZawodnika
@numerRejSzukany varchar(10),
@marka varchar(50),
@model varchar(50),
@pojemnoscWCm3 numeric(5), 
@rodzajDoladowania varchar(1),
@mocWKm numeric(5),
@kodSilnika varchar(20),
@imie varchar(50),
@nazwisko varchar(50), 
@plec varchar(1), 
@wiek numeric(3), 
@zespol varchar(50) 
as
begin transaction
	update zawodnik set imie=@imie, nazwisko=@nazwisko, plec=@plec, wiek=@wiek, zespol=@zespol
	where numerRejPojazdu = @numerRejSzukany;
	update pojazd set marka=@marka, model=@model, pojemnoscWCm3=@pojemnoscWCm3, rodzajDoladowania=@rodzajDoladowania, mocWKm=@mocWKm, kodSilnika=@kodSilnika
	where numerRejPojazdu=@numerRejSzukany;
	
commit;
go
--procedura usuwania danych
create procedure usunDane
@numerRejPojazdu varchar(10)
as
begin
	delete from czas where idZawodnika = (select id from zawodnik where numerRejPojazdu = @numerRejPojazdu);
	delete from zawodnik where numerRejPojazdu = @numerRejPojazdu;
	delete from pojazd where numerRejPojazdu = @numerRejPojazdu;
end
go
--procedura wypisania wyników
create procedure wypiszWyniki
as
begin
	begin
		select z.id, z.imie, z.nazwisko, p.marka, p.model, c.najlepszyCzas, p.pojemnoscWCm3, p.rodzajDoladowania
		from zawodnik z, pojazd p, czas c
		where p.numerRejPojazdu=z.numerRejPojazdu and z.id = c.idZawodnika
		order by c.najlepszyCzas ASC
	end
end
go
--przypisanie uprawnień użytkownikowi master
grant execute on pobierzDanePojazdu to masterUser
grant execute on pobierzDaneZawodnika to masterUser
grant execute on pobierzCzas to masterUser
grant execute on modyfikujPojazd to masterUser
grant execute on modyfikujCzas to masterUser
grant execute on modyfikujZawodnika to masterUser
grant execute on modyfikujPojazdIZawodnika to masterUser
grant execute on usunDane to masterUser
grant execute on wypiszWyniki to masterUser
--testy
execute dodajPojazd 'GDA0001','Opel','Vectra C',1998,'T',150,'z19dth'
execute dodajZawodnika 'Mateusz','Kusiak','M',21,null, 'GDA0001'
execute pobierzDanePojazdu 'GDA0001'
execute pobierzDaneZawodnika 'GDA0001'
execute pobierzCzas 'GDA0001'
execute modyfikujCzas 'GDA0001','01:04:04'
execute modyfikujPojazd 'GDA0002','Honda','Civic',1550,'N',110,'d16v1'
execute modyfikujZawodnika 'GDA0002','Random','Guy','M',19,'Haggard Garage'
execute wypiszWyniki 
execute usunDane '2'
select * from pojazd
select * from zawodnik
select * from czas
delete from pojazd where numerRejPojazdu='GDA0004'
execute modyfikujPojazdIZawodnika 'GDA0005','Opel','Corsa',980, 'N', 54, 'd1a4', 'Zbigniew','Ziobro','M',99,'Martini racing'
go