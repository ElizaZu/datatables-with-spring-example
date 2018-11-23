package dte.controller;

import dte.dao.pagination.model.PageRequest;
import dte.dao.pagination.model.PageTypedResponse;
import dte.model.Person;
import dte.model.view.PersonView;
import dte.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ElZu on 09.04.2018.
 */
@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = { "/simple" }, method = RequestMethod.GET)
    public String simpleListOfPersons(ModelMap model) {
        model.addAttribute("persons", personService.getPersons());
        return "persons_list";
    }

    @RequestMapping(value = "/server_side/page/{animalType}", method = RequestMethod.POST)
    public @ResponseBody
    PageTypedResponse<PersonView> pageOfPersons(@RequestBody PageRequest pageRequest, @PathVariable Integer animalType) throws Exception {
        return personService.getPage(pageRequest, animalType);
    }

    /**
     * This method will list all existing specs.
     */
    @RequestMapping(value = { "/server_side/{animalType}" }, method = RequestMethod.GET, produces={"text/plain; charset=UTF-8"})
    public String listOfPersons(@PathVariable Integer animalType, ModelMap model, HttpServletRequest request) {
        model.addAttribute("animalType", animalType);
        return "persons_list_server_side";
    }

}
