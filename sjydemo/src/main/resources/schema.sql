DROP TABLE IF EXISTS user;

CREATE TABLE user(
user_no INT NOT NULL AUTO_INCREMENT,
email VARCHAR(30),
name VARCHAR(30),
tel VARCHAR(30),
PRIMARY KEY (user_no)
);

DROP TABLE IF EXISTS user_img;

CREATE TABLE user_img(
img_no INT NOT NULL AUTO_INCREMENT,
name VARCHAR(30),
img_name VARCHAR(300),
PRIMARY KEY (img_no)
);