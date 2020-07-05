package com.abahrami.template.book.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor(staticName = "of")
@Builder(toBuilder = true)
@Document(collection = "book")
@Value
@With
public class Book {

  @Id private String id;

  @NotNull @NonNull private String title;

  @Size(min = 1)
  private String isbn;
}
