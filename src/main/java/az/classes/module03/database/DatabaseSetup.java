package az.classes.module03.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseSetup {

    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users ("
            + "id SERIAL PRIMARY KEY, "
            + "name VARCHAR(15) UNIQUE NOT NULL CHECK (char_length(name) >= 3), "
            + "surname VARCHAR(15) NOT NULL CHECK (char_length(surname) >= 3), "
            + "gender VARCHAR(6) NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')), "
            + "age INT CHECK (age > 0), "
            + "email VARCHAR(30) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'), "
            + "status VARCHAR(8) DEFAULT 'active' CHECK (status IN ('active', 'inactive', 'banned')), "
            + "created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP, "
            + "created_by BIGINT REFERENCES users(id) ON DELETE CASCADE, "
            + "updated_by BIGINT REFERENCES users(id) ON DELETE CASCADE"
            + ");";
    
    private static final String CREATE_POSTS_TABLE = "CREATE TABLE IF NOT EXISTS posts ("
            + "post_id SERIAL PRIMARY KEY, "
            + "user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE, "
            + "title VARCHAR(100) NOT NULL CHECK (char_length(title) >= 3), "
            + "content TEXT NOT NULL, "
            + "is_published BOOLEAN DEFAULT FALSE, "
            + "published_at TIMESTAMPTZ(3), "
            + "created_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "created_by BIGINT REFERENCES users(id) ON DELETE CASCADE, "
            + "updated_by BIGINT REFERENCES users(id) ON DELETE CASCADE"
            + ");";

    private static final String CREATE_COMMENTS_TABLE = "CREATE TABLE IF NOT EXISTS comments ("
            + "comment_id SERIAL PRIMARY KEY, "
            + "post_id BIGINT NOT NULL REFERENCES posts(post_id) ON DELETE CASCADE, "
            + "user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE, "
            + "content TEXT NOT NULL CHECK (char_length(content) >= 1 AND char_length(content) <= 500), "
            + "is_comment_edited BOOLEAN DEFAULT FALSE, "
            + "edit_reason VARCHAR(255), "
            + "is_comment_deleted BOOLEAN DEFAULT FALSE, "
            + "created_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "created_by BIGINT REFERENCES users(id) ON DELETE CASCADE, "
            + "updated_by BIGINT REFERENCES users(id) ON DELETE CASCADE"
            + ");";

    private static final String CREATE_LIKES_TABLE = "CREATE TABLE IF NOT EXISTS likes ("
            + "like_id SERIAL PRIMARY KEY, "
            + "target_id BIGINT NOT NULL, "
            + "target_type VARCHAR(10) NOT NULL CHECK (target_type IN ('post', 'comment')), "
            + "user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE, "
            + "like_type VARCHAR(7) NOT NULL CHECK (like_type IN ('like', 'dislike')), "
            + "created_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "updated_at TIMESTAMPTZ(3) DEFAULT CURRENT_TIMESTAMP, "
            + "created_by BIGINT REFERENCES users(id) ON DELETE CASCADE, "
            + "updated_by BIGINT REFERENCES users(id) ON DELETE CASCADE"
            + ");";

    public static void setupDatabase() {
        try (Connection connection = ConnectionManager.getConnection()) {
            executeUpdate(connection, CREATE_USERS_TABLE);
            executeUpdate(connection, CREATE_POSTS_TABLE);
            executeUpdate(connection, CREATE_COMMENTS_TABLE);
            executeUpdate(connection, CREATE_LIKES_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeUpdate(Connection connection, String sql) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }

}
