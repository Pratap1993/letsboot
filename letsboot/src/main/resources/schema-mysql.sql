CREATE DATABASE IF NOT EXISTS letsboot;
USE letsboot;

DROP TABLE IF EXISTS link;

CREATE TABLE link (
  id bigint(20) NOT NULL,
  created_by varchar(255) DEFAULT NULL,
  creation_date datetime DEFAULT NULL,
  last_modified_date datetime DEFAULT NULL,
  modified_by varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS comment;

CREATE TABLE comment (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   created_by VARCHAR(255) DEFAULT NULL,
   creation_date datetime DEFAULT NULL,
   last_modified_by VARCHAR(255) DEFAULT NULL,
   last_modified_date datetime DEFAULT NULL,
   body VARCHAR(255) DEFAULT NULL,
   link_id bigint(20) DEFAULT NULL,
   PRIMARY KEY (id),
   KEY FKoutxw6g1ndh1t6282y0fwvami (link_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;