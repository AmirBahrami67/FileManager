package com.abahrami.template.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.InputStream;
import java.time.Instant;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class File {
  private String fullPath;
  private String fileName;
  private @JsonIgnore InputStream inputStream;
  private String etag;
  private long contentLength;
  private String contentEncoding;
  private String contentType;
  private Instant lastModified;
  private Map<String, String> params;
}
