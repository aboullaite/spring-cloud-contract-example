package me.aboullaite.spring.cloud.springcloudcontractproducer.repository;

import me.aboullaite.spring.cloud.springcloudcontractproducer.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String>{
    List<Book> findByAuthor(String author);
    List<Book> findByTitleLike(String title);
}
