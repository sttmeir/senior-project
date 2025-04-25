INSERT INTO users (email, password, username, full_name, role)
VALUES
    ('admin@example.com', 'adminpass', 'admin', 'Админ Админович', 'ADMIN'),
    ('manager1@example.com', 'managerpass1', 'manager1', 'Менеджер Один', 'MANAGER'),
    ('manager2@example.com', 'managerpass2', 'manager2', 'Менеджер Два', 'MANAGER'),
    ('user1@example.com', 'userpass1', 'user1', 'Сотрудник Один', 'USER'),
    ('user2@example.com', 'userpass2', 'user2', 'Сотрудник Два', 'USER'),
    ('user3@example.com', 'userpass3', 'user3', 'Сотрудник Три', 'USER');

-- Вставка отчетов
INSERT INTO reports (user_id, week_start, accomplishments, challenges, plans)
VALUES
    (4, '2024-04-01', 'Завершён проект X', 'Недостаток времени', 'Начать проект Y'),
    (4, '2024-04-08', 'Разработка модуля A', 'Баги в коде', 'Починить баги и написать тесты'),
    (5, '2024-04-01', 'Изучил Hibernate', 'Сложности с аннотациями', 'Писать больше практики'),
    (5, '2024-04-08', 'Внедрил авторизацию', 'Проблемы с JWT', 'Докрутить безопасность'),
    (6, '2024-04-01', 'Создана база данных', 'Проблемы с связями', 'Тестировать интеграции');

-- Вставка отзывов
INSERT INTO feedback (report_id, manager_id, comment)
VALUES
    (1, 2, 'Отличная работа! Продолжай в том же духе.'),
    (2, 2, 'Нужно больше проработки планов.'),
    (3, 3, 'Хороший прогресс.'),
    (4, 3, 'Пожалуйста, напиши подробнее о проблемах.'),
    (5, 2, 'Следующий раз оформляй подробнее.');

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