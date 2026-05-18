package model;

public class Campeonato {
	private Long id;
	private String nomeCampeonato;

	public Campeonato() {}

	public Campeonato(String nome) { this.nomeCampeonato = nome; }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nomeCampeonato; }
	public void setNome(String nome) { this.nomeCampeonato = nome; }
}
