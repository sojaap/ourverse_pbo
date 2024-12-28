import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/ourverse"; // Ganti dengan URL database Anda
    private static final String USERNAME = "root"; // Ganti dengan username database Anda
    private static final String PASSWORD = "password"; // Ganti dengan password database Anda

    static {
        try {
            // Muat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC tidak ditemukan: " + e.getMessage());
        }
    }

    // Metode connect untuk mendapatkan koneksi
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Metode main untuk pengujian koneksi
    public static void main(String[] args) {
        koneksi dbKoneksi = new koneksi();
        try (Connection conn = dbKoneksi.connect()) {
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }
}
