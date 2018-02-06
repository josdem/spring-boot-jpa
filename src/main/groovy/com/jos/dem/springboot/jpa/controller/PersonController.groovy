package com.jos.dem.springboot.jpa.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET

import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.springboot.jpa.model.Person
import com.jos.dem.springboot.jpa.command.Command
import com.jos.dem.springboot.jpa.command.PersonCommand
import com.jos.dem.springboot.jpa.repository.PersonRepository

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
@RequestMapping('persons/**')
class PersonController {

	@Autowired
	PersonRepository personRepository

	Logger log = LoggerFactory.getLogger(this.class)

	@RequestMapping(method=GET)
	ModelAndView getAll(){
		log.info 'Listing all persons'
		ModelAndView modelAndView = new ModelAndView('persons/list')		
		List<Person> persons = personRepository.findAll()
		modelAndView.addObject('persons', persons)
		modelAndView
	}

	@RequestMapping(value='create', method=GET)
	ModelAndView create(){
		log.info 'Creating person'
		ModelAndView modelAndView = new ModelAndView('persons/create')
		Command personCommand = new PersonCommand()
		modelAndView.addObject('personCommand', personCommand)
		modelAndView
	}
	
}