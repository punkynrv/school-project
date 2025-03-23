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
@Table(name="medii", schema = "public")
public class Media implements Serializable, Cloneable {
    @Id
    @Column(name = "IdS")
    private int idS;
    @Getter
    @OneToOne
    @JoinColumn(name = "idS", insertable = false, updatable = false)
    private Student student;
    private int absenteM;
    private int absenteN;
    private double mediaS;

    @Tolerate
    public Media(int idS, int absenteM, int absenteN, double mediaS) {
        this.idS = idS;
        this.mediaS = mediaS;
        this.absenteM = absenteM;
        this.absenteN = absenteN;
    }


    @Override
    public Media clone() {
        try {
            Media clone = (Media) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
