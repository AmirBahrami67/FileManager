package com.abahrami.template.book.controller;

import com.abahrami.template.book.domain.Book;
import com.abahrami.template.book.repository.BookRepository;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

  private final BookRepository repository;

  public BookController(final BookRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book create(@Valid @RequestBody final Book book) {
    return repository.save(book);
  }

  @GetMapping("/{title}")
  public Book retrieve(@NotEmpty @PathVariable("title") final String title) {
    log.info("Call to title retrieve");
    return repository.findById(title).orElseThrow();
  }

  @GetMapping
  public List<Book> search(@RequestParam final String title) {
    return repository.findByTitleLike(title);
  }
}
