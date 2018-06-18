package dte.dao;

import dte.model.AnimalType;
import dte.model.Person;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */

public interface AnimalTypeDao {
    List<AnimalType> findAll();
    void saveOrUpdate(AnimalType obj);
}
