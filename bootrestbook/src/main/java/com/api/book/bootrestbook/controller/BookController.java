package com.api.book.bootrestbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;

@RestController
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list = this.bookServices.getAllBooks();
        if (list.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // return ResponseEntity.of(Optional.of(list));
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book = this.bookServices.getBookById(id);
        if (book==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try{
            b = this.bookServices.addBook(book);
            System.out.println(book);
             return ResponseEntity.ok(b);
//            return ResponseEntity.status(HttpStatus.CREATED).body(b);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{bId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bId") int id){
        try {
            this.bookServices.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book ,@PathVariable("id") int id){
        try {
            this.bookServices.updateBook(book, id);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



// // @Controller
// @RestController
// public class BookController {
//     // @RequestMapping(value = "/book", method = RequestMethod.GET)
//     // @ResponseBody
//     @GetMapping("/book")
//     public String book(){
//         return "this is a book testing.";
//     }
// }

// @RestController
// public class BookController {

//     @GetMapping("/book")
//     public Book getBook(){
//         Book b = new Book();
//         b.setId(10);
//         b.setName("JAVA");
//         b.setAuthor("James Gosling");
//         return b;
//     }
// }
