DROP TABLE IF EXISTS cluster;
CREATE TABLE cluster (
  ID Bigserial  PRIMARY KEY NOT NULL,
  NAME varchar(100) NOT NULL
);

DROP TABLE IF EXISTS node;
CREATE TABLE node (
  ID Bigserial  PRIMARY KEY NOT NULL,
  NAME varchar(100) NOT NULL
);