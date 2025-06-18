package com.example.clinicadesktop.controllers;

import com.example.core.models.Fatura;
import com.example.core.services.FaturaService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.beans.property.ReadOnlyStringWrapper;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


@Controller
public class ListarFaturasController {

    @FXML
    private TableView<Fatura> tabelaFaturas;

    @FXML
    private TableColumn<Fatura, String> colNum;

    @FXML
    private TableColumn<Fatura, String> colData;

    @FXML
    private TableColumn<Fatura, String> colValorTotal;

    @FXML
    private TableColumn<Fatura, String> colTipoPagamento;

    @Autowired
    private ApplicationContext springContext;

    @Autowired
    private FaturaService faturaService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        colNum.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getNum().toString()));
        colData.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getData().format(formatter)));
        colValorTotal.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getValorTotal().toString()));
        colTipoPagamento.setCellValueFactory(c -> new ReadOnlyStringWrapper(
                c.getValue().getTipoPagamento() != null ? c.getValue().getTipoPagamento().getDescricao() : "N/A"
        ));

        tabelaFaturas.setItems(FXCollections.observableArrayList(faturaService.findAll()));
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
