package com.example.library.controller;

import com.example.library.entity.Person;
import com.example.library.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public String viewListPerson(Model model) {
        List<Person> listPerson = personService.findAllPerson();
        model.addAttribute("listPerson", listPerson);
        return "listPerson";
    }

    @GetMapping("/{id}")
    public String viewPerson(@PathVariable int id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/new")
    public String viewCreatePerson(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("msg", "OK");
        return "createPerson";
    }

    @PostMapping("/new")
    public String createPerson(@Valid Person person, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "OK");
            return "createPerson";
        }
        Person p = personService.createPerson(person);
        if (p == null) {
            model.addAttribute("msg", "login busy");
            return "createPerson";
        }
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String viewEditPerson(Model model, @PathVariable int id) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        model.addAttribute("msg", "OK");
        return "editPerson";
    }

    @PostMapping("/edit")
    public String editPerson(@Valid Person person, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("msg", "OK");
            return "editPerson";
        }
        Person p = personService.editPerson(person);
        if (p == null) {
            model.addAttribute("msg", "login busy");
            return "editPerson";
        }
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }
}
