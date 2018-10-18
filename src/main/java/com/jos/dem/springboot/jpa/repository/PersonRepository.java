package com.jos.dem.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jos.dem.springboot.jpa.model.Person;

interface PersonRepository extends JpaRepository<Person, Long>{

	Person save(Person person);
	List<Person> findAll();

}
