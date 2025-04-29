package com.example.clinicadesktop.controllers;

import com.example.clinicadesktop.app.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class EscolherUtilizadorController {

    @Autowired
    private ApplicationContext context;

    @FXML
    private ImageView logoImageView;

    @FXML
    private void initialize() {
        carregarLogo();
    }

    private void carregarLogo() {
        try {
            logoImageView.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            System.out.println("Logo não encontrado: " + e.getMessage());
        }
    }

    @FXML
    private void escolherAdmin() {
        abrirLoginComTipo("Administrador");
    }

    @FXML
    private void escolherVet() {
        abrirLoginComTipo("Veterinário");
    }

    @FXML
    private void escolherRecep() {
        abrirLoginComTipo("Recepcionista");
    }

    private void abrirLoginComTipo(String tipo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            loader.setControllerFactory(context::getBean);
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setTipoUtilizadorPredefinido(tipo);

            Stage stage = MainApp.getPrimaryStage();
            stage.setTitle("Login – " + tipo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
