-- Tabela Programming_Language
CREATE TABLE programming_language (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    template_link VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Team
CREATE TABLE team (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manager VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Person
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    team_id INT REFERENCES team(id) ON DELETE SET NULL
);

-- Tabela Project
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    programming_language_id INT REFERENCES programming_language(id) ON DELETE SET NULL,
    team_id INT REFERENCES team(id) ON DELETE SET NULL,
    repository_url VARCHAR(255),
    project_url VARCHAR(255),
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela Project_Scope
CREATE TABLE project_scope (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    project_id INT REFERENCES project(id) ON DELETE CASCADE
);

-- Tabela Project_Version
CREATE TABLE project_version (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_notes TEXT,
    release_date DATE NOT NULL,
    project_id INT REFERENCES project(id) ON DELETE CASCADE
);

-- Constraints and Relationships
ALTER TABLE person
ADD CONSTRAINT fk_team
FOREIGN KEY (team_id)
REFERENCES team(id);

ALTER TABLE project
ADD CONSTRAINT fk_programming_language
FOREIGN KEY (programming_language_id)
REFERENCES programming_language(id);

ALTER TABLE project
ADD CONSTRAINT fk_team
FOREIGN KEY (team_id)
REFERENCES team(id);

ALTER TABLE project_scope
ADD CONSTRAINT fk_project
FOREIGN KEY (project_id)
REFERENCES project(id);

ALTER TABLE project_version
ADD CONSTRAINT fk_project
FOREIGN KEY (project_id)
REFERENCES project(id);

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE project_permission (
    id SERIAL PRIMARY KEY,
    project_id INT REFERENCES project(id) ON DELETE CASCADE,
    person_id INT REFERENCES person(id) ON DELETE CASCADE,
    team_id INT REFERENCES team(id) ON DELETE CASCADE,
    role_id INT REFERENCES role(id) ON DELETE CASCADE,
    UNIQUE (project_id, person_id),
    UNIQUE (project_id, team_id)
);

CREATE TABLE approval_workflow (
    id SERIAL PRIMARY KEY,
    project_id INT REFERENCES project(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE workflow_step (
    id SERIAL PRIMARY KEY,
    workflow_id INT REFERENCES approval_workflow(id) ON DELETE CASCADE,
    approver_id INT REFERENCES person(id) ON DELETE CASCADE,
    step_order INT NOT NULL,
    status VARCHAR(255) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE team
ADD COLUMN manager_id INT REFERENCES person(id) ON DELETE SET NULL;

UPDATE team SET manager_id = person.id FROM person WHERE person.name = team.manager;

ALTER TABLE team DROP COLUMN manager;

ALTER TABLE project
ADD COLUMN created_by INT REFERENCES person(id) ON DELETE SET NULL;

INSERT INTO role (name) VALUES
('ADMIN'),
('MANAGER'),
('DEVELOPER');

-- Adicionar a coluna role_id à tabela person
ALTER TABLE person
ADD COLUMN role_id INT;

-- Adicionar a chave estrangeira para a coluna role_id
ALTER TABLE person
ADD CONSTRAINT fk_role
FOREIGN KEY (role_id)
REFERENCES role(id)
ON DELETE SET NULL;  -- Ou ON DELETE CASCADE, dependendo do comportamento desejado
