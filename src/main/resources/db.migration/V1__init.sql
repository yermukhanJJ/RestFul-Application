CREATE TABLE product (
    id serial NOT NULL,
    title VARCHAR(100) NOT NULL,
    description text NOT NULL,
    price int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE article (
    id serial NOT NULL,
    id_product int NOT NULL,
    title text NOT NULL,
    content text NOT NULL,
    dt timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_product)
    REFERENCES product (id)
);