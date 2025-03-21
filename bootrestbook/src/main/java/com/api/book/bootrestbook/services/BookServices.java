package com.api.book.bootrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

import jakarta.transaction.Transactional;

@Component
public class BookServices {

    @Autowired
    private BookRepository bookRepository;


    // private static List<Book> list = new ArrayList<>();

    // static{
    //     list.add(new Book(10,"C++","Pranav"));
    //     list.add(new Book(20,"C","Yash"));
    //     list.add(new Book(30,"Python","Om"));
    // }

    //get all book
    public List<Book> getAllBooks(){
        List<Book> list =(List<Book>) this.bookRepository.findAll();
        return list;
    }

    //get book by id
    public Book getBookById(int id){
        Book b = null;
        try{
            // b = list.stream().filter(e-> e.getId()==id).findFirst().get();

            b = this.bookRepository.findById(id);


        }catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }

    //adding book
    public Book addBook(Book b){
        // list.add(b);
        // return b;
        Book result = bookRepository.save(b);
        return result;
    }




    //deleting book
    public void deleteBook(int id){
            //list = list.stream().filter(book ->{
            //     if (book.getId() != id) {
            //         return true;
            //     }else{
            //         return false;
            //     }
            // }).collect(Collectors.toList());

            
            // list = list.stream().filter(book -> book.getId()!=id).collect(Collectors.toList());

            this.bookRepository.deleteById(id);
    }

//    //updating book
    public void updateBook(Book book, int id){
        // list = list.stream().map(b -> {
        //     if(b.getId()==id){
        //         b.setName(book.getName());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());

        book.setId(id);
        // bookRepository.saveAndFlush(book);
        bookRepository.save(book);
    }




//    @Transactional
//    public Book updateBook(Book book, int id) {
//        Book existingBook = bookRepository.findById(id);
//
//        existingBook.setTitle(book.getTitle());
//        existingBook.setAuthor(book.getAuthor());
//
//        return bookRepository.save(existingBook);
//    }

}
