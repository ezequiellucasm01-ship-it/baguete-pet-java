package com.baguete;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Emulador visual de Windows para o Baguete PET
 * Simula a interface gráfica com janelas, diálogos e animações em ASCII
 */
public class EmuladorWindows {
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String CIANO = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String BRANCO = "\u001B[37m";
    private static final String AZUL = "\u001B[34m";

    private static Scanner scanner = new Scanner(System.in);
    private static String nomeJogador = "Ezequiel";

    public static void main(String[] args) throws InterruptedException {
        limparTela();
        exibirTelaWindows();
        
        // Fase 1: Diálogo Inicial
        fase1_DialogoInicial();
        
        // Fase 2: Mascote Flutuante
        fase2_MascoteFlotuante();
        
        // Fase 3: Jumpscare
        fase3_Jumpscare();
        
        // Fase 4: Encerramento
        fase4_Encerramento();
    }

    /**
     * Exibe a tela inicial do Windows
     */
    private static void exibirTelaWindows() {
        System.out.println(AZUL + "\n");
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████");
        System.out.println("█                                                                                   █");
        System.out.println("█  Windows 10                                                                      █");
        System.out.println("█                                                                                   █");
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████");
        System.out.println("█                                                                                   █");
        System.out.println("█  [Ícone] Baguete PET                                                             █");
        System.out.println("█  [Ícone] Explorador de Arquivos                                                  █");
        System.out.println("█  [Ícone] Chrome                                                                  █");
        System.out.println("█                                                                                   █");
        System.out.println("█                                                                                   █");
        System.out.println("█                                                                                   █");
        System.out.println("█                                                                                   █");
        System.out.println("█                                                                                   █");
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████");
        System.out.println("█ ◀ Iniciar  🔍  ╳  □  🔔  ◀▶  🔊  🕐" + LocalDateTime.now().format(formatador) + "                     █");
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████" + RESET);
    }

    /**
     * FASE 1: Diálogo inicial
     */
    private static void fase1_DialogoInicial() throws InterruptedException {
        limparTela();
        exibirTelaWindows();
        
        System.out.println("\n\n" + MAGENTA);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                  Baguete PET                  ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃                                                ┃");
        System.out.println("┃            Olá, " + VERDE + nomeJogador + MAGENTA + "!                           ┃");
        System.out.println("┃         Eu acabei de entrar.                  ┃");
        System.out.println("┃         Quer ser meu amigo?                   ┃");
        System.out.println("┃                                                ┃");
        System.out.println("┃          ┌──────────────────────────────┐     ┃");
        System.out.println("┃          │  [ Sim ]       [ Não ]       │     ┃");
        System.out.println("┃          └──────────────────────────────┘     ┃");
        System.out.println("┃                                                ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + RESET);
        
        System.out.println("\n" + CIANO + "👉 Digite sua resposta (S/N): " + RESET);
        String resposta = scanner.nextLine().toUpperCase();
        
        if (resposta.equals("S") || resposta.equals("SIM")) {
            System.out.println("\n" + VERDE + "✓ Amizade aceita! Fechando diálogo..." + RESET);
            Thread.sleep(1500);
        } else {
            System.out.println("\n" + VERMELHO + "⚠️  ERRO: Essa não é uma resposta aceitável!" + RESET);
            System.out.println(VERMELHO + "Nós seremos amigos de qualquer forma! 😈" + RESET);
            Thread.sleep(2000);
        }
    }

    /**
     * FASE 2: Mascote flutuante se movendo
     */
    private static void fase2_MascoteFlotuante() throws InterruptedException {
        limparTela();
        exibirTelaWindows();
        
        System.out.println("\n" + AMARELO + "╔════════════════════════════════════════════════════╗" + RESET);
        System.out.println(AMARELO + "║         MASCOTE APARECENDO NA TELA...             ║" + RESET);
        System.out.println(AMARELO + "╚════════════════════════════════════════════════════╝" + RESET);
        
        System.out.println("\n" + CIANO + "[GoogleProfileReader] Procurando pelo Chrome..." + RESET);
        Thread.sleep(800);
        System.out.println(VERDE + "✓ Perfil encontrado: " + nomeJogador + RESET);
        Thread.sleep(800);
        
        System.out.println("\n" + AMARELO + "[MascoteFlotuante] Iniciando movimento..." + RESET);
        Thread.sleep(1000);
        
        // Animação de movimento
        System.out.println("\n" + MAGENTA + "MOVIMENTO DO MASCOTE NA TELA:" + RESET);
        
        int[] posicoes = {100, 300, 500, 700, 900, 1000, 800, 600, 400, 200, 100};
        
        for (int i = 0; i < posicoes.length; i++) {
            StringBuilder linha = new StringBuilder();
            for (int j = 0; j < posicoes[i] / 50; j++) {
                linha.append(" ");
            }
            linha.append(MAGENTA).append("🥖").append(RESET);
            
            System.out.println("[Frame " + (i+1) + "/11] " + linha);
            Thread.sleep(600);
        }
        
        System.out.println("\n" + AMARELO + "⏱️  Temporizador: Jumpscare será acionado em 3 segundos..." + RESET);
        for (int i = 3; i >= 1; i--) {
            System.out.println(AMARELO + "⏱️  " + i + "..." + RESET);
            Thread.sleep(1000);
        }
    }

    /**
     * FASE 3: Jumpscare!
     */
    private static void fase3_Jumpscare() throws InterruptedException {
        limparTela();
        
        // Tela vermelha de terror
        System.out.println("\n\n");
        for (int i = 0; i < 3; i++) {
            System.out.println(VERMELHO + "╔═══════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        }
        
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        System.out.println(VERMELHO + "║                                  ⚠️  JUMPSCARE  ⚠️                                    ║" + RESET);
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        
        System.out.println("\n");
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        System.out.println(VERMELHO + "║                          🥖 ← BAGUETE ASSUSTADOR                                      ║" + RESET);
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        System.out.println(VERMELHO + "║                     Olhos rabiscados e rosto distorcido!                              ║" + RESET);
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        System.out.println("\n");
        
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        System.out.println(VERMELHO + "║            VOCÊ NÃO PODE FUGIR DE MIM, " + nomeJogador.toUpperCase() + "!                              ║" + RESET);
        System.out.println(VERMELHO + "║                                                                                       ║" + RESET);
        
        for (int i = 0; i < 3; i++) {
            System.out.println(VERMELHO + "╚═══════════════════════════════════════════════════════════════════════════════════════╝" + RESET);
        }
        
        System.out.println("\n");
        
        // Som
        System.out.println(VERMELHO + "🔊 REPRODUZINDO SOM DE SUSTO..." + RESET);
        System.out.println("   ♪♫ ♪ ♫ ♪ (som assustador do sistema) ♪ ♫ ♪ ♫ ♪");
        System.out.println("   ♪♫ ♪ ♫ ♪ (som assustador do sistema) ♪ ♫ ♪ ♫ ♪");
        System.out.println("   ♪♫ ♪ ♫ ♪ (som assustador do sistema) ♪ ♫ ♪ ♫ ♪\n");
        
        Thread.sleep(2000);
        
        System.out.println(AMARELO + "⏱️  Susto exibido! Fechando em 5 segundos..." + RESET);
        for (int i = 5; i >= 1; i--) {
            System.out.println(AMARELO + "⏱️  " + i + "..." + RESET);
            Thread.sleep(1000);
        }
    }

    /**
     * FASE 4: Encerramento
     */
    private static void fase4_Encerramento() throws InterruptedException {
        limparTela();
        exibirTelaWindows();
        
        System.out.println("\n\n" + VERDE);
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        🎉 EMULAÇÃO CONCLUÍDA COM SUCESSO! 🎉                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println(RESET);
        
        System.out.println("\n" + CIANO + "📊 RELATÓRIO FINAL:" + RESET);
        System.out.println("  ✅ [FASE 1] Diálogo inicial exibido");
        System.out.println("  ✅ [FASE 2] Mascote se moveu pela tela (11 frames)");
        System.out.println("  ✅ [FASE 3] Jumpscare acionado com sucesso");
        System.out.println("  ✅ [FASE 4] Aplicação encerrada");
        
        System.out.println("\n" + VERDE + "📋 EVENTOS REGISTRADOS:" + RESET);
        System.out.println("  • Perfil do usuário detectado: " + nomeJogador);
        System.out.println("  • Amizade aceita pelo jogador");
        System.out.println("  • Mascote aparecer na tela e se moveu");
        System.out.println("  • Jumpscare acionado após período de espera");
        System.out.println("  • Som de susto reproduzido");
        System.out.println("  • Tela fechada automaticamente");
        
        System.out.println("\n" + AZUL + "═════════════════════════════════════════════════════════════════════════════════════════" + RESET);
        System.out.println(AZUL + "  Para executar a versão REAL com GUI (JavaFX):" + RESET);
        System.out.println(VERDE + "  $ mvn javafx:run" + RESET);
        System.out.println(AZUL + "═════════════════════════════════════════════════════════════════════════════════════════\n" + RESET);
        
        scanner.close();
    }

    /**
     * Limpa a tela do console
     */
    private static void limparTela() throws InterruptedException {
        // Tenta limpar a tela
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Se não conseguir, imprime linhas em branco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
