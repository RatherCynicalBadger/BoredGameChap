INSERT INTO role VALUES
                     (DEFAULT, 'ROLE_USER'),
                     (DEFAULT, 'ROLE_TEAM_ADMIN'),
                     (DEFAULT, 'ROLE_SUPER_ADMIN')
;
# Password is 123
INSERT INTO user(id, about, enabled, password, username) VALUES
                     (DEFAULT, 'TEST', TRUE, '$2a$10$.JbzSsSrMeCClsmVp1qwKOlUz0dPOOfeclci14Yrzntbbh80cSYN6', 'Mr Test 1'),
                     (DEFAULT, 'TEST', TRUE, '$2a$10$.JbzSsSrMeCClsmVp1qwKOlUz0dPOOfeclci14Yrzntbbh80cSYN6', 'Mr Test 2')
;
INSERT INTO user_role VALUES
                          (1, 1),
                          (2, 1)
;