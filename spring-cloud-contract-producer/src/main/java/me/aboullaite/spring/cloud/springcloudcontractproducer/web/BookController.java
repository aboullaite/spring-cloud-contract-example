package me.aboullaite.spring.cloud.springcloudcontractproducer.web;

import me.aboullaite.spring.cloud.springcloudcontractproducer.domain.Book;
import me.aboullaite.spring.cloud.springcloudcontractproducer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
         return bookService.createNew(book);
    }

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {

        return bookService.update(isbn,book);
    }

    @RequestMapping(value = "/{isbn}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Book> getByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @RequestMapping(
            params = {"author"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getByAuthor(@RequestParam(value = "author",required = true) String author){
        return bookService.findByAuthor(author);
    }


    @RequestMapping(
            params = {"title"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getByTitle(@RequestParam(value = "title",required = true) String title){
        return bookService.findByTitleLike(title);
    }



    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getAll() {
        return bookService.findAll();
    }
}
