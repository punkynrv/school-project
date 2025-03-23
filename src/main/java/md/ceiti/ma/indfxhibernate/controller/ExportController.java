package md.ceiti.ma.indfxhibernate.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
public class ExportController {
    public String exportData(String choice) {
        try {
            String fileName;
            String loadedFile = null;
            if (choice.equals("big")) {
                fileName = "src/main/resources/export/biggest_grade.pdf";
                loadedFile = "src\\main\\resources\\reports-xml\\biggest-grade.jasper";
            }
            else if(choice == "small") {
                fileName = "src/main/resources/export/smallest_grade.pdf";
                loadedFile = "src\\main\\resources\\reports-xml\\smallest-grade.jasper";
            }
            else{
                fileName = "src/main/resources/export/students_grades.pdf";
                loadedFile = "src\\main\\resources\\reports-xml\\students-grades.jasper";
            }
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(loadedFile);
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/Scoala",
                    "postgres",
                    "9642"
            );
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), conn);
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, fileName
            );
            conn.close();
            return "Date exportate în mapa export!";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "A apărut o eroare la exportare!";
        }
    }
}
