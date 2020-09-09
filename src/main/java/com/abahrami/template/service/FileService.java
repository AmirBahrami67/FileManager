package com.abahrami.template.service;

import com.abahrami.template.model.File;
import com.abahrami.template.model.FileSummary;
import com.abahrami.template.model.UploadRequest;
import java.util.List;

public interface FileService {

  File getFile(String fullPath);

  File getFileMetadata(String fullpath);

  List<FileSummary> listFiles(String prefix);

  void putFile(UploadRequest request);

  void deleteFile(String fullPath);
}
