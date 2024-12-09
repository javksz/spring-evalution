insert into users ( email, hashed_password, role)
values ('javkhlanbayar11@gmail.com',  '$2a$10$jfyiYQBzFZUuKDww1PSg3.iZqlBNl8NNQSsCUhmEa0L8n5INYeiCe', 'ADMIN'),
         ('manuel@rabl.com' ,'$2a$10$dOCoqTydQm2UxPwBLvjyoOJ7dAn73wLlSw6KQ0Abd4POYwjFEDdXq', 'USER');




INSERT INTO questions ( short_description, long_description, expiration_date)
VALUES
    ( 'Teamwork', 'How would you rate the teams collaboration?', '2024-11-01'),
    ('Work Environment', 'Is the work environment comfortable for you?', '2024-12-31'),
( 'Management Support', 'Do you feel supported by your management?', '2024-12-31'),
( 'Career Growth', 'Are there enough opportunities for career growth?', '2024-12-31'),
( 'Training', 'Are the training programs effective?', '2023-12-31');


INSERT INTO question_answers (user_id, question_id, answer)
VALUES
    ( 2, 1, 'COMPLETELY_AGREE'),
    ( 1, 1, 'PARTIAL_AGREE'),
    ( 2, 5, 'NOT_AGREE');
