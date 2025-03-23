package md.ceiti.ma.indfxhibernate.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import md.ceiti.ma.indfxhibernate.model.entities.Bursier;
import md.ceiti.ma.indfxhibernate.model.entities.Media;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.implementations.*;

import java.util.List;

public class InsertController {
    @FXML
    private TextField idnpField;

    @FXML
    private TextField numeField;

    @FXML
    private TextField prenumeField;

    @FXML
    private ComboBox<String> grupeComboBox;

    @FXML
    private TextField mediaField;

    @FXML
    private Spinner<Integer> nemotivSpinner;

    @FXML
    private Spinner<Integer> motivSpinner;

    private final GrupeImplementation gri = new GrupeImplementation();
    private final StudentiImplementation sti = new StudentiImplementation();
    private final MediiImplementation mdi = new MediiImplementation();
    private final BursieriImplementation bri = new BursieriImplementation();

    public void initialize() {
        nemotivSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        motivSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        grupeComboBox.setItems(FXCollections.observableArrayList(
                gri.getGroupNames()
        ));

        idnpField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idnpField.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 13) {
                idnpField.setText(newValue.substring(0, 13));
            }
        });
        mediaField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    double value = Double.parseDouble(newValue);
                    if (value < 1 || value > 10) {
                        mediaField.setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    mediaField.setText(oldValue);
                }
            }
        });
    }


    @FXML
    private void addStudentAction(){
        String numeGr = grupeComboBox.getValue();
        if(numeGr == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Completati toate campurile!");
            alert.showAndWait();
        }
        else{
            try {
                String result = handleInsertAction(
                        numeField.getText(),
                        prenumeField.getText(),
                        numeGr,
                        Double.parseDouble(mediaField.getText()),
                        nemotivSpinner.getValue(),
                        motivSpinner.getValue(),
                        idnpField.getText()
                );

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(result);
                alert.showAndWait();

                if ("Un nou student a fost adăugat!".equals(result)) {
                    loadBursieri();
                    ((Stage) idnpField.getScene().getWindow()).close();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Completati toate campurile!");
                alert.showAndWait();
            }
        }
    }

    private String handleInsertAction(String nume, String prenume, String numeGr,
                                    double media, int nemotivValue, int motivValue,
                                    String idnpText){
        if(sti.searchByIDNP(idnpText) != -1){
            return "Acest IDNP deja există";
        }
        else if(idnpText.length() != 13){
            return "Format IDNP gresit!";
        }
        else{
            int idG = gri.searchIdByGroupName(numeGr);
            int idS = sti.getRowCount();
            idS++;
            nume = nume.toLowerCase();
            nume = Character.toString(nume.charAt(0)).toUpperCase() + nume.substring(1);
            prenume = prenume.toLowerCase();
            prenume = Character.toString(prenume.charAt(0)).toUpperCase() + prenume.substring(1);
            Student student = new Student(idS, idnpText, nume, prenume, idG);
            sti.create(student);
            Media med = new Media(idS, motivValue, nemotivValue, media);
            mdi.create(med);
            return "Un nou student a fost adăugat!";
        }
    }

    private void loadBursieri(){
        bri.deleteAll();
        List<Student> stmed = sti.getReusiteBest();
        for(Student st : stmed){
            Bursier bursier = new Bursier(st.getIdS(), st.getMedia().getMediaS() * 100);
            bri.create(bursier);
        }
    }

    @FXML
    private void cancelAction(){
        ((Stage) idnpField.getScene().getWindow()).close();
    }
}
