package md.ceiti.ma.indfxhibernate.model.interfaces;

import md.ceiti.ma.indfxhibernate.dto.DateGrupa;
import md.ceiti.ma.indfxhibernate.model.entities.Grupa;

import java.util.List;

public interface GrupeImplement extends CrudInterface <Grupa>{
    List<String> getGroupNames();
    int searchIdByGroupName(String numeGr);
    String searchGroupNameById(int id);
    List <DateGrupa> getGroupData();
}
