DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  role_id serial primary key,
  role varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  user_id serial,
  active int DEFAULT NULL,
  email int NOT NULL,
  last_name int NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role(
  user_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (user_id,role_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
