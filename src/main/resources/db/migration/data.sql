-- Вставка администратора
INSERT INTO users (email, password, username, full_name, role, created_at, supervisor_id)
VALUES
    ('admin@domain.com', 'password123', 'admin', 'Admin User', 'ADMIN', '2025-04-25T14:00:00', NULL);

-- Вставка менеджера
INSERT INTO users (email, password, username, full_name, role, created_at, supervisor_id)
VALUES
    ('manager@domain.com', 'password123', 'manager', 'Manager User', 'MANAGER', '2025-04-25T14:00:00', 1); -- Подчиняется администратору

-- Вставка пользователей, подчиняющихся менеджеру
INSERT INTO users (email, password, username, full_name, role, created_at, supervisor_id)
VALUES
    ('user1@domain.com', 'password123', 'user1', 'User One', 'USER', '2025-04-25T14:00:00', 2), -- Подчиняется менеджеру
    ('user2@domain.com', 'password123', 'user2', 'User Two', 'USER', '2025-04-25T14:00:00', 2), -- Подчиняется менеджеру
    ('user3@domain.com', 'password123', 'user3', 'User Three', 'USER', '2025-04-25T14:00:00', 2), -- Подчиняется менеджеру
    ('user4@domain.com', 'password123', 'user4', 'User Four', 'USER', '2025-04-25T14:00:00', 2), -- Подчиняется менеджеру
    ('user5@domain.com', 'password123', 'user5', 'User Five', 'USER', '2025-04-25T14:00:00', 2); -- Подчиняется менеджеру

-- Вставка отчета 1 (админ)
INSERT INTO reports (user_id, week_start, accomplishments, challenges, plans, productivity_rating, submitted_at)
VALUES
    (3, '2025-04-01', 'Completed administrative tasks for the month', 'Difficulty in managing multiple priorities', 'Delegate some tasks to team members', 4, '2025-04-25T14:05:00');

-- Вставка улучшений для отчета 1
INSERT INTO report_improvement_areas (report_id, area)
VALUES
    (1, 'Task Delegation'),
    (1, 'Time Management');

-- Вставка отчета 2 (менеджер)
INSERT INTO reports (user_id, week_start, accomplishments, challenges, plans, productivity_rating, submitted_at)
VALUES
    (3, '2025-04-01', 'Led the team through the initial phase of the project', 'Faced resource shortages', 'Coordinate with HR for additional resources', 5, '2025-04-25T14:10:00');

-- Вставка улучшений для отчета 2
INSERT INTO report_improvement_areas (report_id, area)
VALUES
    (2, 'Resource Management'),
    (2, 'Team Coordination');

-- Вставка отчета 3 (менеджер)
INSERT INTO reports (user_id, week_start, accomplishments, challenges, plans, productivity_rating, submitted_at)
VALUES
    (4, '2025-04-01', 'Conducted performance reviews for the team', 'Challenges in providing constructive feedback', 'Attend training on performance management', 4, '2025-04-25T14:15:00');

-- Вставка улучшений для отчета 3
INSERT INTO report_improvement_areas (report_id, area)
VALUES
    (3, 'Feedback Delivery'),
    (3, 'Performance Management');

-- Вставка отчета 4 (менеджер)
INSERT INTO reports (user_id, week_start, accomplishments, challenges, plans, productivity_rating, submitted_at)
VALUES
    (5, '2025-04-01', 'Coordinated cross-departmental meetings', 'Issues with inter-department communication', 'Establish clearer communication channels between departments', 3, '2025-04-25T14:20:00');

-- Вставка улучшений для отчета 4
INSERT INTO report_improvement_areas (report_id, area)
VALUES
    (4, 'Inter-department Communication'),
    (4, 'Meeting Effectiveness');

-- Вставка цели для Админа (user_id = 1)
INSERT INTO goals (title, type, progress, user_id)
VALUES
    ('Increase website traffic by 20%', 'KPI', 50.0, 3),
    ('Improve team communication skills', 'COMPETENCY', 30.0, 3);

-- Вставка цели для Менеджера (user_id = 2)
INSERT INTO goals (title, type, progress, user_id)
VALUES
    ('Lead team to complete project phase 1', 'KPI', 80.0, 4),
    ('Enhance leadership skills through training', 'COMPETENCY', 40.0, 4);

-- Вставка цели для обычного юзера (user_id = 3)
INSERT INTO goals (title, type, progress, user_id)
VALUES
    ('Complete training for software development', 'KPI', 60.0, 5),
    ('Build confidence in team meetings', 'COMPETENCY', 25.0, 5);

-- Вставка цели для другого обычного юзера (user_id = 4)
INSERT INTO goals (title, type, progress, user_id)
VALUES
    ('Submit 5 proposals by the end of the month', 'KPI', 20.0, 6),
    ('Improve presentation skills for client meetings', 'COMPETENCY', 15.0, 6);

-- Вставка отзыва для первого отчёта (report_id = 1), менеджер (manager_id = 2)
INSERT INTO feedback (report_id, manager_id, comment, created_at)
VALUES
    (1, 2, 'Great progress, but needs improvement in time management.', '2025-04-25T14:00:00');

-- Вставка отзыва для второго отчёта (report_id = 2), менеджер (manager_id = 2)
INSERT INTO feedback (report_id, manager_id, comment, created_at)
VALUES
    (2, 2, 'Good achievements, but there are challenges with team coordination.', '2025-04-25T14:30:00');

-- Вставка отзыва для третьего отчёта (report_id = 3), менеджер (manager_id = 2)
INSERT INTO feedback (report_id, manager_id, comment, created_at)
VALUES
    (3, 2, 'The goal progress is on track, but needs further efforts on documentation.', '2025-04-25T15:00:00');


-- Вставка данных для первого пользователя (user_id = 1), отчётный период "REGULAR"
INSERT INTO check_ins (due_date, submitted_date, status, type, reviewer_name, user_id)
VALUES
    ('2025-05-01', '2025-04-28', 'SUBMITTED', 'REGULAR', 'John Doe', 3);

-- Вставка данных для второго пользователя (user_id = 2), отчётный период "QUARTER"
INSERT INTO check_ins (due_date, submitted_date, status, type, reviewer_name, user_id)
VALUES
    ('2025-06-01', '2025-05-30', 'NOT_SUBMITTED', 'QUARTER', 'Jane Smith', 4);

-- Вставка данных для третьего пользователя (user_id = 3), отчётный период "ANNUAL"
INSERT INTO check_ins (due_date, submitted_date, status, type, reviewer_name, user_id)
VALUES
    ('2025-12-31', '2025-12-30', 'SUBMITTED', 'ANNUAL', 'Alice Johnson', 5);

-- Вставка данных для четвёртого пользователя (user_id = 4), отчётный период "REGULAR"
INSERT INTO check_ins (due_date, submitted_date, status, type, reviewer_name, user_id)
VALUES
    ('2025-05-15', '2025-05-14', 'SUBMITTED', 'REGULAR', 'Robert Brown', 6);

-- Вставка данных для пятого пользователя (user_id = 5), отчётный период "QUARTER"
INSERT INTO check_ins (due_date, submitted_date, status, type, reviewer_name, user_id)
VALUES
    ('2025-07-01', '2025-06-30', 'NOT_SUBMITTED', 'QUARTER', 'Emily White', 7);