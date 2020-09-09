package com.abahrami.service.file.service;

import com.abahrami.service.file.model.File;
import com.abahrami.service.file.model.FileSummary;
import com.abahrami.service.file.model.UploadRequest;
import java.util.List;

public interface FileService {

  File getFile(String fullPath);

  File getFileMetadata(String fullpath);

  List<FileSummary> listFiles(String prefix);

  void putFile(UploadRequest request);

  void deleteFile(String fullPath);
}
