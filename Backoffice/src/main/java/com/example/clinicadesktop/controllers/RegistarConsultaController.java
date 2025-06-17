package com.example.clinicadesktop.controllers;

import com.example.core.models.Animal;
import com.example.core.models.Consulta;
import com.example.core.models.Utilizador;
import com.example.core.services.AnimalService;
import com.example.core.services.ConsultaService;
import com.example.core.services.UtilizadorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistarConsultaController {

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TextArea motivoField;

    @FXML
    private ComboBox<Utilizador> veterinarioComboBox;

    @FXML
    private ComboBox<Animal> animalComboBox;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private ApplicationContext springContext;

    @FXML
    public void initialize() {
        carregarAnimais();
        carregarVeterinarios();
    }

    private void carregarAnimais() {
        List<Animal> animais = animalService.findAll();
        animais.sort(Comparator.comparing(Animal::getNome)); // opcional, para melhor UX
        ObservableList<Animal> animaisObservable = FXCollections.observableArrayList(animais);
        animalComboBox.setItems(animaisObservable);

        animalComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Animal animal) {
                return (animal != null) ? animal.getNome() : "";
            }

            @Override
            public Animal fromString(String string) {
                return null;
            }
        });
    }

    private void carregarVeterinarios() {
        List<Utilizador> veterinarios = utilizadorService.findAll().stream()
                .filter(u -> u.getTipoUtilizador().getId() == 2)
                .collect(Collectors.toList());

        ObservableList<Utilizador> veterinariosObservable = FXCollections.observableArrayList(veterinarios);
        veterinarioComboBox.setItems(veterinariosObservable);

        veterinarioComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Utilizador utilizador) {
                return (utilizador != null) ? utilizador.getNome() : "";
            }

            @Override
            public Utilizador fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void guardarConsulta() {
        try {
            LocalDate data = dataPicker.getValue();
            String motivo = motivoField.getText();
            Utilizador veterinario = veterinarioComboBox.getValue();
            Animal animalSelecionado = animalComboBox.getValue();

            if (data == null || motivo.isEmpty() || veterinario == null || animalSelecionado == null) {
                mostrarAlerta("Aviso", "Todos os campos devem ser preenchidos e um animal selecionado.");
                return;
            }

            Consulta consulta = new Consulta();
            consulta.setData(data);
            consulta.setMotivo(motivo);
            consulta.setVeterinarioResponsavel(veterinario.getNome());
            consulta.setAnimal(animalSelecionado);

            Utilizador utilizadorAtual = LoginController.utilizadorAutenticado;
            if (utilizadorAtual == null) {
                mostrarAlerta("Erro", "Utilizador autenticado não encontrado.");
                return;
            }
            consulta.setUtilizador(utilizadorAtual);

            consultaService.save(consulta);

            mostrarAlerta("Sucesso", "Consulta marcada com sucesso!");
            limparCampos();

        } catch (Exception e) {
            mostrarAlerta("Erro ao Marcar Consulta", "Ocorreu um erro.\n" + (e.getMessage() != null ? e.getMessage() : "Detalhes indisponíveis."));
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        dataPicker.setValue(null);
        motivoField.clear();
        veterinarioComboBox.getSelectionModel().clearSelection();
        animalComboBox.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
