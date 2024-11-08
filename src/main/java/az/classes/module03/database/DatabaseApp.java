package az.classes.module03.database;

public class DatabaseApp {

    public static void main(String[] args) {

        DatabaseSetup.setupDatabase();
        DataSeeder.seedData();
    }

}
