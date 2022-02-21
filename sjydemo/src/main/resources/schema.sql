
/*강제로 참조관계를 끊고 부모테이블 삭제*/
DROP TABLE IF EXISTS user CASCADE CONSTRAINTS;
DROP TABLE IF EXISTS user_img;

/*
CREATE TABLE user(
user_no INT NOT NULL AUTO_INCREMENT,
email VARCHAR(30),
name VARCHAR(30),
phone VARCHAR(30),
CONSTRAINT user_pk
PRIMARY KEY (user_no)
);
*/

/*
CREATE TABLE user_img(
img_no INT NOT NULL AUTO_INCREMENT,
user_no INT NOT NULL,
name VARCHAR(30),
img_name VARCHAR(300),
CONSTRAINT user_img_pk
FOREIGN KEY (user_no)
REFERENCES user(user_no)
ON DELETE CASCADE
);
*/

CREATE TABLE user(
user_no INT NOT NULL AUTO_INCREMENT,
email VARCHAR(30),
name VARCHAR(30),
phone VARCHAR(30),
PRIMARY KEY (user_no)
);

CREATE TABLE user_img(
img_no INT NOT NULL AUTO_INCREMENT,
user_no INT NOT NULL,
name VARCHAR(30),
img_name VARCHAR(300),
PRIMARY KEY (img_no)
);