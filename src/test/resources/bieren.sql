insert into bieren(naam, brouwerId, alcohol, prijs, besteld)
VALUES
    ('testnaam1', (select id from brouwers where naam = 'testnaam1'), 5.0, 10, 1),
    ('testnaam2', (select id from brouwers where naam = 'testnaam1'), 7.0, 13, 2);