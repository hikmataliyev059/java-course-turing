CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    user_name    VARCHAR(15) UNIQUE NOT NULL CHECK (char_length(user_name) >= 3),
    user_surname VARCHAR(15)        NOT NULL CHECK (char_length(user_surname) >= 3),
    gender       CHAR(6)            NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')),
    age          INT CHECK (age > 0),
    email        VARCHAR(30) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    is_active    BOOLEAN     DEFAULT TRUE,
    created_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by   BIGINT             NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    updated_by   BIGINT             NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO users (user_name, user_surname, gender, age, email, is_active, created_by, updated_by)
VALUES ('Elvin', 'Huseynov', 'Male', 25, 'elvin.huseynov@example.com', TRUE, 1, 1),
       ('Aysel', 'Mammadova', 'Female', 30, 'aysel.mammedova@example.com', TRUE, 1, 1),
       ('Ramin', 'Quliyev', 'Male', 22, 'ramin.quliyev@example.com', TRUE, 1, 1),
       ('Nigar', 'Aliyeva', 'Female', 28, 'nigar.aliyeva@example.com', TRUE, 1, 1),
       ('Farid', 'Rzayev', 'Male', 35, 'farid.rzayev@example.com', TRUE, 1, 1);

CREATE TABLE IF NOT EXISTS posts
(
    post_id      SERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    title        VARCHAR(100) NOT NULL CHECK (char_length(title) >= 3),
    content      TEXT         NOT NULL,
    is_published BOOLEAN        DEFAULT FALSE,
    published_at TIMESTAMPTZ(3),
    created_at   TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    created_by   BIGINT       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    updated_by   BIGINT       NOT NULL REFERENCES users (id) ON DELETE CASCADE
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
    created_by         BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    updated_by         BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE
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
    like_id    SERIAL PRIMARY KEY,
    post_id    BIGINT  NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE,
    comment_id BIGINT REFERENCES comments (comment_id) ON DELETE CASCADE,
    user_id    BIGINT  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    like_type  CHAR(7) NOT NULL CHECK (like_type IN ('like', 'dislike')),
    created_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    updated_by BIGINT  NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO likes (post_id, user_id, like_type, created_by, updated_by)
VALUES (1, 1, 'like', 1, 1),
       (1, 2, 'like', 2, 2),
       (2, 3, 'dislike', 3, 3),
       (2, 2, 'like', 2, 2),
       (3, 1, 'like', 1, 1),
       (3, 3, 'dislike', 3, 3);




