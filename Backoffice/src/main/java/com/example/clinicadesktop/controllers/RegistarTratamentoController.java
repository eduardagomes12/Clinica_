package com.example.clinicadesktop.controllers;

import com.example.core.models.Consulta;
import com.example.core.models.TipoTratamento;
import com.example.core.models.Tratamento;
import com.example.core.services.ConsultaService;
import com.example.core.services.TipoTratamentoService;
import com.example.core.services.TratamentoService;
import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RegistarTratamentoController {

    @FXML private ComboBox<Consulta> consultaComboBox;
    @FXML private ComboBox<TipoTratamento> tipoTratamentoComboBox;
    @FXML private DatePicker dataPicker;
    @FXML private TextArea descricaoField;
    @FXML private TextField statusField;
    @FXML private Label mensagemLabel;

    @Autowired private ApplicationContext springContext;
    @Autowired private TratamentoService tratamentoService;
    @Autowired private ConsultaService consultaService;
    @Autowired private TipoTratamentoService tipoTratamentoService;

    @FXML
    public void initialize() {
        carregarConsultas();
        carregarTiposTratamento();
        dataPicker.setValue(LocalDate.now());

        consultaComboBox.setConverter(new StringConverter<>() {
            @Override public String toString(Consulta c) {
                if (c == null || c.getAnimal() == null) return "Consulta inválida";
                return c.getAnimal().getNome() + " em " + c.getData();
            }
            @Override public Consulta fromString(String s) { return null; }
        });

        tipoTratamentoComboBox.setConverter(new StringConverter<>() {
            @Override public String toString(TipoTratamento t) {
                return t != null ? t.getDescricao() : "";
            }
            @Override public TipoTratamento fromString(String s) { return null; }
        });
    }

    private void carregarConsultas() {
        List<Consulta> consultas = consultaService.findAll();
        consultaComboBox.setItems(FXCollections.observableArrayList(consultas));
    }

    private void carregarTiposTratamento() {
        List<TipoTratamento> tipos = tipoTratamentoService.findAll();
        tipoTratamentoComboBox.setItems(FXCollections.observableArrayList(tipos));
    }

    @FXML
    private void guardarTratamento() {
        Consulta consulta = consultaComboBox.getValue();
        TipoTratamento tipo = tipoTratamentoComboBox.getValue();
        LocalDate data = dataPicker.getValue();
        String descricao = descricaoField.getText();
        String status = statusField.getText();

        if (consulta == null || tipo == null || data == null || descricao.isBlank()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Todos os campos devem ser preenchidos.");
            alerta.showAndWait();
            return;
        }

        Tratamento tratamento = new Tratamento();
        tratamento.setConsulta(consulta);
        tratamento.setTipoTratamento(tipo);
        tratamento.setData(data);
        tratamento.setDescricao(descricao);
        tratamento.setStatus(status);

        tratamentoService.save(tratamento);

        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText(null);
        sucesso.setContentText("Tratamento registado com sucesso!");
        sucesso.showAndWait();

        // Limpar campos
        descricaoField.clear();
        statusField.clear();
        consultaComboBox.getSelectionModel().clearSelection();
        tipoTratamentoComboBox.getSelectionModel().clearSelection();
        dataPicker.setValue(LocalDate.now());
    }

    @FXML
    private void voltarMenuPrincipal(javafx.event.ActionEvent event) {
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
