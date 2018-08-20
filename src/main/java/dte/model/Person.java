package dte.model;

import java.util.List;
import javax.persistence.*;

/**
 * Created by Eliza on 05.04.2018.
 */
@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="firstname", nullable=false, length = 255)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "address", nullable = true, length = 1024)
    private String address;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Animal> animals;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<Animal> getAnimals() {
        return animals;
    }

    private Person() {}

    public static Builder newBuilder(){
        return new Person().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setName(String firstname, String lastname){
            Person.this.setFirstname(firstname);
            Person.this.setLastname(lastname);
            return this;
        }

        public Builder setContacts(String email, String address, String phone){
            Person.this.setEmail(email);
            Person.this.setAddress(address);
            Person.this.setPhone(phone);
            return this;
        }

        public Builder addAnimal(String name, AnimalBreed breed, int yearOfBirth){
            Animal.newBuilder().setName(name).setBreed(breed).setYearOfBirth(yearOfBirth).setPerson(Person.this).build();
            return this;
        }

        public Person build(){
            return Person.this;
        }
    }
}

