CREATE TABLE task5 (
     id BIGSERIAL PRIMARY KEY,
     date DATE NOT NULL,
     description TEXT,
     done BOOLEAN NOT NULL DEFAULT FALSE);

CREATE INDEX task5_date_idx ON task5 (date);
CREATE INDEX task5_done_idx ON task5 (done);