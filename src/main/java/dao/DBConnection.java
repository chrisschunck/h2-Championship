package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simple H2-backed DB connection singleton for this small app.
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    // file-based H2 DB in project folder (data/campeonato.mv.db)
    private final String DB_URL = "jdbc:h2:./data/campeonato;AUTO_SERVER=TRUE";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "";

    private DBConnection() {
        try {
            // ensure driver is available
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            // Give a more helpful error so the user knows how to fix missing driver on classpath
            String msg = "H2 JDBC Driver not found. Make sure the H2 driver is on the application's classpath.\n" +
                    "If you are using Maven, run 'mvn clean package' or reimport the project so Maven downloads dependencies.\n" +
                    "Maven dependency (pom.xml) entry:\n" +
                    "<dependency>\n" +
                    "  <groupId>com.h2database</groupId>\n" +
                    "  <artifactId>h2</artifactId>\n" +
                    "  <version>2.2.220</version>\n" +
                    "</dependency>\n" +
                    "Or add the H2 jar to your runtime classpath (download from https://h2database.com).";
            throw new RuntimeException(msg, e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to H2 database: " + e.getMessage(), e);
        }
    }

    private void initializeDatabase() throws SQLException {
        String createCampeonato = "CREATE TABLE IF NOT EXISTS campeonato (" +
                "id IDENTITY PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL" +
                ")";

        String createPartida = "CREATE TABLE IF NOT EXISTS partida (" +
                "id IDENTITY PRIMARY KEY," +
                "time_a VARCHAR(50) NOT NULL," +
                "time_b VARCHAR(50) NOT NULL," +
                "gols_time_a INT NOT NULL," +
                "gols_time_b INT NOT NULL," +
                "resultado VARCHAR(50)," +
                "cartoes_amarelos INT," +
                "cartoes_vermelhos INT," +
                "escanteios INT," +
                "quantidade_penalti INT," +
                "penalti_convertido BOOLEAN" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createCampeonato);
            stmt.execute(createPartida);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() { return connection; }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException ignored) {}
        instance = null;
    }
}