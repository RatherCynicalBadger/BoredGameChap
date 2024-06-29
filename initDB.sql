CREATE TABLE game
(
    id          BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    description VARCHAR(1000) NULL,
    max_players TINYINT       NOT NULL,
    min_players TINYINT       NOT NULL,
    playtime    INT           NOT NULL,
    released    INT           NULL,
    title       VARCHAR(255)  NOT NULL,
    CONSTRAINT UKb2i64cpcke4nrwphbye9noshb
        UNIQUE (title),
    CHECK (`min_players` >= 1)
);

CREATE TABLE invite
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    invited_id BIGINT       NULL,
    sent       DATETIME(6)  NULL,
    team_id    BIGINT       NULL,
    team_name  VARCHAR(255) NULL
);

CREATE TABLE role
(
    id   BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NULL
);

CREATE TABLE team
(
    id            BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    about         VARCHAR(255) NULL,
    location      VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    team_admin_id BIGINT       NULL
);

CREATE TABLE meeting
(
    id       BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    message  VARCHAR(255) NULL,
    time     DATETIME(6)  NOT NULL,
    poll_id  BIGINT       NULL,
    team_id  BIGINT       NULL,
    CONSTRAINT UKn3snqqmv6durm17hffkdxvwfe
        UNIQUE (poll_id),
    CONSTRAINT FKfpkwe6lpecgirp0hjsxupvdf
        FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    about    VARCHAR(255) NULL,
    enabled  BIT          NOT NULL,
    password VARCHAR(255) NULL,
    username VARCHAR(255) NOT NULL
);

CREATE TABLE poll
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    name1      VARCHAR(255) NULL,
    name2      VARCHAR(255) NULL,
    name3      VARCHAR(255) NULL,
    name4      VARCHAR(255) NULL,
    score1     INT          NOT NULL,
    score2     INT          NOT NULL,
    score3     INT          NOT NULL,
    score4     INT          NOT NULL,
    creator_id BIGINT       NULL,
    meeting_id BIGINT       NULL,
    CONSTRAINT UKijee3rcsj64bv80w09369g123
        UNIQUE (meeting_id),
    CONSTRAINT UKrp3c8n6ll9vi1tfdkfajcu8el
        UNIQUE (creator_id),
    CONSTRAINT FK1fb6g3krynsif7q1f76wi2ec4
        FOREIGN KEY (creator_id) REFERENCES user (id),
    CONSTRAINT FKf7x3p2yxrtxxi4vuf03paks1k
        FOREIGN KEY (meeting_id) REFERENCES meeting (id)
);

ALTER TABLE meeting
    ADD CONSTRAINT FKnpq1owocwbn0d6ped4i9papo0
        FOREIGN KEY (poll_id) REFERENCES poll (id);

CREATE TABLE user_game
(
    user_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    CONSTRAINT FK119tttdkgsb3r72i6l557a6f5
        FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT FKg1pwaakahpjiu1io84bnnthys
        FOREIGN KEY (game_id) REFERENCES game (id)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK859n2jvi8ivhui0rl0esws6o
        FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT FKa68196081fvovjhkek5m97n3y
        FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE user_team
(
    user_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    CONSTRAINT FK6d6agqknw564xtsa91d3259wu
        FOREIGN KEY (team_id) REFERENCES team (id),
    CONSTRAINT FKd6um0sk8hyytfq7oalt5a4nph
        FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO role VALUES
                     (DEFAULT, 'ROLE_USER'),
                     (DEFAULT, 'ROLE_TEAM_ADMIN'),
                     (DEFAULT, 'ROLE_SUPER_ADMIN');