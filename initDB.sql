INSERT INTO role VALUES
                     (DEFAULT, 'ROLE_USER'),
                     (DEFAULT, 'ROLE_TEAM_ADMIN'),
                     (DEFAULT, 'ROLE_SUPER_ADMIN')
;
INSERT INTO user VALUES
                     (DEFAULT, 'TEST', '$2a$10$.JbzSsSrMeCClsmVp1qwKOlUz0dPOOfeclci14Yrzntbbh80cSYN6', 'Mr Test 1', TRUE),
                     (DEFAULT, 'TEST', '$2a$10$.JbzSsSrMeCClsmVp1qwKOlUz0dPOOfeclci14Yrzntbbh80cSYN6', 'Mr Test 2', TRUE)
;
INSERT INTO user_role VALUES
                          (1, 1),
                          (2, 1)
;