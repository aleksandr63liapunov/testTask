create type purchase_item as enum ('TV', 'SMARTPHONE', 'JUICER', 'HEADPHONES', 'KEYBOARD');



CREATE TABLE Sale (
                      id  serial  primary key ,
                      name VARCHAR(30) NOT NULL,
                      last_name VARCHAR(30) NOT NULL,
                      age SMALLINT NOT NULL,
                      purchase_item purchase_item ,
                      count INT NOT NULL,
                      amount FLOAT NOT NULL,
                      purchase_date timestamp without time zone NOT NULL

);
