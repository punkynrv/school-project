package md.ceiti.ma.indfxhibernate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.ceiti.ma.indfxhibernate.model.entities.Bursier;
import md.ceiti.ma.indfxhibernate.model.entities.Exmatriculat;
import md.ceiti.ma.indfxhibernate.model.entities.Student;
import md.ceiti.ma.indfxhibernate.model.implementations.BursieriImplementation;
import md.ceiti.ma.indfxhibernate.model.implementations.ExmatriculatiImplementation;
import md.ceiti.ma.indfxhibernate.model.implementations.StudentiImplementation;

import java.util.List;

public class StudentiController <T> {

    private final StudentiImplementation sti = new StudentiImplementation();
    private final ExmatriculatiImplementation exm = new ExmatriculatiImplementation();
    private final BursieriImplementation bri = new BursieriImplementation();

    public void showStudentiActuali(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "IDNP"),
                createTableColumn("Nume", "nume"),
                createTableColumn("Prenume", "prenume"),
                createTableColumn("Grupa", "grupaNumeG"),
                createTableColumn("Media", "mediaMediaS"),
                createTableColumn("Absente N", "mediaAbsenteN"),
                createTableColumn("Absente M", "mediaAbsenteM")
        );
        List<Student> studenti = sti.getAll();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) studenti);
        tabel.setItems(observableList);
    }

    public void showRestantieri(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "IDNP"),
                createTableColumn("Nume", "nume"),
                createTableColumn("Prenume", "prenume"),
                createTableColumn("Media", "mediaMediaS")
        );
        List<Student> studenti = sti.getRestantieri();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) studenti);
        tabel.setItems(observableList);
    }

    public void showReusite(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "IDNP"),
                createTableColumn("Nume", "nume"),
                createTableColumn("Prenume", "prenume"),
                createTableColumn("Media", "mediaMediaS"),
                createTableColumn("Absente N", "mediaAbsenteN")
        );
        List<Student> studenti = sti.getReusite();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) studenti);
        tabel.setItems(observableList);
    }

    public void showReusiteBest(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "IDNP"),
                createTableColumn("Nume", "nume"),
                createTableColumn("Prenume", "prenume"),
                createTableColumn("Media", "mediaMediaS"),
                createTableColumn("Absente N", "mediaAbsenteN")
        );
        List<Student> studenti = sti.getReusiteBest();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) studenti);
        tabel.setItems(observableList);
    }

    public void showBursieri(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "studentIDNP"),
                createTableColumn("Nume", "studentNume"),
                createTableColumn("Prenume", "studentPrenume"),
                createTableColumn("Bursa", "bursa")
        );
        List <Bursier> br = bri.getAll();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) br);
        tabel.setItems(observableList);
    }

    public void showExmatriculati(TableView<T> tabel) {
        tabel.getColumns().addAll(
                createTableColumn("IDNP", "IDNP"),
                createTableColumn("Nume", "nume"),
                createTableColumn("Prenume", "prenume"),
                createTableColumn("Media", "mediaS"),
                createTableColumn("Absente M", "absenteM"),
                createTableColumn("Absente N", "absenteN"),
                createTableColumn("Grupa", "grupaNumeG")
        );
        List <Exmatriculat> br = exm.getAll();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) br);
        tabel.setItems(observableList);
    }


    private <S, T> TableColumn<S, T> createTableColumn(String columnName, String cell) {
        TableColumn<S, T> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(cell));
        if ("Bursa".equals(columnName)) {
            TableColumn<S, Double> bursaColumn = (TableColumn<S, Double>) column;
            bursaColumn.setCellFactory(tc -> new TableCell<S, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("%.2f", item));
                    }
                }
            });
        }
        return column;
    }
}
