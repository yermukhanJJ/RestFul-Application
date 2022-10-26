# RestFul-Application

Restful CRUD Application

GET http://localhost:8085/app/api/v1/products ---------Выводить все продукты из таблица product

GET http://localhost:8085/app/api/v1/products/id ------Найти продукт по id

GET http://localhost:8085/app/api/v1/products?min=[min_price]&max=[max_price] ------Отсортировать продукты по минимальной и максимальной цене

GET http://localhost:8085/app/api/v1/articles ---------Выводить все статьи 

GET http://localhost:8085/app/api/v1/articles/id ------Найти статью по id

GET http://localhost:8085/app/api/v1/articles?word=[keyword] --Найти статьи по названию продукта

POST http://localhost:8085/app/api/v1/products --------Добавить новый продукт

POST http://localhost:8085/app/api/v1/articles --------Добавить новую статью

PUT http://localhost:8085/app/api/v1/products/id ------Изменить данные продукта 

PUT http://localhost:8085/app/api/v1/articles/id ------Изменить данные статьи

DELETE http://localhost:8085/app/api/v1/articles/id ---Удалить статьи

DELETE http://localhost:8085/app/api/v1/products/id ---Удалить продукты

Скрипты Sql базы данных "V1__init.sql" лежить "resources\db\migration"
