// Novo controller para registar entradas de stock
package com.example.clinicadesktop.controllers;

import com.example.core.models.Eis;
import com.example.core.models.Produto;
import com.example.core.services.EisService;
import com.example.core.services.ProdutoService;
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
import java.util.List;

@Controller
public class RegistarEntradaStockController {

    @FXML
    private ComboBox<Produto> produtoComboBox;

    @FXML
    private TextField quantidadeField;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Label mensagemLabel;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EisService eisService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        carregarProdutos();
        dataPicker.setValue(LocalDate.now());
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoService.findAll();
        produtoComboBox.setItems(FXCollections.observableArrayList(produtos));
        produtoComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Produto produto) {
                return produto != null ? produto.getNome() : "";
            }

            @Override
            public Produto fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    private void guardarEntrada() {
        Produto produto = produtoComboBox.getValue();
        String qtdStr = quantidadeField.getText();
        LocalDate data = dataPicker.getValue();

        if (produto == null || qtdStr.isBlank() || data == null) {
            mostrarAlerta("Aviso", "Preenche todos os campos obrigatórios.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int quantidade = Integer.parseInt(qtdStr);

            // Atualizar stock do produto
            produto.setStock(produto.getStock() + quantidade);
            produtoService.save(produto);

            // Criar entrada EIS
            Eis entrada = new Eis();
            entrada.setProduto(produto);
            entrada.setQtd(quantidade);
            entrada.setData(data);
            entrada.setMovimento("entrada");

            eisService.save(entrada, produto.getId());

            mostrarAlerta("Sucesso", "Entrada registada com sucesso.", Alert.AlertType.INFORMATION);
            quantidadeField.clear();
            dataPicker.setValue(LocalDate.now());
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Quantidade inválida.", Alert.AlertType.ERROR);
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
