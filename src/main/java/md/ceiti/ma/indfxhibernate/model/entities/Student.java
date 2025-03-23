package md.ceiti.ma.indfxhibernate.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"grupa", "media", "bursier"})
@Entity
@Table(name="studenti", schema = "public")

public class Student implements Serializable, Cloneable {
    @Id
    private int idS;
    private String IDNP;
    private String nume;
    private String prenume;
    @Column(name = "idG")
    private int idG;
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idG", referencedColumnName = "idG",
            insertable = false, updatable = false)
    private Grupa grupa;
    @Getter
    @OneToOne(mappedBy = "student", fetch = FetchType.EAGER)
    private Media media;
    @OneToOne(mappedBy = "student", fetch = FetchType.EAGER)
    private Bursier bursier;

    @Tolerate
    public Student(int idS, String IDNP, String nume, String prenume, int idG) {
        this.idS = idS;
        this.IDNP = IDNP;
        this.nume = nume;
        this.prenume = prenume;
        this.idG = idG;
    }

    public String getGrupaNumeG(){
        return grupa.getNumeG();
    }

    public Double getMediaMediaS(){
        return media.getMediaS();
    }

    public int getMediaAbsenteM(){
        return media.getAbsenteM();
    }

    public int getMediaAbsenteN(){
        return media.getAbsenteN();
    }

    @Override
    public Student clone() {
        try {
            Student clone = (Student) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
