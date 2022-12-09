insert into bestellijnen (bestelId, bierId)
values ((select id from bestellingen where naam = 'testnaam1'), (select id from bieren where naam = 'testnaam1'));