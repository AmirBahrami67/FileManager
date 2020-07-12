package com.abahrami.template.book.repository;

import com.abahrami.template.book.domain.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

  List<Book> findByTitleLike(String title);
}
