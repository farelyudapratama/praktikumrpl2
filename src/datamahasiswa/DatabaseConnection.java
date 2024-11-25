package datamahasiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/datamahasiswa";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

//            JOptionPane.showMessageDialog(null, "Koneksi berhasil", "Database Connection", JOptionPane.INFORMATION_MESSAGE);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
