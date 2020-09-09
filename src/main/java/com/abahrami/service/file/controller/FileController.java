package com.abahrami.service.file.controller;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import com.abahrami.service.file.model.File;
import com.abahrami.service.file.model.FileSummary;
import com.abahrami.service.file.model.UploadRequest;
import com.abahrami.service.file.service.FileService;
import com.amazonaws.util.IOUtils;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

  private final FileService fileService;

  public FileController(final FileService fileService) {
    this.fileService = fileService;
  }

  /**
   * upload a multipart file with parameters as QueryParam.
   *
   * @param file multipart file
   * @param fullPath file full path. Example: Documents/books/myFile.txt
   * @param forceReplace should replace the file if already exists
   * @param params user-defined parameters of the file.
   * @throws IOException upload issues
   */
  @PostMapping
  public void uploadFile(
      final @RequestParam("file") MultipartFile file,
      final @RequestParam("fullPath") String fullPath,
      final @RequestParam(value = "forceReplace", defaultValue = "false") boolean forceReplace,
      final @RequestParam(value = "params", required = false) List<String> params)
      throws IOException {

    final UploadRequest uploadRequest =
        UploadRequest.builder()
            .fullPath(fullPath)
            .inputStream(file.getInputStream())
            .forceReplace(forceReplace)
            .params(
                emptyIfNull(params).stream()
                    .filter(StringUtils::isNotBlank)
                    .map(entry -> entry.split(":"))
                    .filter(
                        splitEntry ->
                            splitEntry.length > 1 && StringUtils.isNotBlank(splitEntry[1]))
                    .collect(
                        Collectors.toMap(splitEntry -> splitEntry[0], splitEntry -> splitEntry[1])))
            .build();
    fileService.putFile(uploadRequest);
  }

  /**
   * Download a file.
   *
   * @param fullpath file full path
   * @param response this will get injected by Spring
   * @throws IOException download issues
   */
  @GetMapping
  public void downloadFile(
      @RequestParam("fullpath") final String fullpath, final HttpServletResponse response)
      throws IOException {
    final File file = fileService.getFile(fullpath);

    // Set the content type and attachment header.
    response.addHeader(CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName());
    response.setContentType(file.getContentType());
    response.setCharacterEncoding(file.getContentEncoding());
    response.setContentLengthLong(file.getContentLength());

    // Copy the stream to the response's output stream.
    IOUtils.copy(file.getInputStream(), response.getOutputStream());
    response.flushBuffer();
  }

  /**
   * Delete a file.
   *
   * @param fullpath file full path
   */
  @DeleteMapping()
  public void deleteFile(@RequestParam("fullpath") final String fullpath) {
    fileService.deleteFile(fullpath);
  }

  /**
   * search files matching fullPath prefix.
   *
   * @param fullPathPrefix prefix could contain path including parts of file name
   * @return list of file summaries
   */
  @GetMapping("/search")
  public List<FileSummary> searchFiles(final String fullPathPrefix) {
    return fileService.listFiles(fullPathPrefix);
  }

  @GetMapping("/metadata")
  public File getFileMetadata(@RequestParam("fullpath") final String fullpath) {
    return fileService.getFileMetadata(fullpath);
  }
}
