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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?",
                        new Object[]{book_id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, realiseDate) values (?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getRealiseDate());
    }

    public void update(int book_id, Book book) {
        jdbcTemplate.update("UPDATE book set title=?, author=?, realiseDate=? where book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getRealiseDate(),
                book_id);
    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE from book where book_id = ?", book_id);
    }

    public Optional<Person> getBookOwner(int book_id) {
        return jdbcTemplate.query(
                        "select Person.* from book join person on book.person_id=person.person_id where book.book_id = ?",
                        new Object[]{book_id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int book_id) {
        jdbcTemplate.update("Update book set person_id=NULL where book_id=?", book_id);
    }

    public void assign(int book_id, Person selectedPerson) {
        jdbcTemplate.update("Update book set person_id=? where book_id=?", selectedPerson.getPersonId(), book_id);
    }
}
