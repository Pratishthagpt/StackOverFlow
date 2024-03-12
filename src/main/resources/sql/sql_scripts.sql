USE stack_over_flow;

insert into general_user (full_name, username, email, is_admin, is_member, is_moderator, account_status, reputation, role )
values
    ('Ben Smith', 'Ben', 'ben@yohoo.com', 0, 1, 0, 'ACTIVE', 0, 1),
    ('Jona Yash', 'Yash', 'jona1@yohoo.com', 1, 1, 1, 'ACTIVE', 50, 3),
    ('Sundal Lal', 'Sundar', 'sundar@yohoo.com', 0, 1, 1, 'ACTIVE', 30, 2),
    ('Jetha Lal', 'Jetha', 'tapukpapa@yohoo.com', 1, 1, 1, 'ACTIVE', 100, 3),
    ('Daya Ben', 'Daya', 'bendaya@yohoo.com', 0, 1, 0, 'ACTIVE', 70, 1);

SHOW CREATE TABLE general_user;

ALTER TABLE general_user AUTO_INCREMENT=1;

insert into bounty (expiration_date, reputation)
values
    ('2025-05-12 08:00:00', 2),
    ('2024-06-12 08:00:00', 4),
    ('2027-07-12 08:00:00', 6),
    ('2026-08-12 08:00:00', 8),
    ('2028-09-12 08:00:00', 10);

INSERT INTO question (created_at, updated_at, created_by, members_who_downvoted, members_who_upvoted, no_of_member_who_reported, text, ques_status, title, bounty_id, created_by_member_id)
VALUES
    ('2024-03-12 08:00:00', '2024-03-12 08:30:00', 'Daya', NULL, '2,3,4', 0, 'This is the first question text.', 'OPEN', 'First Question', NULL, 5),
    ('2024-03-13 09:00:00', '2024-03-13 09:15:00', 'Jetha', '1,2', 'Yash,Daya', 1, 'This is the second question text.', 'REPORTED', 'Second Question', 2, 4),
    ('2024-03-14 10:00:00', '2024-03-14 10:20:00', 'Ben', NULL, '2,3,4,5', 0, 'This is the third question text.', 'CLOSED', 'Third Question', 4, 1),
    ('2024-03-15 11:00:00', '2024-03-15 11:45:00', 'Sundar', '1,2,4,5', NULL, 0, 'This is the fourth question text.', 'OPEN', 'Fourth Question', 5, 3),
    ('2024-03-16 12:00:00', '2024-03-16 12:25:00', 'Yash', NULL, NULL, 0, 'This is the fifth question text.', 'OPEN', 'Fifth Question', 1, 2);

INSERT INTO answer (members_who_downvoted, members_who_upvoted, no_of_member_who_reported, text, ans_status, is_satisfied_solution, answers, created_by_member_id)
VALUES
    (NULL, NULL, 0, 'This is the first answer to the question.', 'OPEN', 0, NULL, 1),
    (NULL, NULL, 2, 'Another answer to the question.', 'OPEN', 1, NULL, 2),
    (NULL, NULL, 1, 'Yet another answer to the question.', 'OPEN', 0, NULL, 3),
    (NULL, NULL, 0, 'This answer is reported.', 'REPORTED', 0, NULL, 4),
    (NULL, NULL, 0, 'This answer is closed.', 'CLOSED', 1, NULL, 5),
    (NULL, NULL, 0, 'This answer is same as before.', 'OPEN', 0, NULL, 4),
    (NULL, NULL, 0, 'This answer is correct.', 'CLOSED', 1, NULL, 5);

INSERT INTO comment (members_who_downvoted, members_who_upvoted, no_of_member_who_reported, text, comment_status, answer_id, question_id, question, answer, created_by_member_id)
VALUES
    (NULL, NULL, 0, 'This is a comment on the first question.', 'OPEN', NULL, 1, 1, NULL, 1),
    (NULL, NULL, 1, 'A comment on the second question.', 'OPEN', 2, 2, 2, NULL, 2),
    (NULL, NULL, 3, 'Another comment on the first answer.', 'OPEN', 1, NULL, NULL, 1, 3),
    (NULL, NULL, 0, 'A comment on the second answer.', 'OPEN', 2, 3, NULL, 2, 4),
    (NULL, NULL, 2, 'This comment is reported.', 'REPORTED', NULL, 1, 1, NULL, 5),
    (NULL, NULL, 0, 'This comment is closed.', 'CLOSED', NULL, 4, 2, NULL, 2);

insert into photo (url, user_id) values
                                     ('url1', 1),
                                     ('url2', 4),
                                     ('url3', 5),
                                     ('url4', 2),
                                     ('url5', 3),
                                     ('url6', 1),
                                     ('url7', 2),
                                     ('url8', 4),
                                     ('url9', 5);

insert into answer_photos values
                              (1, 2),
                              (1, 4),
                              (2, 3),
                              (2, 1),
                              (3, 7),
                              (3, 8),
                              (4, 1),
                              (4, 6),
                              (5, 9),
                              (5, 2),
                              (5, 5);

insert into comment_photos values
                               (5, 2),
                               (1, 4),
                               (4, 3),
                               (2, 1),
                               (3, 7),
                               (3, 8),
                               (4, 1);

insert into question_photos values
                                (1, 2),
                                (5, 4),
                                (2, 3),
                                (2, 1),
                                (4, 1),
                                (4, 6),
                                (3, 9),
                                (5, 4),
                                (5, 7);

insert into question_answers values
                                 (1, 1),(2, 2),(3, 3),(4, 4),(5, 5);

insert into tag (text) values ('tag1'),('tag2'),('tag3'),('tag4'),('tag5');

insert into question_tags values
                              (1, 2),(2, 3),(3, 5),(4, 1),(5, 2);


