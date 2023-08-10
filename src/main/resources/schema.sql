
CREATE TABLE audit_client
(
    id              varchar(36) not null,
    client          varchar(60) not null,
    hit_time        timestamp not null,
PRIMARY KEY(id)
);

CREATE INDEX idx_client ON audit_client (client);