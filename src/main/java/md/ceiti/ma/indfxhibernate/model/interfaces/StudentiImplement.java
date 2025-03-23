package md.ceiti.ma.indfxhibernate.model.interfaces;

import md.ceiti.ma.indfxhibernate.model.entities.Student;

import java.util.List;

public interface StudentiImplement extends CrudInterface <Student> {
    int searchByIDNP(String IDNP);
    int getRowCount();
    List<Student> getReusite();
    List <Student> getReusiteBest();
    List <Student> getRestantieri();
    List <Student> getSmallestMed();
    List <Student> getBiggestMed();
}
