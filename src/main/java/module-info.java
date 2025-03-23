module md.ceiti.ma.indfxhibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires net.sf.jasperreports.core;
    opens md.ceiti.ma.indfxhibernate.model.entities to org.hibernate.orm.core, javafx.base;
    opens md.ceiti.ma.indfxhibernate to javafx.fxml;
    exports md.ceiti.ma.indfxhibernate;
    opens md.ceiti.ma.indfxhibernate.controller to javafx.fxml;
    opens md.ceiti.ma.indfxhibernate.dto to javafx.base, org.hibernate.orm.core;
}