CREATE TABLE users (
                       user_id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255),
                       role VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reports (
                         report_id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL REFERENCES users(user_id),
                         week_start DATE NOT NULL,
                         accomplishments TEXT,
                         challenges TEXT,
                         plans TEXT,
                         submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE feedback (
                          feedback_id BIGSERIAL PRIMARY KEY,
                          report_id BIGINT NOT NULL REFERENCES reports(report_id),
                          manager_id BIGINT NOT NULL REFERENCES users(user_id),
                          comment TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

