package com.example.clinicadesktop.controllers;

import com.example.core.models.Pagamento;
import com.example.core.services.PagamentoService;
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
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class ListarPagamentosController {

    @FXML private TableView<Pagamento> tableViewPagamentos;
    @FXML private TableColumn<Pagamento, String> colData;
    @FXML private TableColumn<Pagamento, String> colValor;
    @FXML private TableColumn<Pagamento, String> colValorTotal;
    @FXML private TableColumn<Pagamento, String> colTipoPagamento;
    @FXML private TableColumn<Pagamento, String> colConsulta;

    @Autowired private PagamentoService pagamentoService;
    @Autowired private ApplicationContext springContext;

    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        colData.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getData())
                                .map(data -> data.format(formatter))
                                .orElse("Sem data")
                )
        );

        colValor.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getValor())
                                .map(Object::toString)
                                .orElse("0.00")
                )
        );

        colValorTotal.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getValorTotal())
                                .map(Object::toString)
                                .orElse("0.00")
                )
        );

        colTipoPagamento.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        Optional.ofNullable(cellData.getValue().getTipoPagamento())
                                .map(tp -> Optional.ofNullable(tp.getDescricao()).orElse("Sem descrição"))
                                .orElse("Desconhecido")
                )
        );

        colConsulta.setCellValueFactory(cellData -> {
            var consulta = cellData.getValue().getConsulta();
            if (consulta != null && consulta.getAnimal() != null) {
                String nomeAnimal = Optional.ofNullable(consulta.getAnimal().getNome()).orElse("Animal");
                String dataConsulta = Optional.ofNullable(consulta.getData())
                        .map(Object::toString)
                        .orElse("sem data");
                return new SimpleStringProperty("Consulta de " + nomeAnimal + " em " + dataConsulta);
            } else {
                return new SimpleStringProperty("Consulta não atribuída");
            }
        });

        tableViewPagamentos.setItems(FXCollections.observableArrayList(pagamentoService.findAll()));
        tableViewPagamentos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
