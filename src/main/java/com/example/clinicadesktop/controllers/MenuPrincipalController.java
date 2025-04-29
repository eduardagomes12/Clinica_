package com.example.clinicadesktop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuPrincipalController {

    @Autowired
    private ApplicationContext context;

    @FXML
    private VBox conteudoContainer;

    @FXML
    private ImageView logoImageView;

    @FXML
    public void initialize() {
        try {
            logoImageView.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            System.out.println("Logo não encontrado.");
        }
    }

    private void carregarConteudo(String caminhoFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFxml));
            loader.setControllerFactory(context::getBean);
            Node conteudo = loader.load();
            conteudoContainer.getChildren().setAll(conteudo);
        } catch (IOException e) {
            mostrarErro("Erro ao carregar a página", e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Ações dos botões
    @FXML
    private void abrirRegistarCliente() {
        carregarConteudo("/views/registarCliente.fxml");
    }

    @FXML
    private void abrirListarClientes() {
        carregarConteudo("/views/listarClientes.fxml");
    }

    @FXML
    private void abrirRegistarAnimal() {
        carregarConteudo("/views/registarAnimal.fxml");
    }

    @FXML
    private void abrirListarAnimais() {
        carregarConteudo("/views/listarAnimais.fxml");
    }

    @FXML
    private void abrirMarcarConsulta() {
        carregarConteudo("/views/registarConsulta.fxml");
    }

    @FXML
    private void abrirListarConsultas() {
        carregarConteudo("/views/listarConsultas.fxml");
    }
}
