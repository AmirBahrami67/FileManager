package com.abahrami.service.file.service;

import static org.apache.commons.collections4.MapUtils.emptyIfNull;

import com.abahrami.service.file.model.File;
import com.abahrami.service.file.model.FileSummary;
import com.abahrami.service.file.model.UploadRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AwsFileService implements FileService {

  private final AmazonS3 amazonS3;
  private final String amazonS3BucketName;

  public AwsFileService(
      final AmazonS3 amazonS3, final @Value("${aws.s3.bucket.name}") String amazonS3BucketName) {
    this.amazonS3 = amazonS3;
    this.amazonS3BucketName = amazonS3BucketName;
  }

  @Override
  public void putFile(final UploadRequest request) {
    final ObjectMetadata objectMetadata = new ObjectMetadata();
    emptyIfNull(request.getParams()).forEach(objectMetadata::addUserMetadata);
    amazonS3.putObject(
        amazonS3BucketName, request.getFullPath(), request.getInputStream(), objectMetadata);
  }

  @Override
  public void deleteFile(final String fullPath) {
    amazonS3.deleteObject(amazonS3BucketName, fullPath);
  }

  @SuppressWarnings("PMD.CloseResource")
  @Override
  public File getFile(final String fullPath) {

    final S3Object object = amazonS3.getObject(amazonS3BucketName, fullPath);
    final ObjectMetadata metadata = object.getObjectMetadata();
    return File.builder()
        .fullPath(fullPath)
        .fileName(FilenameUtils.getName(fullPath))
        .inputStream(object.getObjectContent())
        .params(metadata.getUserMetadata())
        .lastModified(metadata.getLastModified().toInstant())
        .contentEncoding(metadata.getContentEncoding())
        .contentLength(metadata.getContentLength())
        .contentType(metadata.getContentType())
        .contentType(metadata.getVersionId())
        .etag(metadata.getETag())
        .build();
  }

  @Override
  public File getFileMetadata(final String fullPath) {
    final ObjectMetadata metadata = amazonS3.getObjectMetadata(amazonS3BucketName, fullPath);

    return File.builder()
        .fullPath(fullPath)
        .fileName(FilenameUtils.getName(fullPath))
        .params(metadata.getUserMetadata())
        .lastModified(metadata.getLastModified().toInstant())
        .contentEncoding(metadata.getContentEncoding())
        .contentLength(metadata.getContentLength())
        .contentType(metadata.getContentType())
        .etag(metadata.getETag())
        .build();
  }

  @Override
  public List<FileSummary> listFiles(final String prefix) {
    return amazonS3.listObjectsV2(amazonS3BucketName, prefix).getObjectSummaries().stream()
        .map(
            summary ->
                FileSummary.builder()
                    .fullPath(summary.getKey())
                    .fileName(FilenameUtils.getName(summary.getKey()))
                    .contentLength(summary.getSize())
                    .etag(summary.getETag())
                    .lastModified(summary.getLastModified().toInstant())
                    .build())
        .collect(Collectors.toList());
  }
}
