package model;

import java.util.Objects;

public class Partida {
    private Long id;
    private String timeA;
    private String timeB;
    private int golsTimeA;
    private int golsTimeB;
    private Resultado resultado;
    private int cartoesAmarelos;
    private int cartoesVermelhos;
    private int escanteios;
    private int quantidadePenalti;
    private boolean penaltiConvertido;

    public Partida() {}

    public Partida(String timeA, String timeB, int golsTimeA, int golsTimeB) {
        if (golsTimeA < 0 || golsTimeB < 0) throw new IllegalArgumentException("Gols não podem ser negativos");
        this.timeA = Objects.requireNonNull(timeA);
        this.timeB = Objects.requireNonNull(timeB);
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
        this.resultado = Resultado.fromGoals(golsTimeA, golsTimeB);
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTimeA() { return timeA; }
    public void setTimeA(String timeA) { this.timeA = timeA; }
    public String getTimeB() { return timeB; }
    public void setTimeB(String timeB) { this.timeB = timeB; }
    public int getGolsTimeA() { return golsTimeA; }
    public void setGolsTimeA(int golsTimeA) { this.golsTimeA = golsTimeA; this.resultado = Resultado.fromGoals(this.golsTimeA, this.golsTimeB); }
    public int getGolsTimeB() { return golsTimeB; }
    public void setGolsTimeB(int golsTimeB) { this.golsTimeB = golsTimeB; this.resultado = Resultado.fromGoals(this.golsTimeA, this.golsTimeB); }
    public Resultado getResultado() { return resultado; }
    public int getCartoesAmarelos() { return cartoesAmarelos; }
    public void setCartoesAmarelos(int cartoesAmarelos) { this.cartoesAmarelos = cartoesAmarelos; }
    public int getCartoesVermelhos() { return cartoesVermelhos; }
    public void setCartoesVermelhos(int cartoesVermelhos) { this.cartoesVermelhos = cartoesVermelhos; }
    public int getEscanteios() { return escanteios; }
    public void setEscanteios(int escanteios) { this.escanteios = escanteios; }
    public int getQuantidadePenalti() { return quantidadePenalti; }
    public void setQuantidadePenalti(int quantidadePenalti) { this.quantidadePenalti = quantidadePenalti; }
    public boolean isPenaltiConvertido() { return penaltiConvertido; }
    public void setPenaltiConvertido(boolean penaltiConvertido) { this.penaltiConvertido = penaltiConvertido; }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", timeA='" + timeA + '\'' +
                ", timeB='" + timeB + '\'' +
                ", golsTimeA=" + golsTimeA +
                ", golsTimeB=" + golsTimeB +
                ", resultado=" + resultado +
                '}';
    }
}
