package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SQLHelper {

    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    public static void clearDB() {
        String[] queries = {
                "DELETE FROM credit_request_entity;",
                "DELETE FROM order_entity;",
                "DELETE FROM payment_entity;"
        };

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            QueryRunner runner = new QueryRunner();
            for (String query : queries) {
                runner.update(conn, query);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception in clearDB: " + e.getMessage());
        }
    }

    public static String getLatestPaymentStatus() {
        return getData("SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;");
    }

    public static String getLatestCreditStatus() {
        return getData("SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1 ;");
    }


    private static String getData(String query) {
        return String.valueOf(getSingleResult(query));
    }

    private static Object getSingleResult(String query) {
        QueryRunner runner = new QueryRunner();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            return runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}