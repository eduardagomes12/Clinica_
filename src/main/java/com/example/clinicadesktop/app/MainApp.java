package com.example.clinicadesktop.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.clinicadesktop")
public class MainApp extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Stage primaryStage;

    @Override
    public void init() {
        // Iniciar o contexto Spring Boot
        springContext = SpringApplication.run(MainApp.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        // Carregar o FXML da escolha de utilizador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escolherUtilizador.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Scene scene = new Scene(loader.load());

        // Carregar o estilo específico da tela de escolher utilizador
        scene.getStylesheets().add(getClass().getResource("/styles/login.css").toExternalForm());

        stage.setTitle("Escolher Utilizador - Clínica Veterinária");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Fechar o contexto Spring Boot
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
