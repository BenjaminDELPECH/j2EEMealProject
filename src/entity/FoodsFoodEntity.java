package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "foods_food", schema = "mealcreator", catalog = "")
public class FoodsFoodEntity {
    private int id;
    private String nameEn;
    private String nameFr;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Basic
    @Column(name = "name_fr")
    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodsFoodEntity that = (FoodsFoodEntity) o;
        return id == that.id &&
                Objects.equals(nameEn, that.nameEn) &&
                Objects.equals(nameFr, that.nameFr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameEn, nameFr);
    }
}
