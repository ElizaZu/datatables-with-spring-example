package dte.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dte.dao.PersonDao;
import dte.dao.PersonPageProvider;
import dte.dao.pagination.model.PageTypedResponse;
import dte.model.Person;
import dte.model.view.PersonView;
import dte.dao.pagination.model.PageRequest;


/**
 * Created by ElZu on 09.04.2018.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    PersonPageProvider personPageProvider;


    public List<Person> getPersons(){
        return personDao.getPersons();
    }

    public PageTypedResponse<PersonView> getPage(PageRequest ajaxRequest, int animalType) throws Exception {
        Map<String, Object> additionalParams = new HashMap<>();
        additionalParams.put("animalType", animalType);
        return personPageProvider.getPageResponseView(ajaxRequest, additionalParams);
    }
}
