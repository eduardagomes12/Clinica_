package com.example.clinicadesktop.controllers;

import com.example.core.models.Cliente;
import com.example.core.services.ClienteService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

@Controller
public class ClienteController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField contactoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField moradaField;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private void guardarCliente() {
        try {
            String nome = nomeField.getText();
            String contacto = contactoField.getText();
            String email = emailField.getText();
            String morada = moradaField.getText();

            if (nome.isEmpty() || contacto.isEmpty() || email.isEmpty() || morada.isEmpty()) {
                mostrarAlerta("Aviso", "Todos os campos devem ser preenchidos.");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setContacto(contacto);
            cliente.setEmail(email);
            cliente.setMorada(morada);

            clienteService.save(cliente);

            mostrarAlerta("Sucesso", "Cliente registado com sucesso!");

            nomeField.clear();
            contactoField.clear();
            emailField.clear();
            moradaField.clear();

        } catch (Exception e) {
            mostrarAlerta("Erro", "Não foi possível registar o cliente.");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void abrirListarClientes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listarClientes.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) nomeField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lista de Clientes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void voltarMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menuPrincipal.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Clínica Veterinária - Menu Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
