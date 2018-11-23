package dte.model;

import javax.persistence.*;

/**
 * Created by Eliza on 05.04.2018.
 */
@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_breed", referencedColumnName = "id", nullable = false)
    private AnimalBreed breed;

    @Basic
    @Column(name = "year", nullable = false)
    private int yearOfBirth;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    private Person person;

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
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public AnimalBreed getBreed() {
        return breed;
    }
    public void setBreed(AnimalBreed breed) {
        this.breed = breed;
    }

    protected Animal() {
    }

    public static Builder newBuilder(){
        return new Animal().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setName(String name){
            Animal.this.setName(name);
            return this;
        }

        public Builder setBreed(AnimalBreed breed){
            Animal.this.setBreed(breed);
            return this;
        }

        public Builder setPerson(Person person){
            Animal.this.setPerson(person);
            return this;
        }

        public Builder setYearOfBirth(int year){
            Animal.this.setYearOfBirth(year);
            return this;
        }

        public Animal build(){
            return Animal.this;
        }
    }
}
