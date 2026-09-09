package com.baguete;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import javafx.screen.Screen;

/**
 * Aplicação principal do Baguete PET
 * Um adorável mascote de desktop com surpresas
 */
public class BaguetePET extends Application {
    private static String nomeJogador;

    public static void main(String[] args) {
        // Obtém o nome do jogador antes de iniciar
        nomeJogador = GoogleProfileReader.obterNomeJogador();
        launch(args);
    }

    @Override
    public void start(Stage palcoInicial) {
        // Exibe o diálogo de pergunta inicial
        exibirDialogoInicial(palcoInicial);
    }

    /**
     * Exibe o diálogo inicial perguntando sobre amizade
     */
    private void exibirDialogoInicial(Stage palco) {
        palco.setWidth(400);
        palco.setHeight(180);
        palco.initStyle(StageStyle.UNDECORATED);
        palco.setAlwaysOnTop(true);

        // Centraliza na tela
        Rectangle2D limitesTela = Screen.getPrimary().getVisualBounds();
        palco.setX((limitesTela.getWidth() - 400) / 2);
        palco.setY((limitesTela.getHeight() - 180) / 2);

        // Conteúdo
        VBox conteudo = new VBox(20);
        conteudo.setStyle("-fx-background-color: #ececec; -fx-padding: 20;");

        Label textoPrincipal = new Label(
            String.format("Olá, %s!\nEu acabei de entrar.\nQuer ser meu amigo?", nomeJogador)
        );
        textoPrincipal.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-text-fill: black; -fx-text-alignment: center;");

        HBox botoesContainer = new HBox(40);
        botoesContainer.setStyle("-fx-alignment: center; -fx-padding: 10 0 0 0;");

        Button botaoSim = new Button("Sim");
        botaoSim.setStyle("-fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 8 24 8 24; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        botaoSim.setOnAction(e -> {
            palco.close();
            iniciarMascoteFlotuante();
        });

        Button botaoNao = new Button("Não");
        botaoNao.setStyle("-fx-font-size: 10; -fx-padding: 8 24 8 24;");
        botaoNao.setOnMouseEntered(e -> {
            // Faz o botão fugir do mouse
            botaoNao.setLayoutX(Math.random() * 300);
            botaoNao.setLayoutY(Math.random() * 80 + 70);
        });
        botaoNao.setOnAction(e -> {
            Alert aviso = new Alert(Alert.AlertType.WARNING);
            aviso.setTitle("Baguete");
            aviso.setHeaderText(null);
            aviso.setContentText("Essa não é uma resposta aceitável. Nós seremos amigos.");
            aviso.showAndWait();
        });

        botoesContainer.getChildren().addAll(botaoSim, botaoNao);
        conteudo.getChildren().addAll(textoPrincipal, botoesContainer);

        Scene cena = new Scene(conteudo);
        palco.setScene(cena);
        palco.show();
    }

    /**
     * Inicia a janela do mascote que se move pela tela
     */
    private void iniciarMascoteFlotuante() {
        Stage janelamascote = new Stage();
        janelamascote.initStyle(StageStyle.TRANSPARENT);
        janelamascote.setAlwaysOnTop(true);
        janelamascote.setWidth(200);
        janelamascote.setHeight(200);

        // Posição inicial aleatória
        Rectangle2D limitesTela = Screen.getPrimary().getVisualBounds();
        double x = Math.random() * (limitesTela.getWidth() - 200);
        double y = Math.random() * (limitesTela.getHeight() - 200);
        janelamascote.setX(x);
        janelamascote.setY(y);

        // Cria a cena com a imagem do mascote
        VBox raiz = new VBox();
        raiz.setStyle("-fx-background-color: transparent;");

        ImageView imagemMascote = new ImageView();
        try {
            Image imagem = new Image(getClass().getResourceAsStream("/mascote.png"));
            imagemMascote.setImage(imagem);
            imagemMascote.setFitWidth(200);
            imagemMascote.setFitHeight(200);
            imagemMascote.setPreserveRatio(true);
        } catch (Exception e) {
            Label placeholder = new Label("[Baguete Normal]");
            placeholder.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
            placeholder.setPrefWidth(200);
            placeholder.setPrefHeight(200);
            raiz.getChildren().add(placeholder);
        }

        raiz.getChildren().add(imagemMascote);

        Scene cena = new Scene(raiz);
        cena.setFill(Color.TRANSPARENT);
        janelamascote.setScene(cena);
        janelamascote.show();

        // Inicia o movimento
        MascoteFlotuante mascote = new MascoteFlotuante(janelamascote, nomeJogador);
        mascote.iniciarMovimento();
    }
}
