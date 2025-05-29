CREATE TABLE revinfo
(
    rev      SERIAL PRIMARY KEY,
    revtstmp BIGINT
);

CREATE TABLE change_status
(
    change_status_id   BIGSERIAL PRIMARY KEY,
    change_status_name VARCHAR(255),
    status             VARCHAR(20),
    created_date       TIMESTAMP,
    updated_date       TIMESTAMP
);


CREATE TABLE change_status_AUD
(
    change_status_id   BIGSERIAL,
    rev                INTEGER NOT NULL,
    revtype            SMALLINT,
    change_status_name VARCHAR(255),
    status             VARCHAR(20),
    created_date       TIMESTAMP,
    updated_date       TIMESTAMP,
    PRIMARY KEY (change_status_id, rev)
);

CREATE TABLE role
(
    role_id      BIGSERIAL PRIMARY KEY,
    role_name    VARCHAR(255),
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE TABLE role_AUD
(
    role_id      BIGSERIAL,
    rev          INTEGER NOT NULL,
    revtype      SMALLINT,
    role_name    VARCHAR(255),
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    PRIMARY KEY (role_id, rev)
);

CREATE TABLE "user"
(
    user_id       BIGSERIAL PRIMARY KEY,
    user_name     VARCHAR(255),
    user_password VARCHAR(255),
    user_status   VARCHAR(50),
    role_id       BIGSERIAL,
    created_date  TIMESTAMP,
    updated_date  TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE TABLE user_AUD
(
    user_id       BIGSERIAL,
    rev           INTEGER NOT NULL,
    revtype       SMALLINT,
    user_name     VARCHAR(255),
    user_password VARCHAR(255),
    user_status   VARCHAR(50),
    role_id       BIGSERIAL,
    created_date  TIMESTAMP,
    updated_date  TIMESTAMP,
    PRIMARY KEY (user_id, rev)
);

CREATE TABLE change
(
    change_id               BIGSERIAL PRIMARY KEY,
    change_name             VARCHAR(255),
    change_description      TEXT,
    criticality_level       VARCHAR(20),
    created_by              BIGSERIAL,
    approver_id             BIGSERIAL,
    status_change_status_id BIGSERIAL,
    created_date            TIMESTAMP,
    updated_date            TIMESTAMP,
    FOREIGN KEY (status_change_status_id) REFERENCES change_status (change_status_id)
);

CREATE TABLE change_AUD
(
    change_id               BIGSERIAL,
    rev                     INTEGER NOT NULL,
    revtype                 SMALLINT,
    change_name             VARCHAR(255),
    change_description      TEXT,
    criticality_level       VARCHAR(20),
    created_by              BIGSERIAL,
    approver_id             BIGSERIAL,
    status_change_status_id BIGSERIAL,
    created_date            TIMESTAMP,
    updated_date            TIMESTAMP,
    PRIMARY KEY (change_id, rev)
);

CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE revinfo ALTER COLUMN rev SET DEFAULT nextval('revinfo_seq');
ALTER SEQUENCE revinfo_seq INCREMENT BY 50;

ALTER TABLE "user" RENAME TO users;
ALTER TABLE changemanagement.user_aud RENAME TO users_aud;