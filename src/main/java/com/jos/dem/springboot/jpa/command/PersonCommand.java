package com.jos.dem.springboot.jpa.command

import javax.validation.constraints.Size
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Email

public class PersonCommand implements Command {

	@NotNull
	@Size(min=3, max=50)
	private String nickname

	@Email
	@NotNull
	@Size(min=1, max=250)
	private String email

}
