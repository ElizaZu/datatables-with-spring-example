package dte.controller;

import dte.model.Person;
import dte.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ElZu on 09.04.2018.
 */
@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String listDepartments(ModelMap model) {
        model.addAttribute("persons", personService.getPersons());
        return "personslist";
    }

}
