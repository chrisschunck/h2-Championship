package service;

import dao.PartidaDAO;
import model.Partida;

import java.util.List;

public class PartidaService {
	private final PartidaDAO partidaDAO = new PartidaDAO();

	public Partida registrarPartida(Partida partida) {
		validatePartida(partida);
		return partidaDAO.insert(partida);
	}

	public List<Partida> listarPartidas() {
		return partidaDAO.listAll();
	}

	public void atualizarPartida(Partida partida) {
		if (partida.getId() == null) throw new IllegalArgumentException("Id da partida é obrigatório para atualização");
		// allow partial updates (e.g., only goals) so validate only fields that are present
		if (partida.getGolsTimeA() < 0 || partida.getGolsTimeB() < 0) throw new IllegalArgumentException("Gols não podem ser negativos");
		partidaDAO.update(partida);
	}

	public void deletarPartida(long id) {
		partidaDAO.delete(id);
	}

	private void validatePartida(Partida partida) {
		if (partida.getTimeA() == null || partida.getTimeA().isBlank()) throw new IllegalArgumentException("Time A inválido");
		if (partida.getTimeB() == null || partida.getTimeB().isBlank()) throw new IllegalArgumentException("Time B inválido");
		if (partida.getGolsTimeA() < 0 || partida.getGolsTimeB() < 0) throw new IllegalArgumentException("Gols não podem ser negativos");
	}
}
