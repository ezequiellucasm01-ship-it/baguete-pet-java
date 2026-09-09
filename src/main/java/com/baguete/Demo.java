package com.baguete;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe de demonstração que simula toda a sequência de eventos do Baguete PET
 * Mostra logs detalhados de cada etapa da execução
 */
public class Demo {
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String CIANO = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";

    public static void main(String[] args) throws InterruptedException {
        System.out.println(CIANO + "\n" +
            "  ╔════════════════════════════════════════════════╗\n" +
            "  ║        🥖 BAGUETE PET - SIMULADOR 🥖           ║\n" +
            "  ║     Demonstração de Sequência de Eventos      ║\n" +
            "  ╚════════════════════════════════════════════════╝" +
            RESET + "\n");

        // Fase 1: Detecção de Perfil
        fase1_DeteccaoPerfil();

        // Fase 2: Diálogo Inicial
        fase2_DialogoInicial();

        // Fase 3: Movimento do Mascote
        fase3_MovimentoMascote();

        // Fase 4: Jumpscare
        fase4_Jumpscare();

        // Resumo Final
        resumoFinal();
    }

    /**
     * FASE 1: Detecção do perfil do usuário
     */
    private static void fase1_DeteccaoPerfil() throws InterruptedException {
        imprimir("\n" + AMARELO + "=" + "=".repeat(48) + RESET, false);
        imprimir(AMARELO + "FASE 1: DETECTANDO PERFIL DO USUÁRIO" + RESET, true);
        imprimir(AMARELO + "=" + "=".repeat(48) + RESET, false);

        imprimir("\n[" + CIANO + "GoogleProfileReader" + RESET + "] Iniciando busca...", false);
        Thread.sleep(500);

        imprimir("[" + CIANO + "GoogleProfileReader" + RESET + "] Verificando caminhos...", true);
        Thread.sleep(300);
        imprimir("  └─ Windows: %LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default\\Preferences", true);
        Thread.sleep(300);
        imprimir("  └─ Linux: ~/.config/google-chrome/Default/Preferences", true);
        Thread.sleep(300);
        imprimir("  └─ macOS: ~/Library/Application Support/Google/Chrome/Default/Preferences", true);
        Thread.sleep(300);

        String nomeUsuario = System.getenv("USERNAME") != null ? System.getenv("USERNAME") :
                            System.getenv("USER") != null ? System.getenv("USER") : "Jogador";
        String primeiroNome = nomeUsuario.split(" ")[0];

        imprimir("\n[" + VERDE + "✓ SUCESSO" + RESET + "] Nome de usuário detectado: " + MAGENTA + primeiroNome + RESET, true);
    }

    /**
     * FASE 2: Diálogo inicial pedindo amizade
     */
    private static void fase2_DialogoInicial() throws InterruptedException {
        imprimir("\n" + AMARELO + "=" + "=".repeat(48) + RESET, false);
        imprimir(AMARELO + "FASE 2: DIÁLOGO INICIAL" + RESET, true);
        imprimir(AMARELO + "=" + "=".repeat(48) + RESET, false);

        imprimir("\n[" + CIANO + "BaguetePET" + RESET + "] Criando janela de diálogo...", true);
        Thread.sleep(400);

        imprimir("[" + VERDE + "✓ JANELA CRIADA" + RESET + "] Dimensões: 400x180", true);
        Thread.sleep(300);

        imprimir("\n" + MAGENTA + "╔════════════════════════════════════════╗" + RESET, true);
        imprimir(MAGENTA + "║                                        ║" + RESET, true);
        imprimir(MAGENTA + "║  Olá, Jogador!                         ║" + RESET, true);
        imprimir(MAGENTA + "║  Eu acabei de entrar.                  ║" + RESET, true);
        imprimir(MAGENTA + "║  Quer ser meu amigo?                   ║" + RESET, true);
        imprimir(MAGENTA + "║                                        ║" + RESET, true);
        imprimir(MAGENTA + "║         [  Sim  ]   [  Não  ]          ║" + RESET, true);
        imprimir(MAGENTA + "║                                        ║" + RESET, true);
        imprimir(MAGENTA + "╚════════════════════════════════════════╝" + RESET, true);

        imprimir("\n[" + CIANO + "USUÁRIO" + RESET + "] Clicando em [Sim]...", true);
        Thread.sleep(500);

        imprimir("[" + VERDE + "✓ AMIZADE ACEITA" + RESET + "] Fechando diálogo...", true);
        Thread.sleep(400);
    }

    /**
     * FASE 3: Movimento do mascote pela tela
     */
    private static void fase3_MovimentoMascote() throws InterruptedException {
        imprimir("\n" + AMARELO + "=" + "=".repeat(48) + RESET, false);
        imprimir(AMARELO + "FASE 3: MOVIMENTO DO MASCOTE" + RESET, true);
        imprimir(AMARELO + "=" + "=".repeat(48) + RESET, false);

        imprimir("\n[" + CIANO + "MascoteFlotuante" + RESET + "] Iniciando movimento...", true);
        Thread.sleep(300);

        double posX = Math.random() * 500;
        double velocidade = 3;
        imprimir("[" + VERDE + "✓ INICIADO" + RESET + "] Posição X inicial: " + String.format("%.2f", posX), true);
        Thread.sleep(300);
        imprimir("[" + VERDE + "✓ INICIADO" + RESET + "] Velocidade: " + velocidade + " pixels/frame", true);
        Thread.sleep(300);

        // Simula o movimento
        imprimir("\n[MOVIMENTO] Baguete se movendo pela tela:", true);
        for (int i = 0; i < 8; i++) {
            posX += velocidade;
            if (posX > 1200) {
                velocidade = -velocidade;
                imprimir("  └─ [" + CIANO + String.format("%3.0f", posX) + "px" + RESET + "] ← Invertendo direção (borda direita)", true);
            } else if (posX < 0) {
                velocidade = -velocidade;
                imprimir("  └─ [" + CIANO + String.format("%3.0f", posX) + "px" + RESET + "] → Invertendo direção (borda esquerda)", true);
            } else {
                imprimir("  └─ [" + CIANO + String.format("%3.0f", posX) + "px" + RESET + "] Movimento suave (20ms por frame)", true);
            }
            Thread.sleep(800);
        }

        // Agenda o jumpscare
        long tempoSusto = 15000 + (long) (Math.random() * 25000);
        imprimir("\n[" + AMARELO + "⏱️  TEMPORIZADOR" + RESET + "] Jumpscare agendado em: " + String.format("%.1f", tempoSusto / 1000.0) + "s", true);
        Thread.sleep(500);
        imprimir("[" + AMARELO + "⏱️  TEMPORIZADOR" + RESET + "] Aguardando...", true);
        Thread.sleep(2000);
    }

    /**
     * FASE 4: Efeito de jumpscare
     */
    private static void fase4_Jumpscare() throws InterruptedException {
        imprimir("\n" + AMARELO + "=" + "=".repeat(48) + RESET, false);
        imprimir(AMARELO + "FASE 4: JUMPSCARE" + RESET, true);
        imprimir(AMARELO + "=" + "=".repeat(48) + RESET, false);

        imprimir("\n[" + VERMELHO + "⚠️  ALERTA" + RESET + "] Tempo de jumpscare acionado!", true);
        Thread.sleep(400);

        imprimir("[" + VERMELHO + "⚠️  ALERTA" + RESET + "] Fechando janela do mascote normal...", true);
        Thread.sleep(300);

        imprimir("[" + VERMELHO + "⚠️  ALERTA" + RESET + "] Criando janela de susto (500x500)...", true);
        Thread.sleep(300);

        // Efeito visual do jumpscare
        imprimir("\n" + VERMELHO + "╔═══════════════════════════════════════════╗" + RESET, true);
        imprimir(VERMELHO + "║                                           ║" + RESET, true);
        imprimir(VERMELHO + "║              ⚠️  JUMPSCARE  ⚠️             ║" + RESET, true);
        imprimir(VERMELHO + "║                                           ║" + RESET, true);
        Thread.sleep(300);

        imprimir(VERMELHO + "║  🥖 ← BAGUETE ASSUSTADOR COM OLHOS        ║" + RESET, true);
        imprimir(VERMELHO + "║      RABISCADOS E ROSTO DISTORCIDO!      ║" + RESET, true);
        Thread.sleep(300);

        imprimir(VERMELHO + "║                                           ║" + RESET, true);
        imprimir(VERMELHO + "║  VOCÊ NÃO PODE FUGIR DE MIM, JOGADOR!    ║" + RESET, true);
        imprimir(VERMELHO + "║                                           ║" + RESET, true);
        imprimir(VERMELHO + "╚═══════════════════════════════════════════╝" + RESET, true);

        imprimir("\n[" + VERMELHO + "🔊 SOM" + RESET + "] Reproduzindo som de susto...", true);
        Thread.sleep(500);
        imprimir("  ♪♫ ♪ ♫ ♪ (som assustador do sistema) ♪ ♫ ♪ ♫ ♪", true);
        Thread.sleep(1000);

        imprimir("\n[" + AMARELO + "⏱️  TEMPORIZADOR" + RESET + "] Susto exibido. Fechando em 5 segundos...", true);
        for (int i = 4; i >= 1; i--) {
            Thread.sleep(1000);
            imprimir("[" + AMARELO + "⏱️  TEMPORIZADOR" + RESET + "] " + i + "...", true);
        }

        imprimir("\n[" + VERDE + "✓ CONCLUÍDO" + RESET + "] Janela de susto fechada.", true);
        Thread.sleep(500);
    }

    /**
     * Resumo final da execução
     */
    private static void resumoFinal() throws InterruptedException {
        imprimir("\n" + CIANO + "=" + "=".repeat(48) + RESET, false);
        imprimir(CIANO + "📊 RESUMO DA EXECUÇÃO" + RESET, true);
        imprimir(CIANO + "=" + "=".repeat(48) + RESET, false);

        imprimir("\n✅ [FASE 1] Detecção de Perfil ..................... CONCLUÍDA", true);
        Thread.sleep(200);
        imprimir("✅ [FASE 2] Diálogo Inicial ........................ CONCLUÍDA", true);
        Thread.sleep(200);
        imprimir("✅ [FASE 3] Movimento do Mascote ................... CONCLUÍDA", true);
        Thread.sleep(200);
        imprimir("✅ [FASE 4] Jumpscare ............................. CONCLUÍDA", true);
        Thread.sleep(200);

        imprimir("\n" + VERDE + "🎉 SIMULAÇÃO CONCLUÍDA COM SUCESSO!" + RESET, true);
        Thread.sleep(300);

        imprimir("\n📋 Eventos Registrados:", true);
        imprimir("  • Nome do usuário detectado com sucesso", true);
        imprimir("  • Diálogo inicial exibido", true);
        imprimir("  • Mascote moveu-se pela tela (8 posições)", true);
        imprimir("  • Jumpscare foi acionado", true);
        imprimir("  • Som do sistema foi reproduzido", true);
        imprimir("  • Aplicação foi encerrada corretamente", true);

        imprimir("\n" + VERDE + "Para executar a versão com GUI:" + RESET, true);
        imprimir("  $ mvn javafx:run", true);

        imprimir("\n" + CIANO + "=" + "=".repeat(48) + RESET + "\n", false);
    }

    /**
     * Método auxiliar para imprimir com timestamp
     */
    private static void imprimir(String mensagem, boolean comTimestamp) {
        if (comTimestamp) {
            System.out.println("[" + CIANO + LocalDateTime.now().format(formatador) + RESET + "] " + mensagem);
        } else {
            System.out.println(mensagem);
        }
    }
}
