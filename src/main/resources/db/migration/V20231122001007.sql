CREATE TABLE "user" (
    id            BIGSERIAL PRIMARY KEY,
    login         TEXT NOT NULL,
    password      TEXT NOT NULL,
    UNIQUE(login));

CREATE INDEX user_login_idx ON "user" (login);