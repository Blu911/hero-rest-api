DROP TABLE IF EXISTS heroes;

CREATE TABLE heroes (
  id bigint(20) NOT NULL auto_increment,
  name VARCHAR(250) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name (name)
);