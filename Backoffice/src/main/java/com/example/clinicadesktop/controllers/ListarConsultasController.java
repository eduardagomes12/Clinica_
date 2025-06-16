package com.example.clinicadesktop.controllers;

import com.example.core.models.Consulta;
import com.example.core.services.ConsultaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ListarConsultasController {

    @FXML
    private TableView<Consulta> tableViewConsultas;

    @FXML
    private TableColumn<Consulta, String> colData;

    @FXML
    private TableColumn<Consulta, String> colMotivo;

    @FXML
    private TableColumn<Consulta, String> colVeterinario;

    @FXML
    private TableColumn<Consulta, String> colAnimal;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        colData.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getData())
                                .map(Object::toString)
                                .orElse("N/A")
                ));

        colMotivo.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getMotivo()).orElse("N/A")
                ));

        colVeterinario.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getVeterinarioResponsavel()).orElse("N/A")
                ));

        colAnimal.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getAnimal())
                                .map(animal -> Optional.ofNullable(animal.getNome()).orElse("Sem Nome"))
                                .orElse("Sem Dono")
                ));

        tableViewConsultas.setItems(FXCollections.observableArrayList(consultaService.findAll()));
        tableViewConsultas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
