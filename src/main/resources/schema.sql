CREATE TABLE IF NOT EXISTS tms_user_config
(
    id          INT AUTO_INCREMENT  PRIMARY KEY,
    uuid        VARCHAR(64)  NOT NULL UNIQUE,
    username    VARCHAR2(128) NOT NULL,
    password    VARCHAR2(128) NOT NULL,
    version     INT      NOT NULL
);