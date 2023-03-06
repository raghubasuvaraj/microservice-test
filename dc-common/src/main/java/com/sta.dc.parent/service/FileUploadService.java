package com.sta.dc.parent.service;

import com.sta.dc.parent.entity.AwsFilePayload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    String fileUpload(String bucketName, MultipartFile file);

    String createBucket(String bucketName);

    List<String> getBucketList();

    List<AwsFilePayload> getBucketFiles(String bucketName);

    String softDeleteBucket(String bucketName);

    String hardDeleteBucket(String bucketName);

    String deleteFile(String bucketName, String fileName);

    AwsFilePayload downloadFile(String bucketName, String fileName);


}
