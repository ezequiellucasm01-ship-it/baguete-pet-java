package com.baguete;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.ByteArrayInputStream;

/**
 * Versão com SOM REAL gerado em Java
 * Som de susto, clique e vitória gerados dinamicamente!
 */
public class BaguetePETComSom extends Application {
    
    private Stage stage;
    private Pane gamePane;
    private Label mascoteLabel;
    private double mascoteX = 400;
    private double mascoteY = 250;
    private double velocidadeX = 2;
    private double velocidadeY = 1.5;
    private boolean amizadeAceita = false;
    private boolean jumpscareFoiacionado = false;
    private AnimationTimer animationTimer;
    private long ultimoToque = 0;
    
    private static final String COR_FUNDO = "-fx-background-color: #f0f0f0;";
    private static final String COR_BOTAO_SIM = "-fx-font-size: 24; -fx-padding: 25 60; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 10; -fx-font-weight: bold;";
    private static final String COR_BOTAO_NAO = "-fx-font-size: 24; -fx-padding: 25 60; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 10; -fx-font-weight: bold;";
    private static final String COR_HEADER = "-fx-background-color: #003366; -fx-padding: 20;";
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("🥖 Baguete PET - Com Som Real!");
        stage.setWidth(1000);
        stage.setHeight(800);
        
        System.out.println("[SISTEMA] BaguetePETComSom iniciado!");
        System.out.println("[SISTEMA] Sons reais gerados em Java!");
        
        mostrarTelaDialogoInicial();
        
        stage.show();
    }
    
    /**
     * Tela de diálogo inicial
     */
    private void mostrarTelaDialogoInicial() {
        BorderPane root = new BorderPane();
        root.setStyle(COR_FUNDO);
        
        VBox header = criarHeader("🥖 Baguete PET - Com Som Real! 🔊");
        root.setTop(header);
        
        VBox dialogoBox = new VBox(30);
        dialogoBox.setStyle("-fx-border-color: #333; -fx-border-width: 5; -fx-background-color: #ffffff; -fx-padding: 50;");
        dialogoBox.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("👋 Olá!");
        titulo.setFont(Font.font("Arial", 48));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #003366;");
        
        Label emoji = new Label("🥖");
        emoji.setFont(Font.font("Arial", 100));
        
        Label mensagem = new Label("Eu sou o Baguete PET!\n\nEu acabei de entrar no seu dispositivo.\n\nQuer ser meu amigo?");
        mensagem.setFont(Font.font("Arial", 28));
        mensagem.setTextAlignment(TextAlignment.CENTER);
        mensagem.setStyle("-fx-text-fill: #333; -fx-line-spacing: 10;");
        
        Label instrucao = new Label("(🔊 Som ativado! Prepare-se para o susto!)");
        instrucao.setFont(Font.font("Arial", 16));
        instrucao.setStyle("-fx-text-fill: #cc0000; -fx-font-weight: bold;");
        
        HBox botoes = new HBox(40);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPrefHeight(150);
        
        Button btnSim = new Button("✅\nSIM");
        btnSim.setStyle(COR_BOTAO_SIM);
        btnSim.setFont(Font.font("Arial", 20));
        btnSim.setPrefWidth(200);
        btnSim.setPrefHeight(120);
        btnSim.setWrapText(true);
        btnSim.setOnMouseClicked(e -> {
            tocarSomClique();
            System.out.println("[EVENTO] Clique em SIM");
            aceitarAmizade();
        });
        btnSim.setOnTouchPressed(e -> {
            tocarSomClique();
            System.out.println("[EVENTO] Toque em SIM");
            aceitarAmizade();
            e.consume();
        });
        
        Button btnNao = new Button("❌\nNÃO");
        btnNao.setStyle(COR_BOTAO_NAO);
        btnNao.setFont(Font.font("Arial", 20));
        btnNao.setPrefWidth(200);
        btnNao.setPrefHeight(120);
        btnNao.setWrapText(true);
        btnNao.setOnMouseClicked(e -> {
            tocarSomClique();
            recusarAmizade();
        });
        btnNao.setOnTouchPressed(e -> {
            tocarSomClique();
            recusarAmizade();
            e.consume();
        });
        
        botoes.getChildren().addAll(btnSim, btnNao);
        
        dialogoBox.getChildren().addAll(titulo, emoji, mensagem, instrucao, botoes);
        
        Pane center = new Pane();
        center.getChildren().add(dialogoBox);
        
        root.setCenter(center);
        
        Label instrucoes = new Label("👉 Toque ou clique - COM SOM! 🔊");
        instrucoes.setFont(Font.font("Arial", 18));
        instrucoes.setStyle("-fx-background-color: #cccccc; -fx-padding: 15; -fx-text-fill: #333;");
        
        root.setBottom(instrucoes);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }
    
    /**
     * Quando aceita amizade
     */
    private void aceitarAmizade() {
        amizadeAceita = true;
        mostrarTelaGameplay();
    }
    
    /**
     * Quando recusa amizade
     */
    private void recusarAmizade() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ffcccc;");
        
        VBox header = criarHeader("⚠️ AVISO DO BAGUETE ⚠️");
        root.setTop(header);
        
        VBox aviso = new VBox(30);
        aviso.setStyle("-fx-border-color: #cc0000; -fx-border-width: 5; -fx-background-color: #ffffff; -fx-padding: 50;");
        aviso.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("😈 ERRO!");
        titulo.setFont(Font.font("Arial", 44));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #cc0000;");
        
        Label emoji = new Label("😱😈😱");
        emoji.setFont(Font.font("Arial", 80));
        
        Label mensagem = new Label("Essa não é uma resposta aceitável!\n\nNós SEREMOS amigos de qualquer forma!\n\nVocê não tem escolha... 👿");
        mensagem.setFont(Font.font("Arial", 26));
        mensagem.setTextAlignment(TextAlignment.CENTER);
        mensagem.setStyle("-fx-text-fill: #333; -fx-line-spacing: 10;");
        
        Button btnContinuar = new Button("CONTINUAR\n(sem opção)");
        btnContinuar.setStyle(COR_BOTAO_NAO);
        btnContinuar.setFont(Font.font("Arial", 20));
        btnContinuar.setPrefWidth(250);
        btnContinuar.setPrefHeight(120);
        btnContinuar.setWrapText(true);
        btnContinuar.setOnMouseClicked(e -> {
            tocarSomClique();
            amizadeAceita = true;
            mostrarTelaGameplay();
        });
        btnContinuar.setOnTouchPressed(e -> {
            tocarSomClique();
            amizadeAceita = true;
            mostrarTelaGameplay();
            e.consume();
        });
        
        aviso.getChildren().addAll(titulo, emoji, mensagem, btnContinuar);
        
        Pane center = new Pane();
        center.getChildren().add(aviso);
        
        root.setCenter(center);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }
    
    /**
     * Tela principal de gameplay
     */
    private void mostrarTelaGameplay() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e8e8e8;");
        
        VBox header = criarHeader("🥖 Toque ou Use o Mouse! 🔊");
        root.setTop(header);
        
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999; -fx-border-width: 3;");
        gamePane.setPrefSize(1000, 600);
        
        mascoteLabel = new Label("🥖");
        mascoteLabel.setFont(Font.font("Arial", 120));
        mascoteLabel.setLayoutX(mascoteX);
        mascoteLabel.setLayoutY(mascoteY);
        
        gamePane.getChildren().add(mascoteLabel);
        
        gamePane.setOnMouseMoved(this::movimentarComMouse);
        gamePane.setOnMousePressed(this::clicarComMouse);
        
        gamePane.setOnTouchMoved(this::movimentarComToque);
        gamePane.setOnTouchPressed(this::clicarComToque);
        
        root.setCenter(gamePane);
        
        VBox instrucoes = new VBox(10);
        instrucoes.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20; -fx-border-color: #ccc; -fx-border-width: 2 0 0 0;");
        instrucoes.setPrefHeight(110);
        instrucoes.setAlignment(Pos.CENTER);
        
        Label instr1 = new Label("👆 TOQUE ou 🖱️ MOVA O MOUSE para o baguete te seguir!");
        instr1.setFont(Font.font("Arial", 22));
        instr1.setStyle("-fx-text-fill: #003366; -fx-font-weight: bold;");
        
        Label instr2 = new Label("👉 TOQUE ou 🖱️ CLIQUE NO BAGUETE para assustar-se! 🔊 (SOM ASSUSTADOR!)");
        instr2.setFont(Font.font("Arial", 22));
        instr2.setStyle("-fx-text-fill: #cc0000; -fx-font-weight: bold;");
        
        instrucoes.getChildren().addAll(instr1, instr2);
        root.setBottom(instrucoes);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        
        iniciarAnimacao();
    }
    
    /**
     * Movimenta com mouse
     */
    private void movimentarComMouse(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        
        double dx = mouseX - mascoteX;
        double dy = mouseY - mascoteY;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        
        if (distancia > 15) {
            mascoteX += (dx / distancia) * 4;
            mascoteY += (dy / distancia) * 4;
        }
        
        atualizarPosicaoMascote();
    }
    
    /**
     * Clica com mouse
     */
    private void clicarComMouse(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        
        double distancia = Math.sqrt(
            Math.pow(mouseX - mascoteX - 60, 2) + 
            Math.pow(mouseY - mascoteY - 60, 2)
        );
        
        if (distancia < 80) {
            System.out.println("[EVENTO] ✅ CLIQUE NO BAGUETE!");
            acionarJumpscare();
        }
    }
    
    /**
     * Movimenta com toque
     */
    private void movimentarComToque(TouchEvent event) {
        double touchX = event.getTouchPoint().getX();
        double touchY = event.getTouchPoint().getY();
        
        double dx = touchX - mascoteX;
        double dy = touchY - mascoteY;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        
        if (distancia > 15) {
            mascoteX += (dx / distancia) * 5;
            mascoteY += (dy / distancia) * 5;
        }
        
        atualizarPosicaoMascote();
        event.consume();
    }
    
    /**
     * Clica com toque
     */
    private void clicarComToque(TouchEvent event) {
        long agora = System.currentTimeMillis();
        
        if (agora - ultimoToque < 500) {
            event.consume();
            return;
        }
        ultimoToque = agora;
        
        double touchX = event.getTouchPoint().getX();
        double touchY = event.getTouchPoint().getY();
        
        double distancia = Math.sqrt(
            Math.pow(touchX - mascoteX - 60, 2) + 
            Math.pow(touchY - mascoteY - 60, 2)
        );
        
        if (distancia < 80) {
            System.out.println("[EVENTO] ✅ TOQUE NO BAGUETE!");
            acionarJumpscare();
        }
        
        event.consume();
    }
    
    /**
     * Atualiza posição
     */
    private void atualizarPosicaoMascote() {
        mascoteLabel.setLayoutX(mascoteX);
        mascoteLabel.setLayoutY(mascoteY);
    }
    
    /**
     * Inicia animação
     */
    private void iniciarAnimacao() {
        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            
            @Override
            public void handle(long now) {
                if (!jumpscareFoiacionado) {
                    if (now - lastUpdate >= 50_000_000) {
                        mascoteX += velocidadeX;
                        mascoteY += velocidadeY;
                        
                        if (mascoteX <= 0 || mascoteX >= gamePane.getWidth() - 120) {
                            velocidadeX = -velocidadeX;
                        }
                        if (mascoteY <= 0 || mascoteY >= gamePane.getHeight() - 120) {
                            velocidadeY = -velocidadeY;
                        }
                        
                        atualizarPosicaoMascote();
                        lastUpdate = now;
                    }
                }
            }
        };
        animationTimer.start();
    }
    
    /**
     * Aciona o jumpscare COM SOM
     */
    private void acionarJumpscare() {
        if (jumpscareFoiacionado) return;
        jumpscareFoiacionado = true;
        
        System.out.println("[SISTEMA] 🎉 JUMPSCARE ACIONADO!");
        System.out.println("[SOM] Tocando som de susto...");
        
        // Tocar som de susto em thread separada
        new Thread(this::tocarSomSusto).start();
        
        if (animationTimer != null) {
            animationTimer.stop();
        }
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #cc0000;");
        
        VBox container = new VBox(30);
        container.setStyle("-fx-background-color: #cc0000; -fx-alignment: center;");
        container.setPrefSize(stage.getWidth(), stage.getHeight());
        container.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("⚠️ JUMPSCARE ⚠️");
        titulo.setFont(Font.font("Arial", 72));
        titulo.setStyle("-fx-text-fill: #ffff00; -fx-font-weight: bold;");
        
        Label bagueteAssustador = new Label("🥖");
        bagueteAssustador.setFont(Font.font("Arial", 200));
        bagueteAssustador.setStyle("-fx-text-fill: #ffff00;");
        
        Label mensagem = new Label("VOCÊ NÃO PODE FUGIR DE MIM!");
        mensagem.setFont(Font.font("Arial", 48));
        mensagem.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        Label som = new Label("🔊 ♪♫ SOOOOOM ASSUSTADOR!!! ♫♪");
        som.setFont(Font.font("Arial", 32));
        som.setStyle("-fx-text-fill: #ffff00;");
        
        container.getChildren().addAll(titulo, bagueteAssustador, mensagem, som);
        
        root.setCenter(container);
        
        Scene jumpscareScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(jumpscareScene);
        
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> mostrarTelaFinal());
        pause.play();
    }
    
    /**
     * Tela final
     */
    private void mostrarTelaFinal() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ccffcc;");
        
        VBox header = criarHeader("🎉 PARABÉNS! 🎉");
        root.setTop(header);
        
        VBox relatorio = new VBox(20);
        relatorio.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00cc00; -fx-border-width: 5; -fx-padding: 40;");
        relatorio.setAlignment(Pos.TOP_CENTER);
        
        Label titulo = new Label("📊 RELATÓRIO FINAL");
        titulo.setFont(Font.font("Arial", 40));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #00cc00;");
        
        Label[] eventos = {
            new Label("✅ Amizade aceita com sucesso!"),
            new Label("✅ Mascote seguiu seus movimentos!"),
            new Label("✅ Você tocou/clicou no baguete!"),
            new Label("✅ Jumpscare foi acionado!"),
            new Label("✅ 🔊 SOM DE SUSTO reproduzido!"),
            new Label("✅ Experiência com som concluída!")
        };
        
        for (Label evento : eventos) {
            evento.setFont(Font.font("Arial", 24));
            evento.setStyle("-fx-text-fill: #333;");
            relatorio.getChildren().add(evento);
        }
        
        HBox botoes = new HBox(30);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPrefHeight(140);
        
        Button btnReiniciar = new Button("🔄\nREINICIAR");
        btnReiniciar.setStyle("-fx-font-size: 20; -fx-padding: 20 60; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand;");
        btnReiniciar.setPrefWidth(220);
        btnReiniciar.setPrefHeight(130);
        btnReiniciar.setWrapText(true);
        btnReiniciar.setOnMouseClicked(e -> {
            tocarSomVitoria();
            jumpscareFoiacionado = false;
            amizadeAceita = false;
            mascoteX = 400;
            mascoteY = 250;
            mostrarTelaDialogoInicial();
        });
        btnReiniciar.setOnTouchPressed(e -> {
            tocarSomVitoria();
            jumpscareFoiacionado = false;
            amizadeAceita = false;
            mascoteX = 400;
            mascoteY = 250;
            mostrarTelaDialogoInicial();
            e.consume();
        });
        
        Button btnSair = new Button("❌\nSAIR");
        btnSair.setStyle("-fx-font-size: 20; -fx-padding: 20 60; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand;");
        btnSair.setPrefWidth(220);
        btnSair.setPrefHeight(130);
        btnSair.setWrapText(true);
        btnSair.setOnMouseClicked(e -> stage.close());
        btnSair.setOnTouchPressed(e -> {
            stage.close();
            e.consume();
        });
        
        botoes.getChildren().addAll(btnReiniciar, btnSair);
        
        relatorio.getChildren().add(botoes);
        
        root.setCenter(relatorio);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }
    
    /**
     * Cria header
     */
    private VBox criarHeader(String titulo) {
        VBox header = new VBox();
        header.setStyle(COR_HEADER);
        header.setAlignment(Pos.CENTER);
        
        Label label = new Label(titulo);
        label.setFont(Font.font("Arial", 36));
        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        header.getChildren().add(label);
        
        return header;
    }
    
    // ==================== SONS GERADOS EM JAVA ====================
    
    /**
     * Som de clique (bip curto)
     */
    private void tocarSomClique() {
        new Thread(() -> {
            try {
                // Tom de 1000 Hz por 100ms
                tocarTom(1000, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    /**
     * Som de susto (descida assustadora)
     */
    private void tocarSomSusto() {
        try {
            System.out.println("[SOM] Iniciando som de susto...");
            
            // Sequência de tons assustadores
            // Tom baixo e escuro que desce
            tocarTom(150, 200);  // Tom baixo
            tocarTom(100, 150);  // Tom mais baixo
            tocarTom(75, 100);   // Tom muito baixo
            
            // Pausa
            Thread.sleep(100);
            
            // Som agudo de susto
            tocarTom(800, 200);  // Tom agudo
            tocarTom(1200, 150); // Tom mais agudo
            tocarTom(800, 200);  // Tom agudo novamente
            
            System.out.println("[SOM] Som de susto concluído!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Som de vitória (ascending tones)
     */
    private void tocarSomVitoria() {
        new Thread(() -> {
            try {
                System.out.println("[SOM] Tocando som de vitória...");
                
                // Tons ascendentes (vitória)
                tocarTom(523, 200);  // C (Dó)
                tocarTom(659, 200);  // E (Mi)
                tocarTom(784, 200);  // G (Sol)
                tocarTom(1047, 400); // C (Dó agudo) - mais longo
                
                System.out.println("[SOM] Som de vitória concluído!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    /**
     * Gera um tom puro usando SourceDataLine
     * @param frequencia Frequência em Hz
     * @param duracao Duração em milissegundos
     */
    private void tocarTom(int frequencia, int duracao) throws Exception {
        int sampleRate = 44100;
        int samples = (int) ((long) sampleRate * duracao / 1000);
        byte[] tone = new byte[samples];
        
        // Gerar forma de onda senoidal
        for (int i = 0; i < samples; i++) {
            double angle = 2.0 * Math.PI * frequencia * i / sampleRate;
            // Envelope para suavizar início e fim
            double envelope = Math.min(1.0, Math.min(i / 100.0, (samples - i) / 100.0));
            tone[i] = (byte) (Math.sin(angle) * 127 * envelope);
        }
        
        // Criar AudioFormat
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
        
        // Criar AudioInputStream
        AudioInputStream audioInputStream = new AudioInputStream(
            new ByteArrayInputStream(tone),
            format,
            samples
        );
        
        // Obter SourceDataLine
        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(format);
        sourceDataLine.open(format);
        sourceDataLine.start();
        
        // Tocar som
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = audioInputStream.read(buffer)) != -1) {
            sourceDataLine.write(buffer, 0, bytesRead);
        }
        
        sourceDataLine.drain();
        sourceDataLine.close();
        audioInputStream.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
