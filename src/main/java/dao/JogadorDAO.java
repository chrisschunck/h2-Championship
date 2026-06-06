package dao;

import model.Jogador;

import java.util.ArrayList; 
import java.util.List;
import java.sql.*;

public class JogadorDAO {
  public Jogador insert(Jogador jogador) {
		String sql = "INSERT INTO jogador(nome, idade, posicao, salario, email, altura, peso, status, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedstatement.setString(1, jogador.getNome());
			preparedstatement.setInt(2, jogador.getIdade());
			preparedstatement.setString(3, jogador.getPosicao());
			preparedstatement.setDouble(4, jogador.getSalario());
			preparedstatement.setString(5, jogador.getEmail());
			preparedstatement.setDouble(6, jogador.getAltura());
			preparedstatement.setInt(7, jogador.getPeso());
			preparedstatement.setBoolean(8, jogador.getStatus());
      preparedstatement.setTime(9, jogador.getTime());
			preparedstatement.executeUpdate();
			try (ResultSet resultset = preparedstatement.getGeneratedKeys()) {
				if (resultset.next()) jogador.setId(resultset.getLong(1));
			}
			return jogador;
		} catch (SQLException exception) {
			throw new RuntimeException("Erro ao inserir partida: " + exception.getMessage(), exception);
		}
	}

	public List<Jogador> listAll() {
		String sql = "SELECT id, nome, idade, posicao, salario, email, altura, peso, status, time";
		List<Jogador> list = new ArrayList<>();
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql); ResultSet resultset = preparedstatement.executeQuery()) {
			while (resultset.next()) {
				Jogador jogador = new Jogador();
				jogador.setId(resultset.getLong("id"));
				jogador.setNome(resultset.getString("time_a"));
				jogador.setIdade(resultset.getString("time_b"));
				jogador.setPosicao(resultset.getInt("gols_time_a"));
				jogador.setSalario(resultset.getInt("gols_time_b"));
        jogador.setEmail(resultset.getString("email"));
        jogador.setAltura(resultset.getDouble("altura"));
        jogador.setPeso(resultset.getInt("peso"));
				jogador.setStatus(resultset.getBoolean("status"));
				jogador.setTime(resultset.getTime("time"));
				list.add(jogador);
			}
			return list;
		} catch (SQLException exception) {
			throw new RuntimeException("Erro ao listar partidas: " + exception.getMessage(), exception);
		}
	}

	public void delete(long id) {
		String sql = "DELETE FROM jogador WHERE id = ?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
			preparedstatement.setLong(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException exception) {
			throw new RuntimeException("Erro ao deletar jogador: " + exception.getMessage(), exception);
		}
	}

	public void update(Jogador jogador) {
		String sql = "UPDATE jogador SET nome=?, idade=?, posicao=?, salario=?, email=?, altura=?, peso=?, status=?, time=?, WHERE id=?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
			preparestatement.setString(1, jogador.getNome());
			preparedstatement.setInt(2, jogador.getIdade());
			preparedstatement.setString(3, jogador.getPosicao());
			preparedstatement.setDouble(4, jogador.getSalario());
			preparedstatement.setString(5, jogador.getEmail());
			preparedstatement.setDouble(6, jogador.getAltura());
			preparedstatement.setInt(7, jogador.getPeso());
			preparedstatement.setBoolean(8, jogador.getStatus());
			preparedstatement.setTime(9, jogador.getTime());
			preparedstatement.setLong(10, partida.getId());
			preparedstatement.executeUpdate();
		} catch (SQLException exception) {
			throw new RuntimeException("Erro ao atualizar jogador: " + exception.getMessage(), exception);
		}
	}
}
