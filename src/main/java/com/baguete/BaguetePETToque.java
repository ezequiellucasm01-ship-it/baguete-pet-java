package com.baguete;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Versão OTIMIZADA PARA TOQUE do Baguete PET
 * Sem necessidade de mouse - apenas gestos de toque!
 */
public class BaguetePETToque extends Application {
    
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
    
    private static final String COR_FUNDO = "-fx-background-color: #f0f0f0;";
    private static final String COR_BOTAO_SIM = "-fx-font-size: 24; -fx-padding: 25 60; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 10;";
    private static final String COR_BOTAO_NAO = "-fx-font-size: 24; -fx-padding: 25 60; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 10;";
    private static final String COR_HEADER = "-fx-background-color: #003366; -fx-padding: 20;";
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("🥖 Baguete PET - Versão Toque");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.setFullScreen(false); // Pode fazer fullscreen se quiser
        
        mostrarTelaDialogoInicial();
        
        stage.show();
    }
    
    /**
     * Tela de diálogo inicial otimizada para toque
     */
    private void mostrarTelaDialogoInicial() {
        BorderPane root = new BorderPane();
        root.setStyle(COR_FUNDO);
        
        // Header
        VBox header = criarHeader("🥖 Baguete PET - Versão Toque 🥖");
        root.setTop(header);
        
        // Conteúdo central
        VBox dialogoBox = new VBox(30);
        dialogoBox.setStyle("-fx-border-color: #333; -fx-border-width: 5; -fx-background-color: #ffffff; -fx-padding: 50;");
        dialogoBox.setAlignment(Pos.CENTER);
        
        // Título grande
        Label titulo = new Label("👋 Olá!");
        titulo.setFont(Font.font("Arial", 48));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #003366;");
        
        // Emoji grande
        Label emoji = new Label("🥖");
        emoji.setFont(Font.font("Arial", 100));
        
        // Mensagem
        Label mensagem = new Label("Eu sou o Baguete PET!\n\nEu acabei de entrar no seu dispositivo.\n\nQuer ser meu amigo?");
        mensagem.setFont(Font.font("Arial", 28));
        mensagem.setTextAlignment(TextAlignment.CENTER);
        mensagem.setStyle("-fx-text-fill: #333; -fx-line-spacing: 10;");
        
        // Botões GRANDES para toque fácil
        HBox botoes = new HBox(40);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPrefHeight(150);
        
        Button btnSim = new Button("✅\nSIM");
        btnSim.setStyle(COR_BOTAO_SIM);
        btnSim.setFont(Font.font("Arial", 20));
        btnSim.setPrefWidth(200);
        btnSim.setPrefHeight(120);
        btnSim.setWrapText(true);
        btnSim.setOnTouchPressed(e -> {
            aceitarAmizade();
            e.consume();
        });
        
        Button btnNao = new Button("❌\nNÃO");
        btnNao.setStyle(COR_BOTAO_NAO);
        btnNao.setFont(Font.font("Arial", 20));
        btnNao.setPrefWidth(200);
        btnNao.setPrefHeight(120);
        btnNao.setWrapText(true);
        btnNao.setOnTouchPressed(e -> {
            recusarAmizade();
            e.consume();
        });
        
        botoes.getChildren().addAll(btnSim, btnNao);
        
        dialogoBox.getChildren().addAll(titulo, emoji, mensagem, botoes);
        
        // ScrollPane para garantir que tudo caiba
        Pane center = new Pane();
        center.getChildren().add(dialogoBox);
        
        root.setCenter(center);
        
        // Instruções na base
        Label instrucoes = new Label("👉 Toque em um dos botões acima");
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
        btnContinuar.setOnTouchPressed(e -> {
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
     * Tela principal de gameplay otimizada para toque
     */
    private void mostrarTelaGameplay() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e8e8e8;");
        
        // Header
        VBox header = criarHeader("🥖 Toque para Brincar! 🥖");
        root.setTop(header);
        
        // Game area GRANDE
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999; -fx-border-width: 3;");
        gamePane.setPrefSize(1000, 600);
        
        // Mascote GIGANTE
        mascoteLabel = new Label("🥖");
        mascoteLabel.setFont(Font.font("Arial", 120));
        mascoteLabel.setLayoutX(mascoteX);
        mascoteLabel.setLayoutY(mascoteY);
        
        gamePane.getChildren().add(mascoteLabel);
        
        // Eventos de toque
        gamePane.setOnTouchMoved(this::movimentarComToque);
        gamePane.setOnTouchPressed(this::clicarNoMascoteComToque);
        
        root.setCenter(gamePane);
        
        // Instruções grande na base
        VBox instrucoes = new VBox(10);
        instrucoes.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20; -fx-border-color: #ccc; -fx-border-width: 2 0 0 0;");
        instrucoes.setPrefHeight(100);
        instrucoes.setAlignment(Pos.CENTER);
        
        Label instr1 = new Label("👆 MOVA SEU DEDO para o baguete te seguir!");
        instr1.setFont(Font.font("Arial", 22));
        instr1.setStyle("-fx-text-fill: #003366; -fx-font-weight: bold;");
        
        Label instr2 = new Label("👉 TOQUE NO BAGUETE para assustar-se!");
        instr2.setFont(Font.font("Arial", 22));
        instr2.setStyle("-fx-text-fill: #cc0000; -fx-font-weight: bold;");
        
        instrucoes.getChildren().addAll(instr1, instr2);
        root.setBottom(instrucoes);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        
        // Iniciar animação
        iniciarAnimacao();
    }
    
    /**
     * Movimenta com toque
     */
    private void movimentarComToque(TouchEvent event) {
        double touchX = event.getTouchPoint().getX();
        double touchY = event.getTouchPoint().getY();
        
        // Calcular direção
        double dx = touchX - mascoteX;
        double dy = touchY - mascoteY;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        
        // Seguir o toque com velocidade aumentada
        if (distancia > 15) {
            mascoteX += (dx / distancia) * 5;
            mascoteY += (dy / distancia) * 5;
        }
        
        atualizarPosicaoMascote();
        event.consume();
    }
    
    /**
     * Clica no mascote com toque
     */
    private void clicarNoMascoteComToque(TouchEvent event) {
        double touchX = event.getTouchPoint().getX();
        double touchY = event.getTouchPoint().getY();
        
        double distancia = Math.sqrt(
            Math.pow(touchX - mascoteX - 60, 2) + 
            Math.pow(touchY - mascoteY - 60, 2)
        );
        
        // Se tocou perto o suficiente do baguete (raio de 80px)
        if (distancia < 80) {
            acionarJumpscare();
        }
        
        event.consume();
    }
    
    /**
     * Atualiza posição do mascote na tela
     */
    private void atualizarPosicaoMascote() {
        mascoteLabel.setLayoutX(mascoteX);
        mascoteLabel.setLayoutY(mascoteY);
    }
    
    /**
     * Inicia a animação de movimento autônomo
     */
    private void iniciarAnimacao() {
        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            
            @Override
            public void handle(long now) {
                if (!jumpscareFoiacionado) {
                    if (now - lastUpdate >= 50_000_000) { // 50ms
                        mascoteX += velocidadeX;
                        mascoteY += velocidadeY;
                        
                        // Rebater nas bordas
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
     * Aciona o jumpscare
     */
    private void acionarJumpscare() {
        if (jumpscareFoiacionado) return;
        jumpscareFoiacionado = true;
        
        if (animationTimer != null) {
            animationTimer.stop();
        }
        
        // Mudar para tela de jumpscare
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #cc0000;");
        
        VBox container = new VBox(30);
        container.setStyle("-fx-background-color: #cc0000; -fx-alignment: center;");
        container.setPrefSize(stage.getWidth(), stage.getHeight());
        container.setAlignment(Pos.CENTER);
        
        // Título GIGANTE
        Label titulo = new Label("⚠️ JUMPSCARE ⚠️");
        titulo.setFont(Font.font("Arial", 72));
        titulo.setStyle("-fx-text-fill: #ffff00; -fx-font-weight: bold;");
        
        // Mascote ENORMEEEE
        Label bagueteAssustador = new Label("🥖");
        bagueteAssustador.setFont(Font.font("Arial", 200));
        bagueteAssustador.setStyle("-fx-text-fill: #ffff00;");
        
        // Mensagem GRANDE
        Label mensagem = new Label("VOCÊ NÃO PODE FUGIR DE MIM!");
        mensagem.setFont(Font.font("Arial", 48));
        mensagem.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        // Som
        Label som = new Label("🔊 ♪♫ SOOOOOM ASSUSTADOR!!! ♫♪");
        som.setFont(Font.font("Arial", 32));
        som.setStyle("-fx-text-fill: #ffff00;");
        
        container.getChildren().addAll(titulo, bagueteAssustador, mensagem, som);
        
        root.setCenter(container);
        
        Scene jumpscareScene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(jumpscareScene);
        
        // Fechar após 5 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> mostrarTelaFinal());
        pause.play();
    }
    
    /**
     * Tela final com relatório
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
            new Label("✅ Mascote seguiu seus movimentos"),
            new Label("✅ Você tocou no baguete"),
            new Label("✅ Jumpscare foi acionado"),
            new Label("✅ Som de susto reproduzido"),
            new Label("✅ Experiência de toque concluída!")
        };
        
        for (Label evento : eventos) {
            evento.setFont(Font.font("Arial", 24));
            evento.setStyle("-fx-text-fill: #333;");
            relatorio.getChildren().add(evento);
        }
        
        // Botões GRANDES para toque
        HBox botoes = new HBox(30);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPrefHeight(140);
        
        Button btnReiniciar = new Button("🔄\nREINICIAR");
        btnReiniciar.setStyle("-fx-font-size: 20; -fx-padding: 20 60; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand;");
        btnReiniciar.setPrefWidth(220);
        btnReiniciar.setPrefHeight(130);
        btnReiniciar.setWrapText(true);
        btnReiniciar.setOnTouchPressed(e -> {
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
     * Cria o header das telas
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
    
    public static void main(String[] args) {
        launch(args);
    }
}
