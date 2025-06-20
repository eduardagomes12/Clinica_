package com.example.clinicadesktop.controllers;

import com.example.clinicadesktop.app.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuPrincipalController {

    @Autowired
    private ApplicationContext context;

    @FXML private VBox conteudoContainer;
    @FXML private ImageView logoImageView;
    @FXML private VBox sideMenu;
    @FXML private BorderPane rootPane;

    @FXML private Button btnRegistarCliente;
    @FXML private Button btnListarClientes;
    @FXML private Button btnRegistarAnimal;
    @FXML private Button btnListarAnimais;
    @FXML private Button btnMarcarConsulta;
    @FXML private Button btnListarConsultas;
    @FXML private Button btnRegistarPagamento;
    @FXML private Button btnListarPagamentos;
    @FXML private Button btnConsultarAgenda;
    @FXML private Button btnEmitirFatura;
    @FXML private Button btnListarFaturas;
    @FXML private Button btnRegistarTratamento;
    @FXML private Button btnListarTratamentos;
    @FXML private Button btnRegistarEntradaStock;
    @FXML private Button btnSaidaStock;
    @FXML private Button btnVerificarConsultaAnimal;




    @Autowired
    private ApplicationContext springContext;

    private String tipoUtilizador;

    // Setter chamado pelo LoginController
    public void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    @FXML
    public void initialize() {
        carregarLogo();

        // Espera que o tipo de utilizador seja definido após o login
        Platform.runLater(() -> {
            aplicarPermissoes();
            rootPane.applyCss();
            rootPane.layout();
        });
    }

    private void carregarLogo() {
        try {
            logoImageView.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            System.out.println("Logo não encontrado.");
        }
    }

    public void aplicarPermissoes() {
        if (tipoUtilizador == null) return;

        switch (tipoUtilizador) {
            case "Veterinário":
                btnRegistarCliente.setVisible(false);
                btnRegistarAnimal.setVisible(false);
                btnRegistarPagamento.setVisible(false);
                btnListarPagamentos.setVisible(false);
                btnEmitirFatura.setVisible(false);
                btnListarFaturas.setVisible(false);
                btnRegistarEntradaStock.setVisible(false);
                break;

            case "Rececionista":
                btnListarConsultas.setVisible(false);
                btnRegistarTratamento.setVisible(false);
                btnRegistarEntradaStock.setVisible(false);
                btnSaidaStock.setVisible(false);
                break;


            // Administrador pode tudo (não escondemos nada)

        }
    }

    private void carregarConteudo(String caminhoFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFxml));
            loader.setControllerFactory(context::getBean);
            Node conteudo = loader.load();
            conteudoContainer.getChildren().setAll(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarErro("Erro ao carregar a página", e.getMessage());
        }
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Ações dos botões
    @FXML private void abrirRegistarCliente() {
        carregarConteudo("/views/registarCliente.fxml");
    }

    @FXML private void abrirListarClientes() {
        carregarConteudo("/views/listarClientes.fxml");
    }

    @FXML private void abrirRegistarAnimal() {
        carregarConteudo("/views/registarAnimal.fxml");
    }

    @FXML private void abrirListarAnimais() {
        carregarConteudo("/views/listarAnimais.fxml");
    }

    @FXML private void abrirMarcarConsulta() {
        carregarConteudo("/views/registarConsulta.fxml");
    }

    @FXML private void abrirListarConsultas() {
        carregarConteudo("/views/listarConsultas.fxml");
    }

    @FXML private void abrirRegistarPagamento() {
        carregarConteudo("/views/registarPagamento.fxml");
    }

    @FXML private void abrirListarPagamentos() {
        carregarConteudo("/views/listarPagamentos.fxml");
    }

    @FXML private void abrirConsultarAgenda() { carregarConteudo("/views/consultarAgenda.fxml");}

    @FXML private void abrirListarFaturas() { carregarConteudo("/views/listarFaturas.fxml");}

    @FXML private void abrirEmitirFatura() { carregarConteudo("/views/emitirFatura.fxml");}

    @FXML private void abrirRegistarTratamento() { carregarConteudo("/views/registarTratamento.fxml");}

    @FXML private void abrirListarTratamentos() { carregarConteudo("/views/listarTratamentos.fxml");}

    @FXML private void abrirRegistarEntradaStock() { carregarConteudo("/views/registarEntradaStock.fxml");}

    @FXML private void abrirSaidaStock() { carregarConteudo("/views/saidaStock.fxml");}

    @FXML private void abrirVerificarConsultaAnimal() { carregarConteudo("/views/verificarConsultaAnimal.fxml");}


    @FXML
    private void terminarSessao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Terminar Sessão");
        alert.setContentText("Tens a certeza que queres terminar a sessão?");

        ButtonType terminar = new ButtonType("Terminar Sessão");
        ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(terminar, cancelar);

        alert.showAndWait().ifPresent(resposta -> {
            if (resposta == terminar) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escolherUtilizador.fxml"));
                    loader.setControllerFactory(context::getBean);
                    Scene scene = new Scene(loader.load());

                    Stage stage = MainApp.getPrimaryStage();
                    stage.setWidth(400);
                    stage.setHeight(500);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("Escolher Utilizador - Clínica Veterinária");

                } catch (IOException e) {
                    mostrarErro("Erro ao terminar sessão", e.getMessage());
                }
            }
        });
    }
}
