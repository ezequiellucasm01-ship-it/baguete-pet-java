package com.baguete;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.screen.Screen;

/**
 * Janela de jumpscare que aparece após o tempo aleatório
 */
public class JumpscareWindow {
    private final String nomeJogador;
    private static final String COR_FUNDO = "#700000";

    public JumpscareWindow(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    /**
     * Exibe a janela de susto
     */
    public void exibir() {
        Stage telasusto = new Stage();
        telasusto.initStyle(StageStyle.UNDECORATED);
        telasusto.setAlwaysOnTop(true);
        telasusto.setWidth(500);
        telasusto.setHeight(500);

        // Centraliza na tela
        Rectangle2D limitesTela = Screen.getPrimary().getVisualBounds();
        telasusto.setX((limitesTela.getWidth() - 500) / 2);
        telasusto.setY((limitesTela.getHeight() - 500) / 2);

        // Cria o conteúdo
        VBox conteudo = new VBox(10);
        conteudo.setStyle("-fx-background-color: " + COR_FUNDO + "; -fx-alignment: center; -fx-padding: 20;");

        // Tenta carregar a imagem de susto
        ImageView imagemsusto = new ImageView();
        try {
            Image imagem = new Image(getClass().getResourceAsStream("/susto.png"));
            imagemsusto.setImage(imagem);
            imagemsusto.setFitWidth(350);
            imagemsusto.setFitHeight(350);
            imagemsusto.setPreserveRatio(true);
        } catch (Exception e) {
            Label placeholder = new Label("⚠️ ERRO FATAL ⚠️");
            placeholder.setStyle("-fx-font-size: 24; -fx-text-fill: white; -fx-font-weight: bold;");
            conteudo.getChildren().add(placeholder);
        }
        conteudo.getChildren().add(imagemsusto);

        // Adiciona o texto assustador
        Label textosusto = new Label(
            String.format("VOCÊ NÃO PODE FUGIR DE MIM, %s.", nomeJogador.toUpperCase())
        );
        textosusto.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Courier New';");
        conteudo.getChildren().add(textosusto);

        Scene cena = new Scene(conteudo);
        cena.setFill(Color.web(COR_FUNDO));
        telasusto.setScene(cena);
        telasusto.show();

        // Reproduz o som de susto
        reproduzirSom();

        // Fecha automaticamente após 5 segundos
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                telasusto.close();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Reproduz o som de susto do sistema
     */
    private void reproduzirSom() {
        new Thread(() -> {
            try {
                String sistemaOperacional = System.getProperty("os.name").toLowerCase();
                if (sistemaOperacional.contains("win")) {
                    // Windows
                    Runtime.getRuntime().exec(new String[]{
                        "powershell", "-c",
                        "(New-Object Media.SoundPlayer 'C:\\Windows\\Media\\chord.wav').PlaySync()"
                    }).waitFor();
                } else if (sistemaOperacional.contains("linux")) {
                    // Linux
                    Runtime.getRuntime().exec(new String[]{
                        "bash", "-c",
                        "aplay /usr/share/sounds/alsa/Rear_Center.wav"
                    }).waitFor();
                } else if (sistemaOperacional.contains("mac")) {
                    // macOS
                    Runtime.getRuntime().exec(new String[]{
                        "afplay", "/System/Library/Sounds/Alarm.aiff"
                    }).waitFor();
                }
            } catch (Exception e) {
                System.err.println("Erro ao reproduzir som: " + e.getMessage());
            }
        }).start();
    }
}
