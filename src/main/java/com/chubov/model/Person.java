package com.chubov.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private int person_id;
    @NotEmpty(message = "Поле ФИО, не может быть пустым")
    @Size(min = 2, max = 60, message = "Длина ФИО, должна быть в пределах [2:60]")
    private String fullName;

    @NotEmpty(message = "Поле ФИО, не может быть пустым")
    @Pattern(regexp = ("^([0-2][0-9]||3[0-1]).(0[0-9]||1[0-2]).([0-9][0-9])?[0-9][0-9]$"),
            message = "Используйте верный формат даты - (dd.mm.yyyy)")
    private String yearOfBirth;


    public Person() {
    }

    public Person(int person_id, String fullName, String yearOfBirth) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return person_id;
    }

    public void setPersonId(int person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
