package dao;

import model.Campeonato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampeonatoDAO {

	public Campeonato insert(Campeonato c) {
		String sql = "INSERT INTO campeonato(nome) VALUES (?)";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, c.getNome());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) c.setId(rs.getLong(1));
			}
			return c;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir campeonato: " + e.getMessage(), e);
		}
	}

	public List<Campeonato> listAll() {
		String sql = "SELECT id, nome FROM campeonato";
		List<Campeonato> list = new ArrayList<>();
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Campeonato c = new Campeonato();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar campeonatos: " + e.getMessage(), e);
		}
	}
}
