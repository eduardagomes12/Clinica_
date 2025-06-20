package com.example.clinicadesktop.controllers;

import com.example.core.models.Animal;
import com.example.core.models.Tratamento;
import com.example.core.services.AnimalService;
import com.example.core.services.TratamentoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ListarTratamentosController {

    @FXML private ComboBox<Animal> animalComboBox;
    @FXML private TableView<Tratamento> tabelaTratamentos;

    @FXML private TableColumn<Tratamento, LocalDate> colData;
    @FXML private TableColumn<Tratamento, String> colDescricao;
    @FXML private TableColumn<Tratamento, String> colStatus;
    @FXML private TableColumn<Tratamento, String> colTipoTratamento;
    @FXML private TableColumn<Tratamento, String> colAnimal;

    @Autowired private TratamentoService tratamentoService;
    @Autowired private AnimalService animalService;
    @Autowired private ApplicationContext springContext;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarAnimais();
        carregarTratamentos();

        animalComboBox.setOnAction(e -> filtrarPorAnimal());
    }

    private void configurarTabela() {
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colTipoTratamento.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoTratamento().getDescricao()));

        colAnimal.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getConsulta().getAnimal().getNome()));
    }

    private void carregarAnimais() {
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

    private void carregarTratamentos() {
        List<Tratamento> tratamentos = tratamentoService.findAll();
        tabelaTratamentos.setItems(FXCollections.observableArrayList(tratamentos));
    }

    @FXML
    private void filtrarPorAnimal() {
        Animal selecionado = animalComboBox.getValue();

        if (selecionado == null) {
            carregarTratamentos();
            return;
        }

        List<Tratamento> filtrados = tratamentoService.findAll().stream()
                .filter(t -> t.getConsulta().getAnimal().getId().equals(selecionado.getId()))
                .collect(Collectors.toList());

        tabelaTratamentos.setItems(FXCollections.observableArrayList(filtrados));
    }

    @FXML
    private void limparFiltro() {
        animalComboBox.getSelectionModel().clearSelection();
        carregarTratamentos();
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
