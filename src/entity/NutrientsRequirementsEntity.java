package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nutrients_requirements", schema = "mealcreator", catalog = "")
public class NutrientsRequirementsEntity {
    private int id;
    private double value;
    private int nutrientId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name="nutrient_id")
    public int getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(int nutrientId) {
        this.nutrientId = nutrientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutrientsRequirementsEntity that = (NutrientsRequirementsEntity) o;
        return id == that.id &&
                Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
