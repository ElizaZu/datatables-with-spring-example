package dte.service;

import dte.dao.PersonDao;
import dte.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */

public interface PersonService {
    List<Person> getPersons();
}
