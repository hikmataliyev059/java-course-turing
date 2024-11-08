CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(15) UNIQUE NOT NULL CHECK (char_length(name) >= 3),
    surname    VARCHAR(15)        NOT NULL CHECK (char_length(surname) >= 3),
    gender     VARCHAR(6)         NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')),
    age        INT CHECK (age > 0),
    email      VARCHAR(30) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    status     VARCHAR(8) DEFAULT 'active' CHECK (status IN ('active', 'inactive', 'banned')),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT REFERENCES users (id) ON DELETE CASCADE,
    updated_by BIGINT REFERENCES users (id) ON DELETE CASCADE
);
z
INSERT INTO users (name, surname, gender, age, email, status, created_by, updated_by)
VALUES ('Elvin', 'Huseynov', 'Male', 25, 'elvin.huseynov@example.com', 'active', NULL, NULL),
       ('Aysel', 'Mammadova', 'Female', 30, 'aysel.mammedova@example.com', 'active', 1, 1),
       ('Ramin', 'Quliyev', 'Male', 22, 'ramin.quliyev@example.com', 'active', 1, 1),
       ('Nigar', 'Aliyeva', 'Female', 28, 'nigar.aliyeva@example.com', 'active', 1, 1),
       ('Farid', 'Rzayev', 'Male', 35, 'farid.rzayev@example.com', 'active', 1, 1);

CREATE TABLE IF NOT EXISTS posts
(
    post_id      SERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    title        VARCHAR(100) NOT NULL CHECK (char_length(title) >= 3),
    content      TEXT         NOT NULL,
    is_published BOOLEAN      DEFAULT FALSE,
    published_at TIMESTAMPTZ(3),
    created_at   TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    created_by   BIGINT       REFERENCES users (id) ON DELETE CASCADE,
    updated_by   BIGINT       REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO posts (user_id, title, content, is_published, created_by, updated_by)
VALUES (1, 'Real Madrid: History of Success',
        'Real Madrid is one of the most successful football clubs in the world, having won the Champions League 15 times throughout its history.',
        TRUE, 1, 1),
       (2, 'Real Madrid and the Classics',
        'Real Madrid is known for its classics against Barcelona. These matches always generate great interest.', TRUE,
        2, 2),
       (3, 'Real Madrid’s Young Talents',
        'In recent years, Real Madrid has focused on developing young players, which is important for the future of the club.',
        TRUE, 3, 3);

CREATE TABLE IF NOT EXISTS comments
(
    comment_id         SERIAL PRIMARY KEY,
    post_id            BIGINT NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE,
    user_id            BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    content            TEXT   NOT NULL CHECK (char_length(content) >= 1 AND char_length(content) <= 500),
    is_comment_edited  BOOLEAN        DEFAULT FALSE,
    edit_reason        VARCHAR(255),
    is_comment_deleted BOOLEAN        DEFAULT FALSE,
    created_at         TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT REFERENCES users (id) ON DELETE CASCADE,
    updated_by         BIGINT REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO comments (post_id, user_id, content, created_by, updated_by)
VALUES (1, 1, 'The history of Real Madrid is very interesting to me!', 1, 1),
       (1, 2, 'My love for this team is endless!', 2, 2),
       (2, 3, 'Classics are always exciting; I love the rivalry with Barcelona.', 3, 3),
       (3, 1, 'Young talents are the stars of the future!', 1, 1),
       (2, 2, 'Real Madrid’s playing style has always been to my liking.', 2, 2),
       (3, 3, 'This is part of the club’s long-term plans.', 3, 3);

CREATE TABLE IF NOT EXISTS likes
(
    like_id     SERIAL PRIMARY KEY,
    target_id   BIGINT  NOT NULL,
    target_type VARCHAR(10) NOT NULL CHECK (target_type IN ('post', 'comment')),
    user_id     BIGINT  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    like_type   VARCHAR(7) NOT NULL CHECK (like_type IN ('like', 'dislike')),
    created_at  TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT REFERENCES users (id) ON DELETE CASCADE,
    updated_by  BIGINT REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO likes (target_id, target_type, user_id, like_type, created_by, updated_by)
VALUES (1, 'post', 1, 'like', 1, 1),
       (1, 'post', 2, 'like', 2, 2),
       (2, 'post', 3, 'dislike', 3, 3),
       (2, 'post', 2, 'like', 2, 2),
       (3, 'post', 1, 'like', 1, 1),
       (3, 'post', 3, 'dislike', 3, 3);
