package dte.model;

import javax.persistence.*;

/**
 * Created by Eliza on 05.04.2018.
 */
@Entity
@Table(name = "animal_type")
public class AnimalType {
    private int id;
    private String name;

    protected AnimalType(){

    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalType that = (AnimalType) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static Builder createBuilder() {
        return new AnimalType().new Builder();
    }

    public class Builder {
        public Builder setName(String name) {
            AnimalType.this.setName(name);
            return this;
        }

        public AnimalType build() {
            return AnimalType.this;
        }
    }

}
