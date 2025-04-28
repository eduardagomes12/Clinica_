package com.example.clinicadesktop.controllers;
/*
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuPrincipalController {

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private void abrirRegistarCliente(ActionEvent event) {
        carregarNovoEcrã(event, "/views/registarCliente.fxml", "Registar Cliente");
    }

    @FXML
    private void abrirListarClientes(ActionEvent event) {
        carregarNovoEcrã(event, "/views/listarClientes.fxml", "Listar Clientes");
    }

    @FXML
    private void abrirRegistarAnimal(ActionEvent event) {
        carregarNovoEcrã(event, "/views/registarAnimal.fxml", "Registar Animal");
    }

    @FXML
    private void abrirListarAnimais(ActionEvent event) {
        carregarNovoEcrã(event, "/views/listarAnimais.fxml", "Listar Animais");
    }

    @FXML
    private void abrirMarcarConsulta(ActionEvent event) {
        carregarNovoEcrã(event, "/views/registarConsulta.fxml", "Marcar Consulta");
    }


    private void carregarNovoEcrã(ActionEvent event, String caminho, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            // Buscar o stage atual a partir do botão clicado
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.IOException;

@Controller
public class MenuPrincipalController {

    @FXML
    private VBox menuPane;

    @FXML
    private BorderPane rootPane;

    @FXML
    private StackPane conteudoPane;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private ImageView logoCentro;

    @FXML
    private void toggleMenu() {
        menuPane.setVisible(!menuPane.isVisible());
    }

    @FXML
    public void initialize() {
        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        logoCentro.setImage(logo);
    }

    private void carregarConteudo(String caminhoFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFxml));
            loader.setControllerFactory(springContext::getBean);
            Parent novoConteudo = loader.load();

            conteudoPane.getChildren().clear();
            conteudoPane.getChildren().add(novoConteudo);

            // Ajusta automaticamente o tamanho da janela
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.sizeToScene();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
