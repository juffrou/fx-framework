package org.juffrou.fx.business.pm;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.error.PropertyCreationException;

/**
 * Person Presentation Model
 * 
 * This class wraps the Person domain (model) and interacts with the Person Controller/View
 * 
 * @author cemartins
 *
 */
public class PersonPM {

	private final Property<String> nameProperty;
	private final Property<String> emailProperty;
	private final Property<LocalDate> dateOfBirthProperty;

	public PersonPM(Person person) {
		try {
			nameProperty = JavaBeanStringPropertyBuilder.create().bean(person).name("name").build();
			emailProperty = JavaBeanStringPropertyBuilder.create().bean(person).name("email").build();
			dateOfBirthProperty = JavaBeanObjectPropertyBuilder.create().bean(person).name("dateOfBirth").build();
		} catch (NoSuchMethodException e) {
			throw new PropertyCreationException(e);
		}
	}
	
	public Property<String> nameProperty() {
		return nameProperty;
	}

	public Property<String> emailProperty() {
		return emailProperty;
	}

	public Property<LocalDate> dateOfBirthProperty() {
		return dateOfBirthProperty;
	}
	
}
