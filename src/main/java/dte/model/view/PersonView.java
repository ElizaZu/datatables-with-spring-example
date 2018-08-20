package dte.model.view;

import dte.model.Animal;
import dte.model.Person;

/**
 * Created by Eliza on 20.08.2018.
 */
public class PersonView {
	private Person person;

	public PersonView(Person person) {
		this.person = person;
	}

	public int getId(){
		return person.getId();
	}

	public String getLastname(){
		return person.getLastname();
	}

	public String getFirstname(){
		return person.getFirstname();
	}

	public String getAddress(){
		return person.getAddress();
	}

	public String getPhone(){
		return person.getPhone();
	}

	public String getEmail(){
		return person.getEmail();
	}

	public String getAnimalInfo(){
		StringBuilder info = new StringBuilder();
		for (Animal animal: person.getAnimals()){
			info.append(String.format("%s %s (breed: %s, year of birth: %d)/n", animal.getBreed().getAnimalType().getName(), animal.getName(), animal.getBreed(), animal.getYearOfBirth()));
		}
		return info.toString();
	}
}


