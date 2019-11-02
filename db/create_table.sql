CREATE DATABASE Card_System;
USE Card_System;
CREATE TABLE Cards(
    card_id varchar(40) auto_increment, 
    card_name varchar(50),
    recipient varchar(20),
    event_type varchar(20),
    oritentation varchar(20)
    PRIMARY KEY(card_id)
);

CREATE TABLE Pages(
    page_id varchar(40) AUTO_INCREMENT,
    page_name varchar(10),
    PRIMARY KEY(page_id)
);

CREATE TABLE Texts(
    text_id varchar(40) AUTO_INCREMENT,
    content varchar(1000),
    font varchar(40),
    bounds varchar(40)
    PRIMARY KEY(text_id)
);

CREATE TABLE Images(
    image_id varchar(40) AUTO_INCREMENT,
    image_path varchar(40),
    image_source varchar(1000),
    bounds varchar(40)
    PRIMARY KEY(image_id)
);

CREATE TABLE Page_contain_images(
    combination_id varchar(40) AUTO_INCREMENT,
    page_id int,
    image_id int,
    PRIMARY KEY(combination_id)
);


ALTER TABLE Pages
ADD COLUMN card_id INT;

ALTER TABLE Pages
ADD FOREIGN KEY (card_id) REFERENCES Cards(card_id);

ALTER TABLE Texts
ADD COLUMN page_id INT;

ALTER TABLE Texts
ADD FOREIGN KEY (page_id) REFERENCES Pages(page_id);

ALTER TABLE Page_contain_images
ADD FOREIGN KEY (page_id) REFERENCES Pages(page_id) 
;

ALTER TABLE Page_contain_images
ADD FOREIGN KEY (image_id) REFERENCES Images(image_id) 
;

