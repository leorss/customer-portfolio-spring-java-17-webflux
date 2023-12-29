CREATE TABLE IF NOT EXISTS customer (
  id bigint AUTO_INCREMENT primary key,
  cnpj varchar(255) not null,
  name varchar(255) not null,
  longitude varchar(255) not null,
  latitude varchar(255) not null,
  created_at timestamp not null,
  updated_at timestamp not null
);
