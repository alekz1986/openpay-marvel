
CREATE TABLE audit_client
(
    id              varchar(36) not null,
    client          varchar(60) not null,
    hit_time        timestamp not null,
PRIMARY KEY(id)
);

CREATE INDEX idx_client ON audit_client (client);


CREATE TABLE user_info (
  id            varchar(36) NOT NULL,
  user_name     varchar(20) NOT NULL,
  password      varchar(128) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT id_UNIQUE UNIQUE (id),
  CONSTRAINT username_UNIQUE UNIQUE (user_name)
);

CREATE TABLE valid_token (
  id                NUMERIC NOT NULL AUTO_INCREMENT,
  token             varchar(1500) NOT NULL,
  expiration_date   timestamp NOT NULL,
  user_id           varchar(36) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user_info(id)
);


--password: 123456
INSERT INTO user_info (id, user_name, password) VALUES ('2fa6b9fb-d711-41ca-9567-e2c016174e18', 'alexch', 'JGFyZ29uMmlkJHY9MTkkbT00MDk2LHQ9MyxwPTEkbTVYaGZ5TFpHcUFzSHhTUmp4K1JCQSRnYmx4NTN4ZEsyalByaTA4LzhoakZzeE5HMTBGSnVlcmpNWVplQ2p2eWpV');
