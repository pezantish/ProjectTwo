package com.qa.zms.exceptions;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends NoSuchElementException{
	
	public AnimalNotFoundException(long id) {
		super("Animal does not exist with ID: " + id);
		
	}
}
