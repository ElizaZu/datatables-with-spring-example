package dte.model;

import javax.persistence.*;

/**
 * Created by Eliza on 05.04.2018.
 */
@Entity
@Table(name = "animal_type")
public class AnimalType {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Builder newBuilder(){
        return new AnimalType().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setName(String name){
            AnimalType.this.setName(name);
            return this;
        }

        public AnimalType build(){
            return AnimalType.this;
        }

    }
}
