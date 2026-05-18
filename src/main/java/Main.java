import model.Partida;
import service.PartidaService;

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
            System.out.println("1 - Registrar partida");
            System.out.println("2 - Listar partidas");
            System.out.println("3 - Atualizar partida");
            System.out.println("4 - Deletar partida");
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
                        System.out.print("Time A: ");
                        String timeA = scanner.nextLine();
                        System.out.print("Time B: ");
                        String timeB = scanner.nextLine();
                        System.out.print("Gols Time A: ");
                        int golsA = Integer.parseInt(scanner.nextLine());
                        System.out.print("Gols Time B: ");
                        int golsB = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões amarelos: ");
                        int cartoesAm = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões vermelhos: ");
                        int cartoesVerm = Integer.parseInt(scanner.nextLine());
                        System.out.print("Escanteios: ");
                        int escanteios = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade de pênaltis: ");
                        int qtdPenalti = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pênalti convertido? (s/n): ");
                        boolean penaltiConv = scanner.nextLine().trim().equalsIgnoreCase("s");

                        Partida p = new Partida(timeA, timeB, golsA, golsB);
                        p.setCartoesAmarelos(cartoesAm);
                        p.setCartoesVermelhos(cartoesVerm);
                        p.setEscanteios(escanteios);
                        p.setQuantidadePenalti(qtdPenalti);
                        p.setPenaltiConvertido(penaltiConv);

                        service.registrarPartida(p);
                        System.out.println("✓ Partida registrada com sucesso!");
                        System.out.println("  Resultado: " + p.getResultado());
                    }
                    case 2 -> {
                        List<Partida> partidas = service.listarPartidas();
                        if (partidas.isEmpty()) {
                            System.out.println("Nenhuma partida registrada.");
                        } else {
                            System.out.println("\n========== PARTIDAS REGISTRADAS ==========");
                            for (Partida part : partidas) {
                                System.out.println("\nID: " + part.getId());
                                System.out.println("  " + part.getTimeA() + " " + part.getGolsTimeA() + " x " + part.getGolsTimeB() + " " + part.getTimeB());
                                System.out.println("  Resultado: " + part.getResultado());
                                System.out.println("  Cartões: " + part.getCartoesAmarelos() + " 🟨 | " + part.getCartoesVermelhos() + " 🟥");
                                System.out.println("  Escanteios: " + part.getEscanteios() + " | Pênaltis: " + part.getQuantidadePenalti() + (part.isPenaltiConvertido() ? " ✓" : " ✗"));
                            }
                            System.out.println("\n=========================================");
                        }
                    }
                    case 3 -> {
                        System.out.print("Id da partida a atualizar: ");
                        long id = Long.parseLong(scanner.nextLine());
                        System.out.print("Gols Time A: ");
                        int golsA = Integer.parseInt(scanner.nextLine());
                        System.out.print("Gols Time B: ");
                        int golsB = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões amarelos: ");
                        int cartoesAm = Integer.parseInt(scanner.nextLine());
                        System.out.print("Cartões vermelhos: ");
                        int cartoesVerm = Integer.parseInt(scanner.nextLine());
                        System.out.print("Escanteios: ");
                        int escanteios = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade de pênaltis: ");
                        int qtdPenalti = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pênalti convertido? (s/n): ");
                        boolean penaltiConv = scanner.nextLine().trim().equalsIgnoreCase("s");

                        Partida p = new Partida();
                        p.setId(id);
                        p.setGolsTimeA(golsA);
                        p.setGolsTimeB(golsB);
                        p.setCartoesAmarelos(cartoesAm);
                        p.setCartoesVermelhos(cartoesVerm);
                        p.setEscanteios(escanteios);
                        p.setQuantidadePenalti(qtdPenalti);
                        p.setPenaltiConvertido(penaltiConv);

                        service.atualizarPartida(p);
                        System.out.println("✓ Partida atualizada com sucesso!");
                    }
                    case 4 -> {
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
