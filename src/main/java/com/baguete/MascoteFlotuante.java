package com.baguete;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.screen.Screen;
import javafx.stage.Stage;

/**
 * Controla o movimento do mascote pela tela
 */
public class MascoteFlotuante {
    private final Stage janelamascote;
    private final String nomeJogador;
    private double posX;
    private double posY;
    private double velocidadeX = 3;
    private boolean jumpscareAtivado = false;
    private AnimationTimer animacao;

    public MascoteFlotuante(Stage janelamascote, String nomeJogador) {
        this.janelamascote = janelamascote;
        this.nomeJogador = nomeJogador;
        this.posX = janelamascote.getX();
        this.posY = janelamascote.getY();
    }

    /**
     * Inicia o movimento do mascote e agenda o jumpscare
     */
    public void iniciarMovimento() {
        // Agenda o jumpscare para um tempo aleatório (15-40 segundos)
        long tempoSusto = 15000 + (long) (Math.random() * 25000);
        new Thread(() -> {
            try {
                Thread.sleep(tempoSusto);
                acionarJumpscare();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Inicia o loop de animação
        animacao = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!jumpscareAtivado) {
                    atualizarMovimento();
                }
            }
        };
        animacao.start();
    }

    /**
     * Atualiza a posição do mascote
     */
    private void atualizarMovimento() {
        posX += velocidadeX;

        Rectangle2D limitesTela = Screen.getPrimary().getVisualBounds();
        double larguraTela = limitesTela.getWidth();

        // Inverte a velocidade ao atingir as bordas
        if (posX > (larguraTela - 200) || posX < 0) {
            velocidadeX = -velocidadeX;
        }

        janelamascote.setX(posX);
        janelamascote.setY(posY);
    }

    /**
     * Aciona o efeito de jumpscare
     */
    private void acionarJumpscare() {
        jumpscareAtivado = true;
        if (animacao != null) {
            animacao.stop();
        }
        janelamascote.close();

        // Cria e exibe a janela de susto
        JumpscareWindow jumpscareWindow = new JumpscareWindow(nomeJogador);
        jumpscareWindow.exibir();
    }
}
