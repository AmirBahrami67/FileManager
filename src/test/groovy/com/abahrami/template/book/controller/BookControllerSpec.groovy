package com.abahrami.template.book.controller

import com.abahrami.template.book.domain.Book
import com.abahrami.template.book.repository.BookRepository
import spock.lang.Specification

/**
 * BookController unit tests.
 */
class BookControllerSpec extends Specification {

    private BookController bookController
    private BookRepository mockBookRepository
    private Book sampleBook

    void setup() {
        mockBookRepository = Mock()
        bookController = new BookController(mockBookRepository)
        sampleBook = new Book('hp1', 'Harry Potter 1', 'isbn1')
    }

    void 'should call service for create'() {
        given:
        1 * mockBookRepository.save(sampleBook) >> sampleBook

        expect:
        bookController.create(sampleBook) == sampleBook
    }

    void 'should call service for retrieve'() {
        given:
        1 * mockBookRepository.findById('id1') >> Optional.of(sampleBook)

        expect:
        bookController.retrieve('id1') == sampleBook
    }

    void 'should call service for search'() {
        given:
        1 * mockBookRepository.findByTitleLike('harry') >> [sampleBook]

        expect:
        bookController.search('harry') == [sampleBook]
    }
}
