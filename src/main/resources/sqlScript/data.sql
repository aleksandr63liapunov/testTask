insert into Sale (id, name, last_name, age, purchase_item, count, amount,purchase_date)
values (1,'Alex', 'Liapunov', 18, 'TV', 1, 1000,'2018-05-10'),
       (2, 'Oleg', 'Ivanov', 36, 'SMARTPHONE', 3, 3000,'2018-05-10'),
       (3,'Dima', 'Petrov', 23, 'KEYBOARD', 2, 2000,'2018-05-10'),
       (4,'Olia', 'Sidorova', 30, 'HEADPHONES', 3, 1500,'2018-05-10'),
       (5,'Dasha', 'Petrova', 28, 'SMARTPHONE', 1, 1000,'2018-05-10'),
       (6,'Vika', 'Utkina', 18, 'TV', 5, 5000,'2018-05-10');

---------------список покупок за неделю ---------------------------------------------------------
SELECT purchase_item FROM Sale
WHERE purchase_date  >=date(now()-interval '1 week') ;



-------------- список самых прод товаров-------------------------------------------------
select purchase_item
FROM Sale
where purchase_date >= date(now() - interval '1 month')
group by purchase_item
having count(purchase_item) = (SELECT max(count_it)
                               FROM (
                                        SELECT count(purchase_item) as count_it
                                        from Sale
                                        where purchase_date >= date(now() - interval '1 month')
                                        group by purchase_item) е1
);


----------------------------------имя и фамилия человека, совершившего больше всего покупок за полгода
select name,last_name from Sale
where purchase_date >= date(now() - interval '6 month')
group by name, last_name
having count(name)=(SELECT max(count_it)
                    FROM (
                             SELECT name,last_name, COUNT(last_name) AS count_it
                             from Sale
                             where purchase_date >= date(now() - interval '6 month')
                             group by name,last_name) е1);


-------------------------------------Что чаще всего покупают люди в возрасте 18 лет--------------------

select purchase_item, count(purchase_item)
from Sale
where age = 18
group by purchase_item
HAVING COUNT(purchase_item) = (SELECT MAX(count_it)
                               FROM (
                                        SELECT purchase_item, COUNT(*) AS count_it
                                        FROM sale
                                        WHERE age = 18
                                        GROUP BY purchase_item
                                    ) e1);