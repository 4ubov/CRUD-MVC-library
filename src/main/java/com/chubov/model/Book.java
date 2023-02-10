package com.chubov.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int book_id;

    @NotEmpty(message = "Поле Название, не может быть пустым")
    @Size(min = 2, max = 60, message = "Длина Названия, должна быть в пределах [2:60]")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Поле Автор, не может быть пустым")
    @Length(min = 2, max = 60, message = "Длина поля Автор, должна быть в пределах [2:60]")
    @Column(name = "author")
    private String author;
    @NotEmpty(message = "Поле Дата выпуска, не может быть пустым")
    @Pattern(regexp = ("^([0-2][0-9]||3[0-1]).(0[0-9]||1[0-2]).([0-9][0-9])?[0-9][0-9]$"),
            message = "Используйте верный формат даты - (dd.mm.yyyy)")
    @Column(name = "realiseDate")
    private String realiseDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;


    public Book() {
    }

    public Book(int book_id, String title, String author, String realiseDate) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.realiseDate = realiseDate;
    }

    public int getBookId() {
        return book_id;
    }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRealiseDate() {
        return realiseDate;
    }

    public void setRealiseDate(String realiseDate) {
        this.realiseDate = realiseDate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
