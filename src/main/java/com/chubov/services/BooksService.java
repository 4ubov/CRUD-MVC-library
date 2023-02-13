package com.chubov.services;

import com.chubov.model.Book;
import com.chubov.model.Person;
import com.chubov.repositories.BooksRepository;
import com.chubov.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    @Transactional
    public Page<Book> findAll(Pageable var1){
        return booksRepository.findAll(var1);
    }

    @Transactional
    public List<Book> findAll(Sort var1){
        return booksRepository.findAll(var1);
    }



    public Book findOne(int book_id){
        Optional<Book> foundedBook = booksRepository.findById(book_id);
        return foundedBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int book_id, Book updatedBook){
        updatedBook.setBookId(book_id);
        updatedBook.setOwner(booksRepository.findById(book_id).get().getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int book_id){
        booksRepository.deleteById(book_id);
    }


    public Optional<Person> getBookOwner(int book_id) {
        return Optional.ofNullable(booksRepository.getOne(book_id).getOwner());
    }

    @Transactional
    public void release(int book_id) {
        Book book = booksRepository.findById(book_id).get();
        book.setOwner(null);
//        jdbcTemplate.update("Update book set person_id=NULL where book_id=?", book_id);
    }
    @Transactional
    public void assign(int book_id, Person selectedPerson) {
        Book book = booksRepository.findById(book_id).get();
        selectedPerson = peopleRepository.findById(selectedPerson.getPersonId()).get();
        book.setOwner(selectedPerson);
        selectedPerson.setBooks(book);
    }

    public List<Book> searchByTitle(String searchQuery){
        return booksRepository.findByTitleStartingWithIgnoreCase(searchQuery);
    }
}
