CREATE TABLE Users
(
    id         SERIAL PRIMARY KEY,
    user_id    INT  NOT NULL REFERENCES Posts (Id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at  TIMESTAMP DEFAULT current_timestamp,
    created_by VARCHAR(15),
    updateby   VARCHAR(15)
    CHECK ( length(content) > 2 )
);

INSERT INTO Users (user_id, content, created_by, updateby)
VALUES (1, 'This is the first user comment.', 'admin', 'admin');

CREATE TABLE Posts
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(20) NOT NULL,
    content    TEXT        NOT NULL,
    created_ad TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(20) NOT NULL,
    update_by  VARCHAR(20),
    CHECK ( length(title) > 5 )
);

INSERT INTO Posts (title, content, created_by)
VALUES ('First Post', 'This is the content of the first post.', 1);

CREATE TABLE Comments
(
    id         SERIAL PRIMARY KEY,
    post_id    INT  NOT NULL References Posts (id) ON DELETE CASCADE,
    user_id    INT  NOT NULL REFERENCES Posts (Id) ON DELETE CASCADE,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at  TIMESTAMP DEFAULT current_timestamp,
    created_by VARCHAR(15),
    update_by  VARCHAR(15)
    CHECK ( length(content) > 2 )
);

INSERT INTO Comments (post_id, user_id, content)
VALUES (1, 2, 'Great post!');
INSERT INTO Comments (post_id, user_id, content)
VALUES (2, 1, 'Thanks for sharing!');



