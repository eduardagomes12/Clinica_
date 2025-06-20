package com.example.clinicadesktop.controllers;

import com.example.core.models.Animal;
import com.example.core.models.Consulta;
import com.example.core.services.AnimalService;
import com.example.core.services.ConsultaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VerificarConsultaAnimalController {

    @FXML
    private ComboBox<Animal> animalComboBox;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        List<Animal> animais = animalService.findAll();
        animalComboBox.setItems(FXCollections.observableArrayList(animais));
        animalComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Animal animal) {
                return animal != null ? animal.getNome() : "";
            }

            @Override
            public Animal fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    private void verificarConsulta() {
        Animal animal = animalComboBox.getValue();

        if (animal == null) {
            mostrarAlerta("Aviso", "Seleciona um animal.", Alert.AlertType.WARNING);
            return;
        }

        List<Consulta> futuras = consultaService.findAll().stream()
                .filter(c -> c.getAnimal().getId().equals(animal.getId()))
                .filter(c -> !c.getData().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Consulta::getData))
                .collect(Collectors.toList());

        if (futuras.isEmpty()) {
            mostrarAlerta("Sem Consultas", "Este animal não tem consultas futuras.", Alert.AlertType.INFORMATION);
        } else {
            Consulta proxima = futuras.get(0);
            mostrarAlerta("Consulta Encontrada", "Este animal tem consulta marcada para " + proxima.getData(), Alert.AlertType.INFORMATION);
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void voltarMenuPrincipal(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menuPrincipal.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
