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
@ToString(exclude = {"catedra", "grupa"})
@Entity
@Table(name="profesori", schema = "public")
public class Profesor implements Serializable, Cloneable {
    @Id
    private int idP;
    @Column(name = "idCatedra")
    private int idCatedra;
    private String IDNP;
    private String nume;
    private String prenume;
    private int salariu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCatedra", referencedColumnName = "idC",
            insertable = false, updatable = false)
    private Catedra catedra;
    @OneToMany(mappedBy = "profesor")
    private List<Grupa> grupa;

    @Tolerate
    public Profesor(int salariu, String prenume, String nume, String IDNP,
                    int idCatedra, int idP) {
        this.idP = idP;
        this.IDNP = IDNP;
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.idCatedra = idCatedra;
    }

    @Override
    public Profesor clone() {
        try {
            Profesor clone = (Profesor) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

