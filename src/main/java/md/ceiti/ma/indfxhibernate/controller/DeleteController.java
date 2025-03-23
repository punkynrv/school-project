package md.ceiti.ma.indfxhibernate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import md.ceiti.ma.indfxhibernate.model.entities.Exmatriculat;
import md.ceiti.ma.indfxhibernate.model.entities.Media;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.implementations.BursieriImplementation;
import md.ceiti.ma.indfxhibernate.model.implementations.ExmatriculatiImplementation;
import md.ceiti.ma.indfxhibernate.model.implementations.MediiImplementation;
import md.ceiti.ma.indfxhibernate.model.implementations.StudentiImplementation;

public class DeleteController {
    @FXML
    private TextField tf;

    @FXML
    private Button close;

    private final StudentiImplementation sti = new StudentiImplementation();
    private final MediiImplementation mdi = new MediiImplementation();
    private final ExmatriculatiImplementation exm = new ExmatriculatiImplementation();
    private final BursieriImplementation brs = new BursieriImplementation();

    public void initialize() {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 13) {
                tf.setText(newValue.substring(0, 13));
            }
        });
    }

    @FXML
    private void handleDelete() {
        String result = deleteStudent(tf.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();
        if (result.equals("Student sters cu succes!")) {
            closeWindow();
        }
    }

    @FXML
    private void handleClose() {
        closeWindow();
    }

    private String deleteStudent(String IDNP){
        if (IDNP != null && IDNP.length() == 13){
            int id = sti.searchByIDNP(IDNP);
            if(id != -1){
                Student st = sti.get(id);
                Media med = mdi.get(id);
                int idInserare = exm.getRowCount();
                idInserare++;
                Exmatriculat exmatriculat = new Exmatriculat(idInserare,
                        med.getAbsenteM(), med.getAbsenteN(), st.getIdG(),
                        st.getIDNP(), med.getMediaS(), st.getNume(), st.getPrenume());
                brs.delete(id);
                mdi.delete(id);
                sti.delete(id);
                exm.create(exmatriculat);
                return "Student sters cu succes!";
            }
            else{
                return "Nu s-a gasit un student cu astfel de IDNP";
            }
        }
        else {
            return "Format gresit la IDNP!";
        }
    }

    private void closeWindow() {
        ((javafx.stage.Stage) close.getScene().getWindow()).close();
    }
}
