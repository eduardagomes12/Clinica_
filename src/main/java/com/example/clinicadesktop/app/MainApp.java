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

    private ConfigurableApplicationContext springContext;
    private static Stage primaryStage; // Guardar o stage principal

    @Override
    public void init() {
        springContext = SpringApplication.run(MainApp.class); // arranca Spring
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage; // guardar a referência ao Stage
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/menuPrincipal.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Scene scene = new Scene(loader.load());
        stage.setTitle("Clínica Veterinária - Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Permite que outras classes acedam ao Stage principal
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
