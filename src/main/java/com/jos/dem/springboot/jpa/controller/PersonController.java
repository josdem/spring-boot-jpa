package com.jos.dem.springboot.jpa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.jos.dem.springboot.jpa.model.Person;
import com.jos.dem.springboot.jpa.command.Command;
import com.jos.dem.springboot.jpa.command.PersonCommand;
import com.jos.dem.springboot.jpa.repository.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("persons/**")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method=GET)
	public ModelAndView getAll(){
		log.info("Listing all persons");
		ModelAndView modelAndView = new ModelAndView("persons/list");
		List<Person> persons = personRepository.findAll();
		modelAndView.addObject("persons", persons);
		return modelAndView;
	}

	@RequestMapping(value="create", method=GET)
	public ModelAndView create(){
		log.info("Creating person");
		ModelAndView modelAndView = new ModelAndView("persons/create");
		Command personCommand = new PersonCommand();
		modelAndView.addObject("personCommand", personCommand);
		return modelAndView;
	}

	@RequestMapping(method=POST)
	public ModelAndView save(@Valid PersonCommand personCommand, BindingResult bindingResult){
		log.info("Registering new Person: " + personCommand.getNickname());
		ModelAndView modelAndView = new ModelAndView("persons/list");
		if(bindingResult.hasErrors()){
			modelAndView.setViewName("persons/create");
			modelAndView.addObject("personCommand", personCommand);
			return modelAndView;
		}
		Person person = new Person();
    person.setNickname(personCommand.getNickname());
    person.setEmail(personCommand.getEmail());
		personRepository.save(person);
		List<Person> persons = personRepository.findAll();
		modelAndView.addObject("persons", persons);
		return modelAndView;
	}

}
