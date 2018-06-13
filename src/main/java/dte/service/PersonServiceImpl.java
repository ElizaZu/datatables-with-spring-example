package dte.service;

import dte.dao.PersonDao;
import dte.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    public List<Person> getPersons(){
        return personDao.getPersons();
    }
}
