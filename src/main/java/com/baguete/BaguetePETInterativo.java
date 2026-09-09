package com.baguete;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Versão interativa com toque/cliques do Baguete PET
 * Totalmente controlável com mouse, toque ou teclado
 */
public class BaguetePETInterativo extends Application {
    
    private Stage stage;
    private Pane gamePane;
    private Label mascoteLabel;
    private double mascoteX = 400;
    private double mascoteY = 300;
    private double velocidadeX = 3;
    private double velocidadeY = 1;
    private boolean amizadeAceita = false;
    private boolean jumpscareFoiacionado = false;
    private AnimationTimer animationTimer;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("🥖 Baguete PET - Versão Interativa");
        stage.setWidth(1000);
        stage.setHeight(700);
        
        // Tela inicial
        mostrarTelaDialogoInicial();
        
        stage.show();
    }
    
    /**
     * Tela de diálogo inicial
     */
    private void mostrarTelaDialogoInicial() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");
        
        // Cabeçalho
        VBox header = criarHeader("🥖 Baguete PET - Versão Interativa");
        root.setTop(header);
        
        // Conteúdo central - Diálogo
        VBox dialogoBox = new VBox(20);
        dialogoBox.setStyle("-fx-border-color: #333; -fx-border-width: 3; -fx-background-color: #ffffff; -fx-padding: 30;");
        dialogoBox.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("Olá! Eu sou o Baguete PET 🥖");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setStyle("-fx-font-weight: bold;");
        
        Label mensagem = new Label("Eu acabei de entrar no seu PC.\nQuer ser meu amigo?");
        mensagem.setFont(Font.font("Arial", 18));
        mensagem.setTextAlignment(TextAlignment.CENTER);
        
        // Botões
        HBox botoes = new HBox(30);
        botoes.setAlignment(Pos.CENTER);
        
        Button btnSim = new Button("✅ SIM");
        btnSim.setStyle("-fx-font-size: 18; -fx-padding: 15 50; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand;");
        btnSim.setOnAction(e -> aceitarAmizade());
        
        Button btnNao = new Button("❌ NÃO");
        btnNao.setStyle("-fx-font-size: 18; -fx-padding: 15 50; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand;");
        btnNao.setOnAction(e -> recusarAmizade());
        
        botoes.getChildren().addAll(btnSim, btnNao);
        
        dialogoBox.getChildren().addAll(titulo, mensagem, botoes);
        
        Pane center = new Pane();
        center.getChildren().add(dialogoBox);
        center.setStyle("-fx-alignment: center;");
        
        root.setCenter(center);
        
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
        
        VBox header = criarHeader("⚠️ AVISO DO BAGUETE");
        root.setTop(header);
        
        VBox aviso = new VBox(20);
        aviso.setStyle("-fx-border-color: #cc0000; -fx-border-width: 3; -fx-background-color: #ffffff; -fx-padding: 30;");
        aviso.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("Essa não é uma resposta aceitável! 😈");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #cc0000;");
        
        Label mensagem = new Label("Nós seremos amigos de qualquer forma!\nVocê não tem escolha... 👿");
        mensagem.setFont(Font.font("Arial", 18));
        mensagem.setTextAlignment(TextAlignment.CENTER);
        
        Button btnContinuar = new Button("Continuar...");
        btnContinuar.setStyle("-fx-font-size: 18; -fx-padding: 15 50; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand;");
        btnContinuar.setOnAction(e -> {
            amizadeAceita = true;
            mostrarTelaGameplay();
        });
        
        aviso.getChildren().addAll(titulo, mensagem, btnContinuar);
        
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
        
        // Header
        VBox header = criarHeader("🥖 Baguete PET - Agora interaja comigo!");
        root.setTop(header);
        
        // Game area
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: #ffffff;");
        gamePane.setPrefSize(1000, 600);
        
        // Mascote
        mascoteLabel = new Label("🥖");
        mascoteLabel.setFont(Font.font("Arial", 80));
        mascoteLabel.setLayoutX(mascoteX);
        mascoteLabel.setLayoutY(mascoteY);
        
        gamePane.getChildren().add(mascoteLabel);
        
        // Interação com mouse
        gamePane.setOnMouseMoved(this::movimentarMascote);
        gamePane.setOnMouseClicked(this::clicarNoMascote);
        gamePane.setOnTouchMoved(event -> {
            double x = event.getTouchPoint().getX();
            double y = event.getTouchPoint().getY();
            mascoteX = x - 40;
            mascoteY = y - 40;
            atualizarPosicaoMascote();
        });
        
        root.setCenter(gamePane);
        
        // Instruções
        VBox instrucoes = new VBox(10);
        instrucoes.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 15; -fx-border-color: #ccc; -fx-border-width: 1 0 0 0;");
        instrucoes.setPrefHeight(80);
        
        Label instr = new Label("💡 Mova o mouse ou toque para fazer o baguete te seguir! Clique nele para assustar-se!");
        instr.setFont(Font.font("Arial", 14));
        instr.setStyle("-fx-text-fill: #333;");
        instr.setWrapText(true);
        
        instrucoes.getChildren().add(instr);
        root.setBottom(instrucoes);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        
        // Iniciar animação
        iniciarAnimacao();
    }
    
    /**
     * Movimenta o mascote para seguir o mouse
     */
    private void movimentarMascote(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        
        // Calcular direção
        double dx = mouseX - mascoteX;
        double dy = mouseY - mascoteY;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        
        // Seguir suavemente
        if (distancia > 10) {
            mascoteX += (dx / distancia) * 3;
            mascoteY += (dy / distancia) * 3;
        }
        
        atualizarPosicaoMascote();
    }
    
    /**
     * Quando clica no mascote
     */
    private void clicarNoMascote(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        
        Bounds bounds = mascoteLabel.getBoundsInParent();
        
        // Verificar se clicou no mascote
        if (bounds.contains(mouseX, mouseY)) {
            acionarJumpscare();
        }
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
            @Override
            public void handle(long now) {
                if (!jumpscareFoiacionado && (now / 1_000_000_000) % 15 < 1) {
                    // Movimento aleatório
                    mascoteX += velocidadeX;
                    mascoteY += velocidadeY;
                    
                    // Rebater nas bordas
                    if (mascoteX <= 0 || mascoteX >= gamePane.getWidth() - 80) {
                        velocidadeX = -velocidadeX;
                    }
                    if (mascoteY <= 0 || mascoteY >= gamePane.getHeight() - 80) {
                        velocidadeY = -velocidadeY;
                    }
                    
                    atualizarPosicaoMascote();
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
        
        VBox container = new VBox();
        container.setStyle("-fx-background-color: #cc0000; -fx-alignment: center;");
        container.setPrefSize(stage.getWidth(), stage.getHeight());
        
        // Título
        Label titulo = new Label("⚠️ JUMPSCARE ⚠️");
        titulo.setFont(Font.font("Arial", 48));
        titulo.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        // Mascote assustador
        Label bagueteAssustador = new Label("🥖");
        bagueteAssustador.setFont(Font.font("Arial", 150));
        bagueteAssustador.setStyle("-fx-text-fill: #ffff00;");
        
        // Mensagem
        Label mensagem = new Label("VOCÊ NÃO PODE FUGIR DE MIM!");
        mensagem.setFont(Font.font("Arial", 32));
        mensagem.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        // Som de susto (simulado com aviso)
        Label som = new Label("🔊 ♪♫ SOOOOOM DE SUSTO!!! ♫♪");
        som.setFont(Font.font("Arial", 20));
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
        
        VBox header = criarHeader("🎉 PARABÉNS! Experiência Concluída!");
        root.setTop(header);
        
        VBox relatorio = new VBox(15);
        relatorio.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00cc00; -fx-border-width: 3; -fx-padding: 30;");
        relatorio.setAlignment(Pos.TOP_CENTER);
        
        Label titulo = new Label("📊 RELATÓRIO FINAL");
        titulo.setFont(Font.font("Arial", 28));
        titulo.setStyle("-fx-font-weight: bold; -fx-text-fill: #00cc00;");
        
        Label[] eventos = {
            new Label("✅ Amizade aceita com sucesso!"),
            new Label("✅ Mascote seguiu seus movimentos"),
            new Label("✅ Você clicou no baguete"),
            new Label("✅ Jumpscare foi acionado"),
            new Label("✅ Som de susto reproduzido"),
            new Label("✅ Experiência interativa concluída!")
        };
        
        for (Label evento : eventos) {
            evento.setFont(Font.font("Arial", 16));
            evento.setStyle("-fx-text-fill: #333;");
            relatorio.getChildren().add(evento);
        }
        
        // Botão para reiniciar
        Button btnReiniciar = new Button("🔄 Reiniciar");
        btnReiniciar.setStyle("-fx-font-size: 16; -fx-padding: 10 40; -fx-background-color: #00cc00; -fx-text-fill: white; -fx-cursor: hand;");
        btnReiniciar.setOnAction(e -> {
            jumpscareFoiacionado = false;
            amizadeAceita = false;
            mascoteX = 400;
            mascoteY = 300;
            mostrarTelaDialogoInicial();
        });
        
        Button btnSair = new Button("❌ Sair");
        btnSair.setStyle("-fx-font-size: 16; -fx-padding: 10 40; -fx-background-color: #cc0000; -fx-text-fill: white; -fx-cursor: hand;");
        btnSair.setOnAction(e -> stage.close());
        
        HBox botoes = new HBox(20);
        botoes.setAlignment(Pos.CENTER);
        botoes.getChildren().addAll(btnReiniciar, btnSair);
        
        relatorio.getChildren().add(botoes);
        
        ScrollPane scroll = new ScrollPane(relatorio);
        scroll.setFitToWidth(true);
        
        root.setCenter(scroll);
        
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
    }
    
    /**
     * Cria o header das telas
     */
    private VBox criarHeader(String titulo) {
        VBox header = new VBox();
        header.setStyle("-fx-background-color: #003366; -fx-padding: 15;");
        header.setAlignment(Pos.CENTER);
        
        Label label = new Label(titulo);
        label.setFont(Font.font("Arial", 28));
        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        
        header.getChildren().add(label);
        
        return header;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
