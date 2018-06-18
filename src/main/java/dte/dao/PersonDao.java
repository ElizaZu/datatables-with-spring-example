package dte.dao;

import dte.model.Person;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */

public interface PersonDao {
    List<Person> findAll();
    void saveOrUpdate(Person person);
}
