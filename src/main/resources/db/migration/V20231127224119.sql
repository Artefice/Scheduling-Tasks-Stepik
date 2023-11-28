ALTER TABLE task ADD COLUMN user_id BIGINT NOT NULL default 1;

ALTER TABLE task    ADD CONSTRAINT task_user_id_fkey
                    FOREIGN KEY (user_id)
                    REFERENCES "user"(id);

CREATE INDEX task_user_id_idx ON task (user_id);