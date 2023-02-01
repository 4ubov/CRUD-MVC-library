package com.chubov.DAO;

import com.chubov.model.Book;
import com.chubov.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                        new Object[]{person_id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES(?, ?)",
                person.getFullName(),
                person.getYearOfBirth());
    }

    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE person_id = ?", personId);
    }

    public void update(int person_id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, yearOfBirth=? WHERE person_id=?",
                updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(),
                person_id);
    }

    public List<Book> getBooksByPersonId(int person_id) {
        return jdbcTemplate.query("select * from book where person_id=?",
                new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
