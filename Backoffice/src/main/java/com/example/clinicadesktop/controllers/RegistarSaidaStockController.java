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
public class RegistarSaidaStockController {

    @FXML
    private ComboBox<Produto> produtoComboBox;

    @FXML
    private TextField quantidadeField;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EisService eisService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        carregarProdutos();
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
            public Produto fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void registarSaida() {
        Produto produtoSelecionado = produtoComboBox.getValue();
        String qtdText = quantidadeField.getText();

        if (produtoSelecionado == null || qtdText.isEmpty()) {
            mostrarAlerta("Atenção", "Seleciona um produto e indica a quantidade.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int quantidade = Integer.parseInt(qtdText);

            if (quantidade <= 0) {
                mostrarAlerta("Valor inválido", "A quantidade deve ser maior que zero.", Alert.AlertType.WARNING);
                return;
            }

            if (quantidade > produtoSelecionado.getStock()) {
                mostrarAlerta("Stock insuficiente", "Não há stock suficiente para esta saída.", Alert.AlertType.ERROR);
                return;
            }

            // Criar e salvar movimento de saída
            Eis movimento = new Eis();
            movimento.setProduto(produtoSelecionado);
            movimento.setQtd(quantidade);
            movimento.setMovimento("saida");
            movimento.setData(LocalDate.now());
            eisService.save(movimento, produtoSelecionado.getId());

            // Atualizar stock
            produtoSelecionado.setStock(produtoSelecionado.getStock() - quantidade);
            produtoService.save(produtoSelecionado);

            mostrarAlerta("Sucesso", "Saída de stock registada com sucesso!", Alert.AlertType.INFORMATION);
            quantidadeField.clear();
            produtoComboBox.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "A quantidade deve ser um número inteiro.", Alert.AlertType.ERROR);
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
