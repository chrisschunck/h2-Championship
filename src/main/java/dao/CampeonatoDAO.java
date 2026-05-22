package dao;

import model.Campeonato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampeonatoDAO {

	public Campeonato insert(Campeonato campeonato) {
		String sql = "INSERT INTO campeonato(nome) VALUES (?)";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedstatement.setString(1, campeonato.getNome());
			preparedstatement.executeUpdate();
			try (ResultSet resultset = preparedstatement.getGeneratedKeys()) {
				if (resultset.next()) campeonato.setId(resultset.getLong(1));
			}
			return c;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir campeonato: " + e.getMessage(), e);
		}
	}

	public List<Campeonato> listAll() {
		String sql = "SELECT id, nome FROM campeonato";
		List<Campeonato> list = new ArrayList<>();
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql); ResultSet resultset = preparedstatement.executeQuery()) {
			while (resultset.next()) {
				Campeonato campeonato = new Campeonato();
				campeonato.setId(resultset.getLong("id"));
				campeonato.setNome(resultset.getString("nome"));
				list.add(campeonato);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar campeonatos: " + e.getMessage(), e);
		}
	}
}
