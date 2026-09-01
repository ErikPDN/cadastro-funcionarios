package trabalho.sd.rh;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/funcionarios";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para inicializar o banco de dados com a tabela de funcionários toda vez que o servidor for iniciado
    public static void inicializarBanco() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             InputStream in = ConexaoDB.class.getResourceAsStream("/db/schema.sql");
            ) {
            String sql = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o banco de dados: " + e.getMessage(), e);
        }
    }
}
