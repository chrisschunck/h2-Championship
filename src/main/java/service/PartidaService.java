package service;

import dao.PartidaDAO;
import model.Partida;

import java.util.List;

public class PartidaService {
	private final PartidaDAO partidaDAO = new PartidaDAO();

	public Partida registrarPartida(Partida p) {
		validatePartida(p);
		return partidaDAO.insert(p);
	}

	public List<Partida> listarPartidas() {
		return partidaDAO.listAll();
	}

	public void atualizarPartida(Partida p) {
		if (p.getId() == null) throw new IllegalArgumentException("Id da partida é obrigatório para atualização");
		// allow partial updates (e.g., only goals) so validate only fields that are present
		if (p.getGolsTimeA() < 0 || p.getGolsTimeB() < 0) throw new IllegalArgumentException("Gols não podem ser negativos");
		partidaDAO.update(p);
	}

	public void deletarPartida(long id) {
		partidaDAO.delete(id);
	}

	private void validatePartida(Partida p) {
		if (p.getTimeA() == null || p.getTimeA().isBlank()) throw new IllegalArgumentException("Time A inválido");
		if (p.getTimeB() == null || p.getTimeB().isBlank()) throw new IllegalArgumentException("Time B inválido");
		if (p.getGolsTimeA() < 0 || p.getGolsTimeB() < 0) throw new IllegalArgumentException("Gols não podem ser negativos");
	}
}
