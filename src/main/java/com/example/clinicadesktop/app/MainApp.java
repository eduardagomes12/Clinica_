package com.example.clinicadesktop.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.clinicadesktop") // para apanhar tudo
public class MainApp extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(MainApp.class); // arranca Spring
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/registarCliente.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean); // permite usar @Autowired nos controllers

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Clínica Veterinária - Backoffice");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args); // chama o JavaFX Application
    }
}
