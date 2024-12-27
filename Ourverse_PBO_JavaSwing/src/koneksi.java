import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    // set konstanta untuk pengaturan driver,db,user dan password
    private final String JDBC_URL = "com.mysql.cj.jdbc.Driver";
    private final String DB_NAME = "ourverse";
    private final String DB_URL = "jdbc:mysql://localhost/" + DB_NAME;
    private final String USER = "root";
    private final String PASS = "";
    // inisialisasi objek conn
    Connection conn;

    public Connection connect() throws ClassNotFoundException,
            SQLException {
        // isi driver sesuai URL yang di definisikan
        Class.forName(JDBC_URL);
        // isi objek conn untuk mendapatkan koneksi dengan database
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        System.out.println("koneksi sukses");
        return conn;
    }
}