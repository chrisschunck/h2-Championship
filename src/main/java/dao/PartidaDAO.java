package dao;

import model.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

	public Partida insert(Partida partida) {
		String sql = "INSERT INTO partida(time_a, time_b, gols_time_a, gols_time_b, resultado, cartoes_amarelos, cartoes_vermelhos, escanteios, quantidade_penalti, penalti_convertido) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedstatement.setString(1, partida.getTimeA());
			preparedstatement.setString(2, partida.getTimeB());
			preparedstatement.setInt(3, partida.getGolsTimeA());
			preparedstatement.setInt(4, partida.getGolsTimeB());
			preparedstatement.setString(5, partida.getResultado() != null ? p.getResultado().name() : null);
			preparedstatement.setInt(6, partida.getCartoesAmarelos());
			preparedstatement.setInt(7, partida.getCartoesVermelhos());
			preparedstatement.setInt(8, partida.getEscanteios());
			preparedstatement.setInt(9, partida.getQuantidadePenalti());
			preparedstatement.setBoolean(10, partida.isPenaltiConvertido());
			preparedstatement.executeUpdate();
			try (ResultSet resultset = preparedstatement.getGeneratedKeys()) {
				if (resultset.next()) partida.setId(resultset.getLong(1));
			}
			return p;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir partida: " + e.getMessage(), e);
		}
	}

	public List<Partida> listAll() {
		String sql = "SELECT id, time_a, time_b, gols_time_a, gols_time_b, resultado, cartoes_amarelos, cartoes_vermelhos, escanteios, quantidade_penalti, penalti_convertido FROM partida";
		List<Partida> list = new ArrayList<>();
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql); ResultSet resultset = preparedstatement.executeQuery()) {
			while (resultset.next()) {
				Partida partida = new Partida();
				partida.setId(resultset.getLong("id"));
				partida.setTimeA(resultset.getString("time_a"));
				partida.setTimeB(resultset.getString("time_b"));
				partida.setGolsTimeA(resultset.getInt("gols_time_a"));
				partida.setGolsTimeB(resultset.getInt("gols_time_b"));
				String resultado = resultset.getString("resultado");
				if (resultado != null) partida.setGolsTimeA(p.getGolsTimeA()); // ensure resultado computed
				partida.setCartoesAmarelos(resultset.getInt("cartoes_amarelos"));
				partida.setCartoesVermelhos(resultset.getInt("cartoes_vermelhos"));
				partida.setEscanteios(resultset.getInt("escanteios"));
				partida.setQuantidadePenalti(resultset.getInt("quantidade_penalti"));
				partida.setPenaltiConvertido(resultset.getBoolean("penalti_convertido"));
				list.add(partida);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar partidas: " + e.getMessage(), e);
		}
	}

	public void delete(long id) {
		String sql = "DELETE FROM partida WHERE id = ?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
			preparedstatement.setLong(1, id);
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar partida: " + e.getMessage(), e);
		}
	}

	public void update(Partida partida) {
		String sql = "UPDATE partida SET time_a=?, time_b=?, gols_time_a=?, gols_time_b=?, resultado=?, cartoes_amarelos=?, cartoes_vermelhos=?, escanteios=?, quantidade_penalti=?, penalti_convertido=? WHERE id=?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement preparedstatement = connection.prepareStatement(sql)) {
			preparestatement.setString(1, partida.getTimeA());
			preparedstatement.setString(2, partida.getTimeB());
			preparedstatement.setInt(3, partida.getGolsTimeA());
			preparedstatement.setInt(4, partida.getGolsTimeB());
			preparedstatement.setString(5, partida.getResultado() != null ? p.getResultado().name() : null);
			preparedstatement.setInt(6, partida.getCartoesAmarelos());
			preparedstatement.setInt(7, partida.getCartoesVermelhos());
			preparedstatement.setInt(8, partida.getEscanteios());
			preparedstatement.setInt(9, partida.getQuantidadePenalti());
			preparedstatement.setBoolean(10, partida.isPenaltiConvertido());
			preparedstatement.setLong(11, partida.getId());
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar partida: " + e.getMessage(), e);
		}
	}
}
