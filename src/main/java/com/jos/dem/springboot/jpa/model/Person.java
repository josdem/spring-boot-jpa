package com.jos.dem.springboot.jpa.model;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=AUTO)
  private Long id;

  @Column(nullable=false)
  private String nickname;

  @Column(unique=true, nullable=false)
  private String email;

}
