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
@ToString(exclude = "profesor")
@Entity
@Table(name="catedre", schema = "public")
public class Catedra implements Serializable, Cloneable {
    @Id
    private int idC;
    private String nmCatedra;
    @OneToMany(mappedBy = "idCatedra")
    private List <Profesor> profesor;

    @Tolerate
    public Catedra(int idC, String nmCatedra) {
        this.idC = idC;
        this.nmCatedra = nmCatedra;
    }

    @Override
    public Catedra clone() {
        try {
            Catedra clone = (Catedra) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
