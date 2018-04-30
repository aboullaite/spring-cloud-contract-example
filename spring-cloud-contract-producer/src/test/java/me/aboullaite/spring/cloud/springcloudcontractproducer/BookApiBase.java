package me.aboullaite.spring.cloud.springcloudcontractproducer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import me.aboullaite.spring.cloud.springcloudcontractproducer.domain.Book;
import me.aboullaite.spring.cloud.springcloudcontractproducer.repository.BookRepository;
import me.aboullaite.spring.cloud.springcloudcontractproducer.service.BookService;
import me.aboullaite.spring.cloud.springcloudcontractproducer.web.BookController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BookApiBase {

    @Autowired
    BookController bookController;

    @MockBean
    private BookRepository repository;

    @Before
    public void setup() {
        Book book= new Book("123", "Ferok Book", "Fero Hero");
        when(repository.findById(any(String.class))).thenReturn(Optional.of(book));
        when(repository.save(any(Book.class))).thenReturn(book);

        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(bookController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

}
