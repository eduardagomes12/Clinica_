package com.example.clinicadesktop.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.core", "com.example.clinicadesktop"})
@EnableJpaRepositories(basePackages = {"com.example.core.reps"})
@EntityScan(basePackages = {"com.example.core.models"})
public class MainApp extends Application {

    private static ConfigurableApplicationContext springContext;
    private static Stage primaryStage;

    @Override
    public void init() {
        springContext = SpringApplication.run(MainApp.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setWidth(1100);
        primaryStage.setHeight(700);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escolherUtilizador.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/styles/login.css").toExternalForm());

        stage.setTitle("Escolher Utilizador - Clínica Veterinária");
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

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
