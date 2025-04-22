
DROP TABLE IF EXISTS feedback CASCADE;
DROP TABLE IF EXISTS reports CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255),
                       username VARCHAR(255) UNIQUE,
                       full_name VARCHAR(255),
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP
    -- Поле authorities пропущено (исключено)
);

-- Таблица еженедельных отчётов
CREATE TABLE reports (
                         report_id SERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         week_start DATE,
                         accomplishments TEXT,
                         challenges TEXT,
                         plans TEXT,
                         submitted_at TIMESTAMP,

                         CONSTRAINT fk_report_user FOREIGN KEY (user_id)
                             REFERENCES users(user_id)
                             ON DELETE CASCADE
);

CREATE TABLE feedback (
                          feedback_id BIGSERIAL PRIMARY KEY,
                          report_id BIGINT NOT NULL,
                          manager_id BIGINT NOT NULL,
                          comment TEXT,
                          created_at TIMESTAMP,

                          CONSTRAINT fk_feedback_report FOREIGN KEY (report_id)
                              REFERENCES reports(report_id)
                              ON DELETE CASCADE,

                          CONSTRAINT fk_feedback_manager FOREIGN KEY (manager_id)
                              REFERENCES users(user_id)
                              ON DELETE CASCADE
);