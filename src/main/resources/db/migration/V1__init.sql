CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE company
(
    id         uuid PRIMARY KEY      DEFAULT gen_random_uuid(),
    name       varchar(255) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version    bigint       NOT NULL DEFAULT 0
);

CREATE TABLE person
(
    id         uuid PRIMARY KEY      DEFAULT gen_random_uuid(),
    full_name  varchar(255) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version    bigint       NOT NULL DEFAULT 0
);

CREATE TABLE task
(
    id         uuid PRIMARY KEY      DEFAULT gen_random_uuid(),
    title      varchar(255) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version    bigint       NOT NULL DEFAULT 0
);

CREATE TABLE company_person
(
    company_id uuid        NOT NULL,
    person_id  uuid        NOT NULL,
    position   varchar(64) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version    bigint      NOT NULL DEFAULT 0,
    PRIMARY KEY (company_id, person_id),
    CONSTRAINT fk_company_person_company FOREIGN KEY (company_id) REFERENCES company (id),
    CONSTRAINT fk_company_person_person FOREIGN KEY (person_id) REFERENCES person (id)
);

CREATE INDEX idx_company_person_person_id ON company_person (person_id);

CREATE TABLE person_task
(
    person_id          uuid        NOT NULL,
    task_id            uuid        NOT NULL,
    participation_type varchar(64) NOT NULL,
    created_at         timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version            bigint      NOT NULL DEFAULT 0,
    PRIMARY KEY (person_id, task_id),
    CONSTRAINT fk_person_task_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT fk_person_task_task FOREIGN KEY (task_id) REFERENCES task (id)
);

CREATE INDEX idx_person_task_task_id ON person_task (task_id);
