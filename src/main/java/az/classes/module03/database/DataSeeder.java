package az.classes.module03.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Types.BIGINT;

public class DataSeeder {

    public static void seedData() {
        try (Connection connection = ConnectionManager.getConnection()) {
            String insertUserSQL = "INSERT INTO users (name, surname, gender, age, email, status, created_by, updated_by) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertUserSQL)) {
                stmt.setString(1, "Elvin");
                stmt.setString(2, "Huseynov");
                stmt.setString(3, "Male");
                stmt.setInt(4, 25);
                stmt.setString(5, "elvin.huseynov@example.com");
                stmt.setString(6, "active");
                stmt.setNull(7, BIGINT);
                stmt.setNull(8, BIGINT);
                stmt.executeUpdate();
            }

            String insertPostsSQL = "INSERT INTO posts (user_id, title, content, is_published, created_by, updated_by) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertPostsSQL)) {
                stmt.setInt(1, 1);
                stmt.setString(2, "Real Madrid: History of Success");
                stmt.setString(3, "Real Madrid is one of the most successful football clubs in the world, having won the Champions League 15 times throughout its history.");
                stmt.setBoolean(4, true);
                stmt.setInt(5, 1);
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "Real Madrid and the Classics");
                stmt.setString(3, "Real Madrid is known for its classics against Barcelona. These matches always generate great interest.");
                stmt.setBoolean(4, true);
                stmt.setInt(5, 2);
                stmt.setInt(6, 2);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "Real Madrid’s Young Talents");
                stmt.setString(3, "In recent years, Real Madrid has focused on developing young players, which is important for the future of the club.");
                stmt.setBoolean(4, true);
                stmt.setInt(5, 3);
                stmt.setInt(6, 3);
                stmt.executeUpdate();
            }

            String insertCommentsSQL = "INSERT INTO comments (post_id, user_id, content, created_by, updated_by) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertCommentsSQL)) {
                stmt.setInt(1, 1);
                stmt.setInt(2, 1);
                stmt.setString(3, "The history of Real Madrid is very interesting to me!");
                stmt.setInt(4, 1);
                stmt.setInt(5, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 1);
                stmt.setInt(2, 2);
                stmt.setString(3, "My love for this team is endless!");
                stmt.setInt(4, 2);
                stmt.setInt(5, 2);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setInt(2, 3);
                stmt.setString(3, "Classics are always exciting; I love the rivalry with Barcelona.");
                stmt.setInt(4, 3);
                stmt.setInt(5, 3);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setInt(2, 1);
                stmt.setString(3, "Young talents are the stars of the future!");
                stmt.setInt(4, 1);
                stmt.setInt(5, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setInt(2, 2);
                stmt.setString(3, "Real Madrid’s playing style has always been to my liking.");
                stmt.setInt(4, 2);
                stmt.setInt(5, 2);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setInt(2, 3);
                stmt.setString(3, "This is part of the club’s long-term plans.");
                stmt.setInt(4, 3);
                stmt.setInt(5, 3);
                stmt.executeUpdate();
            }

            String insertLikesSQL = "INSERT INTO likes (target_id, target_type, user_id, like_type, created_by, updated_by) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertLikesSQL)) {
                stmt.setInt(1, 1);
                stmt.setString(2, "post");
                stmt.setInt(3, 1);
                stmt.setString(4, "like");
                stmt.setInt(5, 1);
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 1);
                stmt.setString(2, "post");
                stmt.setInt(3, 2);
                stmt.setString(4, "like");
                stmt.setInt(5, 2);
                stmt.setInt(6, 2);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "post");
                stmt.setInt(3, 3);
                stmt.setString(4, "dislike");
                stmt.setInt(5, 3);
                stmt.setInt(6, 3);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "post");
                stmt.setInt(3, 2);
                stmt.setString(4, "like");
                stmt.setInt(5, 2);
                stmt.setInt(6, 2);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "post");
                stmt.setInt(3, 1);
                stmt.setString(4, "like");
                stmt.setInt(5, 1);
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "post");
                stmt.setInt(3, 3);
                stmt.setString(4, "dislike");
                stmt.setInt(5, 3);
                stmt.setInt(6, 3);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

