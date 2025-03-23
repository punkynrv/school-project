package md.ceiti.ma.indfxhibernate.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "student")
@Entity
@Table(name="bursieri", schema = "public")

public class Bursier implements Serializable, Cloneable {
    @Id
    @Column(name = "idS")
    private int idS;
    private double bursa;
    @OneToOne
    @JoinColumn(name = "idS", insertable = false, updatable = false)
    private Student student;

    @Tolerate
    public Bursier(int idS, double bursa) {
        this.idS = idS;
        this.bursa = bursa;
    }

    public String getStudentIDNP(){
        return student.getIDNP();
    }

    public String getStudentNume(){
        return student.getNume();
    }

    public String getStudentPrenume(){
        return student.getPrenume();
    }

    @Override
    public Bursier clone() {
        try {
            Bursier clone = (Bursier) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

