package com.example.clinicadesktop.controllers;

import com.example.core.models.Animal;
import com.example.core.models.Cliente;
import com.example.core.services.AnimalService;
import com.example.core.services.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class RegistarAnimalController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField idadeField;

    @FXML
    private TextField especieField;

    @FXML
    private TextField racaField;

    @FXML
    private ComboBox<Cliente> clienteComboBox;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        carregarClientes();
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteService.findAll();
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList(clientes);
        clienteComboBox.setItems(clientesObservable);

        clienteComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Cliente cliente) {
                if (cliente != null) {
                    return cliente.getNome() + " (" + cliente.getContacto() + ")";
                } else {
                    return "";
                }
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void guardarAnimal() {
        try {
            String nome = nomeField.getText();
            String idadeStr = idadeField.getText();
            String especie = especieField.getText();
            String raca = racaField.getText();
            Cliente clienteSelecionado = clienteComboBox.getValue();

            if (nome.isEmpty() || idadeStr.isEmpty() || especie.isEmpty() || raca.isEmpty() || clienteSelecionado == null) {
                mostrarAlerta("Aviso", "Todos os campos devem ser preenchidos e um cliente selecionado.");
                return;
            }

            int idade = Integer.parseInt(idadeStr);

            Animal animal = new Animal();
            animal.setNome(nome);
            animal.setIdade(idade);
            animal.setEspecie(especie);
            animal.setRaca(raca);
            animal.setCliente(clienteSelecionado); // ← CORREÇÃO

            animalService.save(animal);

            mostrarAlerta("Sucesso", "Animal registado com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Idade deve ser um número inteiro.");
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao registar animal.");
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

    private void limparCampos() {
        nomeField.clear();
        idadeField.clear();
        especieField.clear();
        racaField.clear();
        clienteComboBox.getSelectionModel().clearSelection();
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
