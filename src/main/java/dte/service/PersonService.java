package dte.service;

import dte.dao.PersonDao;
import dte.dao.pagination.model.PageRequest;
import dte.dao.pagination.model.PageTypedResponse;
import dte.model.Person;
import dte.model.view.PersonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */

public interface PersonService {
    List<Person> getPersons();
    PageTypedResponse<PersonView> getPage(PageRequest ajaxRequest, int animalType) throws Exception;
}
