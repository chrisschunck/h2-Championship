package model;

public enum Resultado {
    VITORIA_TIME_A,
    VITORIA_TIME_B,
    EMPATE;

    public static Resultado fromGoals(int a, int b) {
        if (a > b) return VITORIA_TIME_A;
        if (b > a) return VITORIA_TIME_B;
        return EMPATE;
    }
}

