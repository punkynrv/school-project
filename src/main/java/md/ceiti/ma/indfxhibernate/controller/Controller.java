package md.ceiti.ma.indfxhibernate.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.ceiti.ma.indfxhibernate.Main;
import md.ceiti.ma.indfxhibernate.model.entities.*;
import md.ceiti.ma.indfxhibernate.model.implementations.*;

import java.io.IOException;
import java.util.List;

public class Controller <T>{
    @FXML
    private TableView <T> tabel;

    private final GrupeController grupeController = new GrupeController();
    private final ExportController exportController = new ExportController();
    private final StudentiController studentiController = new StudentiController();
    private final BursieriImplementation bri = new BursieriImplementation();
    private final StudentiImplementation sti = new StudentiImplementation();

    @FXML
    private void initialize(){
        loadBursieri();
        if (tabel.getItems().isEmpty()) {
            tabel.setVisible(false);
        }
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void openInsertWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class
                .getResource("insert-student-window.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Inserare Student");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource
                ("/md/ceiti/ma/indfxhibernate/css/insert-window-style.css")
                .toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(tabel.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void showStudentiActuali() {
        clearTable();
        studentiController.showStudentiActuali(tabel);
        displayTable();
    }

    @FXML
    private void showRestantieri() {
        clearTable();
        studentiController.showRestantieri(tabel);
        displayTable();
    }

    @FXML
    private void showReusite() {
        clearTable();
        studentiController.showReusite(tabel);
        displayTable();
    }

    @FXML
    private void showReusiteBest() {
        clearTable();
        studentiController.showReusiteBest(tabel);
        displayTable();
    }

    @FXML
    private void showBursieri() {
        clearTable();
        studentiController.showBursieri(tabel);
        displayTable();
    }

    @FXML
    private void showExmatriculati() {
        clearTable();
        studentiController.showExmatriculati(tabel);
        displayTable();
    }

    @FXML
    private void showGrupe(){
        clearTable();
        grupeController.showGrupe(tabel);
        displayTable();
    }

    @FXML
    private void showMediaGr(){
        clearTable();
        grupeController.showMediaGr(tabel);
        displayTable();
    }

    @FXML
    private void showAbsenteGr(){
        clearTable();
        grupeController.showAbsenteGr(tabel);
        displayTable();
    }

    @FXML
    private void exportDataSmall(){
        String result = exportController.exportData("small");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
    }

    @FXML
    private void exportDataStudenti(){
        String result = exportController.exportData("studenti");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
    }

    @FXML
    private void exportDataBig(){
        String result = exportController.exportData("big");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
    }

    private void clearTable(){
        tabel.getItems().clear();
        tabel.getColumns().clear();
    }

    private void loadBursieri(){
        bri.deleteAll();
        List <Student> stmed = sti.getReusiteBest();
        for(Student st : stmed){
            Bursier bursier = new Bursier(st.getIdS(), Math.round(st.getMedia().getMediaS() * 100));
            bri.create(bursier);
        }
    }

    @FXML
    private void openDeleteWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class
                .getResource("delete-student-window.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Exmatriculare");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource
                ("/md/ceiti/ma/indfxhibernate/css/delete-window-style.css")
                .toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(tabel.getScene().getWindow());
        stage.show();
    }

    private void displayTable(){
        if (!tabel.getItems().isEmpty()) {
            tabel.setVisible(true);
        }
    }

}
