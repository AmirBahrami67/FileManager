package com.abahrami.template.book;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abahrami.template.book.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class BookIntegrationTest {

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(final WebApplicationContext webApplicationContext) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void bookRetrieve(@Autowired final MongoTemplate mongoTemplate) throws Exception {
    // stage data on embedded mongo
    final Book book = Book.of("hp1", "Harry Potter 1", "1234");
    mongoTemplate.save(book, "book");

    // perform REST call to the app
    mockMvc
        .perform(get("/book/hp1"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(
            content().string("{\"id\":\"hp1\",\"title\":\"Harry Potter 1\",\"isbn\":\"1234\"}"));
  }
}
