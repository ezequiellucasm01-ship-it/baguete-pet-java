package com.baguetepet;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class BagueteGame extends Application {

    private double xOffset = 0, yOffset = 0;
    private String nomeUsuario = "Amigo";

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);

        Pane petRoot = new Pane();
        petRoot.setStyle("-fx-background-color: transparent;");

        Button petBaguete = new Button("🥖 Baguete-Pet");
        petBaguete.setStyle("-fx-background-radius: 20; -fx-padding: 12; -fx-background-color: #1a1a1a; " +
                "-fx-text-fill: #ff3333; -fx-font-weight: bold; -fx-border-color: #ff3333; -fx-border-width: 2;");
        petBaguete.setPrefSize(140, 50);
        petRoot.getChildren().add(petBaguete);

        petBaguete.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        petBaguete.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - xOffset);
            primaryStage.setY(e.getScreenY() - yOffset);
        });

        Scene petScene = new Scene(petRoot, 140, 50);
        petScene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(petScene);
        
        primaryStage.setX((bounds.getWidth() - 140) / 2);
        primaryStage.setY((bounds.getHeight() - 50) / 2);
        primaryStage.show();

        Thread dataThread = new Thread(() -> {
            this.nomeUsuario = obterNomeDoSistema();
            
            Platform.runLater(() -> {
                PauseTransition delayInicial = new PauseTransition(Duration.seconds(4));
                delayInicial.setOnFinished(event -> abrirInterfaceInterrogatorio());
                delayInicial.play();
            });
        });
        dataThread.setDaemon(true);
        dataThread.start();
    }

    private void abrirInterfaceInterrogatorio() {
        Stage quizStage = new Stage();
        quizStage.initStyle(StageStyle.UNDECORATED);
        quizStage.setAlwaysOnTop(true);

        VBox layout = new VBox(15);
        layout.setStyle("-fx-background-color: #0d0d0d; -fx-padding: 25; -fx-border-color: #ff3333; -fx-border-width: 2;");
        layout.setAlignment(Pos.CENTER);

        Label lblHeader = new Label("Eu vejo você, " + nomeUsuario + ".");
        lblHeader.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");

        Label lblPergunta = new Label("Você quer jogar um jogo comigo?");
        lblPergunta.setStyle("-fx-text-fill: #b3b3b3; -fx-font-size: 14px; -fx-font-family: 'Courier New';");

        HBox botoesBox = new HBox(25);
        botoesBox.setAlignment(Pos.CENTER);

        Button btnSim = new Button("Sim");
        Button btnNao = new Button("Não");

        btnSim.setStyle("-fx-background-color: #1a1a1a; -fx-text-fill: #00ff00; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-pref-width: 90;");
        btnNao.setStyle("-fx-background-color: #1a1a1a; -fx-text-fill: #ff3333; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-pref-width: 90;");

        btnNao.setOnMouseEntered(e -> {
            Random rng = new Random();
            btnNao.setTranslateX(rng.nextInt(160) - 80);
            btnNao.setTranslateY(rng.nextInt(120) - 60);
        });

        btnSim.setOnAction(e -> {
            quizStage.close();
            iniciarSimulacaoTerminal();
        });

        botoesBox.getChildren().addAll(btnSim, btnNao);
        layout.getChildren().addAll(lblHeader, lblPergunta, botoesBox);

        Scene quizScene = new Scene(layout, 380, 220);
        quizStage.setScene(quizScene);
        quizStage.show();
    }

    private void iniciarSimulacaoTerminal() {
        Stage terminalStage = new Stage();
        terminalStage.initStyle(StageStyle.UNDECORATED);
        terminalStage.setAlwaysOnTop(true);

        VBox layoutTerminal = new VBox(10);
        layoutTerminal.setStyle("-fx-background-color: #000000; -fx-padding: 20; -fx-border-color: #00ff00; -fx-border-width: 2;");
        layoutTerminal.setAlignment(Pos.TOP_LEFT);

        Label lblLogs = new Label();
        lblLogs.setStyle("-fx-text-fill: #00ff00; -fx-font-family: 'Courier New'; -fx-font-size: 13px;");
        layoutTerminal.getChildren().add(lblLogs);

        Scene terminalScene = new Scene(layoutTerminal, 450, 300);
        terminalStage.setScene(terminalScene);
        terminalStage.show();

        String[] linhasLog = {
                "> Inicializando subsistemas...",
                "> Carregando módulos do Baguete-Pet...",
                "> Varrendo permissões do diretório local...",
                "> Conexão estabelecida com a interface gráfica.",
                "> Nome do host identificado: " + nomeUsuario,
                "> O jogo terminou. Obrigado por participar."
        };

        StringBuilder sb = new StringBuilder();
        Timeline timeline = new Timeline();

        for (int i = 0; i < linhasLog.length; i++) {
            final String linha = linhasLog[i] + "\n";
            KeyFrame frame = new KeyFrame(Duration.seconds(0.8 * (i + 1)), e -> {
                sb.append(linha);
                lblLogs.setText(sb.toString());
            });
            timeline.getKeyFrames().add(frame);
        }

        timeline.setOnFinished(e -> {
            PauseTransition transicao = new PauseTransition(Duration.seconds(1.5));
            transicao.setOnFinished(ev -> {
                terminalStage.close();
                exibirDesfechoVisual();
            });
            transicao.play();
        });

        timeline.play();
    }

    private void exibirDesfechoVisual() {
        Stage finalStage = new Stage();
        finalStage.initStyle(StageStyle.TRANSPARENT);
        finalStage.setFullScreen(true);
        finalStage.setAlwaysOnTop(true);

        VBox finalLayout = new VBox();
        finalLayout.setStyle("-fx-background-color: #000000;");
        finalLayout.setAlignment(Pos.CENTER);

        Label lblFinal = new Label("FIM DE JOGO, " + nomeUsuario.toUpperCase() + ".");
        lblFinal.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 45px; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");

        finalLayout.getChildren().add(lblFinal);
        Scene finalScene = new Scene(finalLayout, 800, 600);
        finalScene.setFill(Color.BLACK);

        finalStage.setScene(finalScene);
        finalStage.show();

        PauseTransition fim = new PauseTransition(Duration.seconds(3));
        fim.setOnFinished(e -> {
            finalStage.close();
            Platform.exit();
            System.exit(0);
        });
        fim.play();
    }

    private String obterNomeDoSistema() {
        try {
            String nomeSO = System.getProperty("user.name");
            if (nomeSO != null && !nomeSO.isEmpty()) {
                return nomeSO;
            }

            String localData = System.getenv("LOCALAPPDATA");
            if (localData != null) {
                File arqPref = new File(localData + "\\Google\\Chrome\\User Data\\Default\\Preferences");
                if (arqPref.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(arqPref))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            if (linha.contains("\"name\"")) {
                                int idx = linha.indexOf("\"name\"");
                                String parte = linha.substring(idx + 7);
                                return parte.split("\"");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Jogador";
    }

    public static void main(String[] args) {
        launch(args);
    }
  }
