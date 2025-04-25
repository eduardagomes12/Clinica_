package com.example.clinicadesktop.controllers;

import com.example.clinicadesktop.models.Cliente;
import com.example.clinicadesktop.services.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    @FXML
    private void guardarCliente() {
        try {
            String nome = nomeField.getText();
            String contacto = contactoField.getText();
            String email = emailField.getText();
            String morada = moradaField.getText();

            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setContacto(contacto);
            cliente.setEmail(email);
            cliente.setMorada(morada);

            clienteService.save(cliente);

            mostrarAlerta("Sucesso", "Cliente registado com sucesso!");

            // Limpar os campos
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
}
