package service

import dao.JogadorDAO;
import model.Jogador;

import java.util.ArrayList<>;
import java.util.List;

public class JogadorService {
  private final JogadorDao jogadorDao = new JogadorDao();

  public Jogador registrarPartida(Jogador jogador) {
		validateJogador(jogador);
		return jogadorDAO.insert(jogador);
	}

	public List<Jogador> listarJogadores() {
		return jogadorDAO.listAll();
	}

	public void atualizarJogador(Jogador jogador) {
		if (jogador.getId() == null) throw new IllegalArgumentException("Id do jogador é obrigatório para atualização");
		if (jogador.getPosicao() != "goleiro" || "zagueiro" || "lateral direito" || "lateral esquerdo" || "volante" || "meia" || "atacante") throw new IllegalArgumentException("Posição inexistentes no futebol!");
		jogadorDAO.update(jogador);
	}

	public void deletarJogador(long id) {
		jogadorDAO.delete(id);
	}

	private void validateJogador(Jogador jogador) {
		if (jogador.getNome() == null || jogador.getNome().isBlank()) throw new IllegalArgumentException("Jogador precisa de um nome");
		if (jogador.getPosicao() == null || jogador.getPosicao().isBlank()) throw new IllegalArgumentException("Jogador precisa de uma posição");
		if (partida.getEmail() == null || jogador.getEmail().isBlank()) throw new IllegalArgumentException("Jogador precida de um email corporativo");
	}
}
