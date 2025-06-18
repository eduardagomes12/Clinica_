package com.example.clinicadesktop.controllers;

import com.example.core.models.Consulta;
import com.example.core.models.Utilizador;
import com.example.core.services.ConsultaService;
import com.example.core.services.UtilizadorService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ConsultarAgendaController {

    @FXML
    private ComboBox<Utilizador> veterinarioComboBox;

    @FXML
    private TableView<Consulta> consultaTable;

    @FXML
    private TableColumn<Consulta, String> colData;

    @FXML
    private TableColumn<Consulta, String> colAnimal;

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private ConsultaService consultaService;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarVeterinarios();
    }

    private void configurarTabela() {
        colData.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getData().toString()
        ));

        colAnimal.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getAnimal().getNome()
        ));
    }

    private void carregarVeterinarios() {
        List<Utilizador> veterinarios = utilizadorService.findAll().stream()
                .filter(u -> u.getTipoUtilizador().getId() == 2)
                .collect(Collectors.toList());

        ObservableList<Utilizador> obsVeterinarios = FXCollections.observableArrayList(veterinarios);
        veterinarioComboBox.setItems(obsVeterinarios);

        veterinarioComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Utilizador utilizador) {
                return (utilizador != null) ? utilizador.getNome() : "";
            }

            @Override
            public Utilizador fromString(String string) {
                return null;
            }
        });

        veterinarioComboBox.setOnAction(event -> carregarConsultasDoVeterinario());
    }

    private void carregarConsultasDoVeterinario() {
        Utilizador vetSelecionado = veterinarioComboBox.getSelectionModel().getSelectedItem();
        if (vetSelecionado != null) {
            List<Consulta> consultas = consultaService.findAll().stream()
                    .filter(c -> c.getUtilizador() != null && c.getId().equals(vetSelecionado.getId()))
                    .collect(Collectors.toList());

            consultaTable.setItems(FXCollections.observableArrayList(consultas));
        } else {
            consultaTable.getItems().clear();
        }
    }
}