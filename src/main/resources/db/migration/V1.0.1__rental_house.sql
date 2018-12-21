use airbnb;
create table rental_house
(
  id               Int(20) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  title            NVARCHAR(255)          NOT NULL,
  description      TEXT NOT NULL,
  status           BOOLEAN,
  post_date        NVARCHAR(255),
  price            BIGINT,
  user_id          INT(20),
  category_id      INT(20),
  quantity_bedroom INT(20),
  quantity_toilet  INT(20),
  quantity_storey  INT(20),
  province         NVARCHAR(255),
  address          NVARCHAR(512),
  featured         NVARCHAR(255),
  house_area       INT(255),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (category_id) REFERENCES category(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table image_house
(
  id              Int(20) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name            NVARCHAR(255),
  alt             NVARCHAR(1028),
  link            NVARCHAR(1028),
  rental_house_id Int(20)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table category
(
  id   INT(20)       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name NVARCHAR(512) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table user
(
  id       INT(20)       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username NVARCHAR(255) NOT NULL,
  password NVARCHAR(255) NOT NULL,
  email    NVARCHAR(512) NOT NULL,
  province NVARCHAR(255),
  address  NVARCHAR(999),
  phone    NVARCHAR(13),
  age      INT(25),
  gender   BOOLEAN
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table order_detail(
  id INT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  price BIGINT DEFAULT 0,
  date_begin NVARCHAR(255),
  date_end NVARCHAR(255),
  username NVARCHAR(255),
  rental_house_id INT(20),
  FOREIGN KEY (username) REFERENCES user(username),
  FOREIGN KEY (rental_house_id) REFERENCES rental_house(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
