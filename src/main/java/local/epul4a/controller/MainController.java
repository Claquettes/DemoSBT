package local.epul4a.controller;

import java.util.ArrayList;
import java.util.List;

import local.epul4a.form.PersonForm;
import local.epul4a.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", persons);
        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm) {
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        if (firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

    @RequestMapping(value = {"/editPerson/{index}"}, method = RequestMethod.GET)
    public String showEditPersonPage(Model model, @PathVariable("index") int index) {
        if (index < 0 || index >= persons.size()) {
            model.addAttribute("errorMessage", "Invalid User Index");
            return "personList";
        }
        Person person = persons.get(index);
        PersonForm personForm = new PersonForm();
        personForm.setFirstName(person.getFirstName());
        personForm.setLastName(person.getLastName());
        model.addAttribute("personForm", personForm);
        model.addAttribute("index", index);
        return "editPerson";
    }

    @RequestMapping(value = {"/editPerson/{index}"}, method = RequestMethod.POST)
    public String updatePerson(Model model, @PathVariable("index") int index, @ModelAttribute("personForm") PersonForm personForm) {
        if (index < 0 || index >= persons.size()) {
            model.addAttribute("errorMessage", "Invalid User Index");
            return "personList";
        }
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        if (firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
            Person person = persons.get(index);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "editPerson";
    }

    @RequestMapping(value = {"/deletePerson/{index}"}, method = RequestMethod.GET)
    public String deletePerson(Model model, @PathVariable("index") int index) {
        if (index >= 0 && index < persons.size()) {
            persons.remove(index);
        }
        return "redirect:/personList";
    }
}
