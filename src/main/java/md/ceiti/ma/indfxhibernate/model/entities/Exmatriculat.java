package md.ceiti.ma.indfxhibernate.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "grupa")
@Entity
@Table(name="exmatriculati", schema = "public")

public class Exmatriculat implements Serializable, Cloneable {
    @Id
    private int idS;
    private int absenteM;
    private int absenteN;
    @Column(name = "idG")
    private int idG;
    private String IDNP;
    private double mediaS;
    private String nume;
    private String prenume;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idG", referencedColumnName = "idG",
            insertable = false, updatable = false)
    private Grupa grupa;

    @Tolerate
    public Exmatriculat(int idS, int absenteM, int absenteN,
                        int idG, String IDNP, double mediaS,
                        String nume, String prenume) {
        this.idS = idS;
        this.absenteM = absenteM;
        this.absenteN = absenteN;
        this.idG = idG;
        this.IDNP = IDNP;
        this.mediaS = mediaS;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getGrupaNumeG(){
        return grupa.getNumeG();
    }

    @Override
    public Exmatriculat clone() {
        try {
            Exmatriculat clone = (Exmatriculat) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

