CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE project
(
    id           BIGINT    NOT NULL,
    name         VARCHAR   NOT NULL,
    passwordhash VARCHAR   NOT NULL,
    createdby    VARCHAR   NOT NULL,
    createdat    TIMESTAMP NOT NULL,
    updatedby    VARCHAR   NOT NULL,
    updatedat    TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE player
(
    id           BIGINT    NOT NULL,
    name         VARCHAR   NOT NULL,
    passwordhash VARCHAR   NOT NULL,
    createdat    TIMESTAMP NOT NULL,
    createdby    VARCHAR   NOT NULL,
    updatedat    TIMESTAMP NOT NULL,
    updatedby    VARCHAR   NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name),
);

CREATE TABLE game
(
    id         BIGINT    NOT NULL,
    name       VARCHAR   NOT NULL,
    project_id BIGINT    NOT NULL,
    createdby  VARCHAR   NOT NULL,
    createdat  TIMESTAMP NOT NULL,
    updatedby  VARCHAR   NOT NULL,
    updatedat  TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (project_id, name),
    CONSTRAINT fk_game_project FOREIGN KEY (project_id) REFERENCES project
);

CREATE TABLE match
(
    id        BIGINT    NOT NULL,
    date      TIMESTAMP NOT NULL,
    game_id   BIGINT    NOT NULL,
    createdby VARCHAR   NOT NULL,
    createdat TIMESTAMP NOT NULL,
    updatedby VARCHAR   NOT NULL,
    updatedat TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_match_game FOREIGN KEY (game_id) REFERENCES game
);


CREATE TABLE result_table
(
    result VARCHAR UNIQUE
);

INSERT INTO result_table (result)
VALUES ('WIN'),
       ('DRAW'),
       ('LOSS');

CREATE TABLE team
(
    id        BIGINT    NOT NULL,
    result    VARCHAR,
    score     INTEGER   NOT NULL,
    match_id  BIGINT    NOT NULL,
    createdby VARCHAR   NOT NULL,
    createdat TIMESTAMP NOT NULL,
    updatedby VARCHAR   NOT NULL,
    updatedat TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_team_match FOREIGN KEY (match_id) REFERENCES match,
    CONSTRAINT fk_team_result FOREIGN KEY (result) REFERENCES result_table (result)
);


CREATE TABLE player_project
(
    players_id  BIGINT NOT NULL,
    projects_id BIGINT NOT NULL,
    PRIMARY KEY (players_id, projects_id),
    CONSTRAINT fk_player_project_player FOREIGN KEY (players_id) REFERENCES player,
    CONSTRAINT fk_player_project_project FOREIGN KEY (projects_id) REFERENCES project,

);

CREATE TABLE team_player
(
    team_id    BIGINT NOT NULL,
    players_id BIGINT NOT NULL,
    PRIMARY KEY (team_id, players_id),
    CONSTRAINT fk_team_player_team FOREIGN KEY (team_id) REFERENCES team,
    CONSTRAINT fk_team_player_player FOREIGN KEY (players_id) REFERENCES player,
)
