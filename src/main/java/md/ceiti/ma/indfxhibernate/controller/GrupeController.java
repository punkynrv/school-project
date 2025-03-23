package md.ceiti.ma.indfxhibernate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.ceiti.ma.indfxhibernate.dto.DateGrupa;
import md.ceiti.ma.indfxhibernate.model.entities.Grupa;
import md.ceiti.ma.indfxhibernate.model.implementations.GrupeImplementation;

import java.util.List;

public class GrupeController <T> {

    private GrupeImplementation gri = new GrupeImplementation();

    public void showGrupe(TableView <T> tabel){
        tabel.getColumns().addAll(
                createTableColumn("Nume Grupa", "numeG"),
                createTableColumn("An Admitere", "anAdm"),
                createTableColumn("Diriginte", "profesorDiriginte")
        );
        List<Grupa> gr = gri.getAll();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) gr);
        tabel.setItems(observableList);
    }

    public void showMediaGr(TableView <T> tabel){
        tabel.getColumns().addAll(
                createTableColumn("Nume Grupa", "numeG"),
                createTableColumn("Media per Grupă", "med")
        );
        List<DateGrupa> gr = gri.getGroupData();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) gr);
        tabel.setItems(observableList);
    }

    public void showAbsenteGr(TableView <T> tabel){
        tabel.getColumns().addAll(
                createTableColumn("Nume Grupa", "numeG"),
                createTableColumn("Absențe per Grupă", "absente")
        );
        List<DateGrupa> gr = gri.getGroupData();
        ObservableList<T> observableList = FXCollections.observableArrayList((List <T>) gr);
        tabel.setItems(observableList);
    }

    private <S, T> TableColumn<S, T> createTableColumn(String columnName, String cell) {
        TableColumn<S, T> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(cell));
        return column;
    }
}
