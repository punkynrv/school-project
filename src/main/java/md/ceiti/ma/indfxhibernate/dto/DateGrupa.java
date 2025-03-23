package md.ceiti.ma.indfxhibernate.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DateGrupa implements Serializable, Cloneable {
    private String numeG;
    private double med;
    private Long absente;

    @Override
    public DateGrupa clone() {
        try {
            DateGrupa clone = (DateGrupa) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

