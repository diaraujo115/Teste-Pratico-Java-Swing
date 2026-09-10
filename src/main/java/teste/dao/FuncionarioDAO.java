package teste.dao;

import teste.db.ConnectionFactory;
import teste.model.Funcionario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void cadastrar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, data_admissao, salario, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, Date.valueOf(funcionario.getDataAdmissao()));
            stmt.setBigDecimal(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isStatus());

            stmt.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM funcionarios ORDER BY id";
        List<Funcionario> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDate("data_admissao").toLocalDate(),
                    rs.getBigDecimal("salario"),
                    rs.getBoolean("status")
                );
                lista.add(f);
            }
        }

        return lista;
    }
}