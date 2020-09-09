package com.abahrami.service.file.model;

import java.io.InputStream;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UploadRequest {
  private String fullPath;
  private boolean forceReplace;
  private Map<String, String> params;
  private InputStream inputStream;
}
