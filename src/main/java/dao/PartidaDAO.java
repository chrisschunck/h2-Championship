package dao;

import model.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

	public Partida insert(Partida p) {
		String sql = "INSERT INTO partida(time_a, time_b, gols_time_a, gols_time_b, resultado, cartoes_amarelos, cartoes_vermelhos, escanteios, quantidade_penalti, penalti_convertido) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, p.getTimeA());
			ps.setString(2, p.getTimeB());
			ps.setInt(3, p.getGolsTimeA());
			ps.setInt(4, p.getGolsTimeB());
			ps.setString(5, p.getResultado() != null ? p.getResultado().name() : null);
			ps.setInt(6, p.getCartoesAmarelos());
			ps.setInt(7, p.getCartoesVermelhos());
			ps.setInt(8, p.getEscanteios());
			ps.setInt(9, p.getQuantidadePenalti());
			ps.setBoolean(10, p.isPenaltiConvertido());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) p.setId(rs.getLong(1));
			}
			return p;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir partida: " + e.getMessage(), e);
		}
	}

	public List<Partida> listAll() {
		String sql = "SELECT id, time_a, time_b, gols_time_a, gols_time_b, resultado, cartoes_amarelos, cartoes_vermelhos, escanteios, quantidade_penalti, penalti_convertido FROM partida";
		List<Partida> list = new ArrayList<>();
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Partida p = new Partida();
				p.setId(rs.getLong("id"));
				p.setTimeA(rs.getString("time_a"));
				p.setTimeB(rs.getString("time_b"));
				p.setGolsTimeA(rs.getInt("gols_time_a"));
				p.setGolsTimeB(rs.getInt("gols_time_b"));
				String res = rs.getString("resultado");
				if (res != null) p.setGolsTimeA(p.getGolsTimeA()); // ensure resultado computed
				p.setCartoesAmarelos(rs.getInt("cartoes_amarelos"));
				p.setCartoesVermelhos(rs.getInt("cartoes_vermelhos"));
				p.setEscanteios(rs.getInt("escanteios"));
				p.setQuantidadePenalti(rs.getInt("quantidade_penalti"));
				p.setPenaltiConvertido(rs.getBoolean("penalti_convertido"));
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar partidas: " + e.getMessage(), e);
		}
	}

	public void delete(long id) {
		String sql = "DELETE FROM partida WHERE id = ?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar partida: " + e.getMessage(), e);
		}
	}

	public void update(Partida p) {
		String sql = "UPDATE partida SET time_a=?, time_b=?, gols_time_a=?, gols_time_b=?, resultado=?, cartoes_amarelos=?, cartoes_vermelhos=?, escanteios=?, quantidade_penalti=?, penalti_convertido=? WHERE id=?";
		try (Connection connection = DBConnection.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, p.getTimeA());
			ps.setString(2, p.getTimeB());
			ps.setInt(3, p.getGolsTimeA());
			ps.setInt(4, p.getGolsTimeB());
			ps.setString(5, p.getResultado() != null ? p.getResultado().name() : null);
			ps.setInt(6, p.getCartoesAmarelos());
			ps.setInt(7, p.getCartoesVermelhos());
			ps.setInt(8, p.getEscanteios());
			ps.setInt(9, p.getQuantidadePenalti());
			ps.setBoolean(10, p.isPenaltiConvertido());
			ps.setLong(11, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar partida: " + e.getMessage(), e);
		}
	}
}
