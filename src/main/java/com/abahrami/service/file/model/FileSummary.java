package com.abahrami.service.file.model;

import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FileSummary {
  private String fullPath;
  private String fileName;
  private String etag;
  private long contentLength;
  private Instant lastModified;
}
