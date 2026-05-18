package model;

public class Campeonato {
	private Long id;
	private String nomeCampeonato;

	public Campeonato() {}

	public Campeonato(String nomeCampeonato) { this.nomeCampeonato = nomeCampeonato; }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNomeCampeonato() { return nomeCampeonato; }
	public void setNomeCampeonato(String nomeCampeonato) { this.nomeCampeonato = nomeCampeonato; }
}
