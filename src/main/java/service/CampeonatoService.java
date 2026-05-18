package service;

import dao.CampeonatoDAO;
import model.Campeonato;

import java.util.List;

public class CampeonatoService {
	private final CampeonatoDAO dao = new CampeonatoDAO();

	public Campeonato criarCampeonato(String nome) {
		if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do campeonato inválido");
		return dao.insert(new Campeonato(nome));
	}

	public List<Campeonato> listarCampeonatos() {
		return dao.listAll();
	}
}
