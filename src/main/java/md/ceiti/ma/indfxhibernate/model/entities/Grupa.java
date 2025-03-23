package md.ceiti.ma.indfxhibernate.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"profesor", "student"})
@Entity
@Table(name="grupe", schema = "public")

public class Grupa implements Serializable, Cloneable {
    @Id
    private int idG;
    private int anAdm;
    @Column(name = "idDir")
    private int idDir;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDir", referencedColumnName = "idP",
            insertable = false, updatable = false)
    private Profesor profesor;
    private String numeG;
    @OneToMany(mappedBy = "grupa")
    private List<Student> student;

    @Tolerate
    public Grupa(int idG, int anAdm, int idDir, String numeG) {
        this.idG = idG;
        this.anAdm = anAdm;
        this.numeG = numeG;
        this.idDir = idDir;
    }

    public String getProfesorDiriginte(){
        return profesor.getNume() + " " + profesor.getPrenume();
    }


    @Override
    public Grupa clone() {
        try {
            Grupa clone = (Grupa) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
