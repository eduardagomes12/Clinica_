package com.example.clinicadesktop.controllers;

import com.example.core.models.Fatura;
import com.example.core.models.Pagamento;
import com.example.core.services.FaturaService;
import com.example.core.services.PagamentoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.util.StringConverter;

@Controller
public class EmitirFaturaController {

    @FXML private ComboBox<Pagamento> pagamentoComboBox;
    @FXML private Label labelTipoPagamento;
    @FXML private Label labelNumeroFatura;
    @FXML private Label labelDataAtual;
    @FXML private Label labelValor;
    @FXML private Button btnEmitir;
    @FXML private Label mensagemLabel;

    @Autowired private ApplicationContext springContext;
    @Autowired private PagamentoService pagamentoService;
    @Autowired private FaturaService faturaService;

    private int proximoNumero = 1;

    @FXML
    public void initialize() {
        carregarPagamentos();
        labelDataAtual.setText(LocalDate.now().toString());
        definirProximoNumeroFatura();

        pagamentoComboBox.setOnAction(e -> {
            Pagamento p = pagamentoComboBox.getValue();
            if (p != null) {
                labelValor.setText(p.getValor() + " €");
                labelTipoPagamento.setText(p.getTipoPagamento().getDescricao());
            } else {
                labelValor.setText("");
                labelTipoPagamento.setText("");
            }
        });

        configurarConversores();
        btnEmitir.setOnAction(e -> emitirFatura());
    }

    private void configurarConversores() {
        pagamentoComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Pagamento pag) {
                if (pag == null || pag.getConsulta() == null || pag.getConsulta().getAnimal() == null)
                    return "Pagamento inválido";
                return String.format("Consulta de %s em %s",
                        pag.getConsulta().getAnimal().getNome(),
                        pag.getConsulta().getData());
            }

            @Override
            public Pagamento fromString(String s) {
                return null;
            }
        });
    }

    private void carregarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.findAll().stream()
                .filter(p -> p.getFatura() == null)
                .collect(Collectors.toList());
        pagamentoComboBox.setItems(FXCollections.observableArrayList(pagamentos));
    }

    private void definirProximoNumeroFatura() {
        Optional<Fatura> ultima = faturaService.findAll().stream()
                .max(Comparator.comparing(Fatura::getNum));
        proximoNumero = ultima.map(f -> f.getNum() + 1).orElse(1);
        labelNumeroFatura.setText(String.valueOf(proximoNumero));
    }

    @FXML
    private void emitirFatura() {
        Pagamento pagamentoSelecionado = pagamentoComboBox.getValue();

        if (pagamentoSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um pagamento.");
            alert.showAndWait();
            return;
        }

        Fatura fatura = new Fatura();
        fatura.setData(LocalDate.now());
        fatura.setNum(proximoNumero);
        fatura.setValorTotal(pagamentoSelecionado.getValor());
        fatura.setTipoPagamento(pagamentoSelecionado.getTipoPagamento());

        Fatura salva = faturaService.save(fatura, fatura.getTipoPagamento().getId());

        pagamentoSelecionado.setFatura(salva);
        pagamentoService.save(pagamentoSelecionado);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Fatura emitida com sucesso!");
        alert.showAndWait();

        pagamentoComboBox.getItems().remove(pagamentoSelecionado);
        labelValor.setText("");
        labelTipoPagamento.setText("");
        definirProximoNumeroFatura();
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
