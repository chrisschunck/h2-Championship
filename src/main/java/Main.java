import model.Partida;
import service.PartidaService;
import service.JogadorService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaService service = new PartidaService();

        try {
            org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        } catch (Exception e) {
            System.out.println("Aviso: não foi possível iniciar o console H2: " + e.getMessage());
        }

        int opcao = -1;
        do {
            System.out.println("====== Menu Campeonato ======");
            System.out.println("1 - Registrar Jogador");
            System.out.println("2 - Registrar Partida");
            System.out.println("3 - Listar Partidas");
            System.out.println("4 - Atualizar Partida");
            System.out.println("5 - Deletar Partida");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            String line = scanner.nextLine();
            try {
                opcao = Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Opção inválida. Digite um número entre 0 e 4.");
                continue;
            }
            if (opcao < 0 || opcao > 4) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome jogador: ");
                        String nome = scanner.nextLine();
                        System.out.print("Idade jogador: ");
                        int idade = scanner.nextInt();
                        System.out.print("Posição jogador: ");
                        String posicao = scanner.nextLine();
                        System.out.print("Salário jogador: ");
                        double salario = scanner.nextDouble();
                        System.out.print("Email jogador: ");
                        String email = scanner.nextLine();
                        System.out.print("Altura jogador: ");
                        double altura = scanner.nextDouble();
                        System.out.print("Peso jogador: ")
                        int peso = scanner.nextInt();
                        System.out.print("Status jogador: ");
                        boolean status = scanner.nextBoolean();
                        System.out.print("Time atual jogador: ");
                        Time time = scanner.nextLine();

                        Jogador jogador = new Jogador(nome, idade, posicao, salario, email, altura, peso);
                        jogador.setStatus(status);
                        jogador.setTime(time);

                        jogador.registarJogador(jogador);
                        System.out.println("Jogador: " + jogador.getNome() + " registrado");
                    }
                    case 2 -> {
                        System.out.print("Time A: ");
                        String timeA = scanner.nextLine();
                        System.out.print("Time B: ");
                        String timeB = scanner.nextLine();
                        System.out.print("Gols Time A: ");
                        int golsA = Integer.parseInt(scanner.nextLine());
                        System.out.print("Gols Time B: ");
                        int golsB = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões amarelos: ");
                        int cartoesAmarelos = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões vermelhos: ");
                        int cartoesVermelhos = Integer.parseInt(scanner.nextLine());
                        System.out.print("Escanteios: ");
                        int escanteios = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade de pênaltis: ");
                        int quantidadePenalti = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pênalti convertido? (s/n): ");
                        boolean penaltiConvertido = scanner.nextLine().trim().equalsIgnoreCase("s");

                        Partida partida = new Partida(timeA, timeB, golsA, golsB);
                        partida.setCartoesAmarelos(cartoesAmarelos);
                        partida.setCartoesVermelhos(cartoesVermelhos);
                        partida.setEscanteios(escanteios);
                        partida.setQuantidadePenalti(quantidadePenalti);
                        partida.setPenaltiConvertido(penaltiConvertido);

                        service.registrarPartida(partida);
                        System.out.println("✓ Partida registrada com sucesso!");
                        System.out.println("  Resultado: " + partida.getResultado());
                    }
                    case 3 -> {
                        List<Partida> partidas = service.listarPartidas();
                        if (partidas.isEmpty()) {
                            System.out.println("Nenhuma partida registrada.");
                        } else {
                            System.out.println("\n========== PARTIDAS REGISTRADAS ==========");
                            for (Partida partida : partidas) {
                                System.out.println("\nID: " + partida.getId());
                                System.out.println("  " + partida.getTimeA() + " " + part.getGolsTimeA() + " x " + partida.getGolsTimeB() + " " + partida.getTimeB());
                                System.out.println("  Resultado: " + partida.getResultado());
                                System.out.println("  Cartões: " + partida.getCartoesAmarelos() + " 🟨 | " + partida.getCartoesVermelhos() + " 🟥");
                                System.out.println("  Escanteios: " + partida.getEscanteios() + " | Pênaltis: " + partida.getQuantidadePenalti() + (partida.isPenaltiConvertido() ? " ✓" : " ✗"));
                            }
                            System.out.println("\n=========================================");
                        }
                    }
                    case 4 -> {
                        System.out.print("Id da partida a atualizar: ");
                        long id = Long.parseLong(scanner.nextLine());
                        System.out.print("Gols Time A: ");
                        int golsA = Integer.parseInt(scanner.nextLine());
                        System.out.print("Gols Time B: ");
                        int golsB = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões amarelos: ");
                        int cartoesAmarelos = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões vermelhos: ");
                        int cartoesVermelhos = Integer.parseInt(scanner.nextLine());
                        System.out.print("Escanteios: ");
                        int escanteios = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade de pênaltis: ");
                        int quantidadePenalti = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pênalti convertido? (s/n): ");
                        boolean penaltiConvertido = scanner.nextLine().trim().equalsIgnoreCase("s");

                        Partida partida = new Partida();
                        partida.setId(id);
                        partida.setGolsTimeA(golsA);
                        partida.setGolsTimeB(golsB);
                        partida.setCartoesAmarelos(cartoesAmarelos);
                        partida.setCartoesVermelhos(cartoesVermelhos);
                        partida.setEscanteios(escanteios);
                        partida.setQuantidadePenalti(quantidadePenalti);
                        partida.setPenaltiConvertido(penaltiConvertido);

                        service.atualizarPartida(partida);
                        System.out.println("✓ Partida atualizada com sucesso!");
                    }
                    case 5 -> {
                        System.out.print("Id da partida a deletar: ");
                        long id = Long.parseLong(scanner.nextLine());
                        service.deletarPartida(id);
                        System.out.println("Partida deletada");
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}
