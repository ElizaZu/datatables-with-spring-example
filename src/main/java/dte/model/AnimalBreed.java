package dte.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Eliza on 05.04.2018.
 */
@Entity
@Table(name = "animal_breed")
public class AnimalBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id", nullable = false)
    private AnimalType animalType;

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
    public AnimalType getAnimalType() {return animalType;}
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public static Builder newBuilder(){
        return new AnimalBreed().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setName(String name){
            AnimalBreed.this.setName(name);
            return this;
        }

        public Builder setAnimalType(AnimalType type){
            AnimalBreed.this.setAnimalType(type);
            return this;
        }

        public AnimalBreed build(){
            return AnimalBreed.this;
        }

    }
}
