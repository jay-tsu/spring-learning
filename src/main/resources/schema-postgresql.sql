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

DROP TABLE IF EXISTS system;
CREATE TABLE system (
  ID Bigserial  PRIMARY KEY NOT NULL,
  NAME varchar(100) NOT NULL,
  HEALTH varchar(100) NOT NULL
);

DROP TABLE IF EXISTS log_bundle;
CREATE TABLE log_bundle (
  Id UUID  PRIMARY KEY NOT NULL,
  NAME varchar(100) NOT NULL,
  Types varchar(100) NOT NULL,
  Size int NOT NULL,
  CreationTime timestamp NOT NULL
);

DROP TABLE IF EXISTS job;
CREATE TABLE job (
  Id UUID  PRIMARY KEY NOT NULL,
  Description varchar(256) NULL,
  Owner varchar(100) NULL,
  State varchar(128) NOT NULL,
  Error varchar(256) NULL,
  Progress INT NOT NULL,
  StartTime timestamp NOT NULL,
  EndTime timestamp NULL,
  Target varchar(256) NULL
);