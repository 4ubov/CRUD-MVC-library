package com.chubov.util;

import com.chubov.DAO.PersonDAO;
import com.chubov.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.show(person.getFullName()).isPresent()) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("fullName", "", "Этот человек уже есть в БД");
        }

    }
}
