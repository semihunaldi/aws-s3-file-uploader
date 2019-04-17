package com.semihunaldi.amazon.awss3fileuploader;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by semihunaldi on 17.04.2019
 */
public class S3FileUploader {

	private static final String PRE_URL = "https://s3.";
	private static final String POSTURL = ".amazonaws.com/";

	private S3Client s3Client;
	private S3Settings s3Settings;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	public S3FileUploader(S3Settings s3Settings) {
		this.s3Settings = s3Settings;
		this.s3Client = new S3Client(s3Settings);
	}

	public void asyncUploadFileToS3Bucket(String fileName, String filePath, CannedAccessControlList accessControl, S3ResultListener s3ResultListener) {
		asyncUploadFileToS3Bucket(fileName, new File(filePath), accessControl, s3ResultListener);
	}

	public void asyncUploadFileToS3Bucket(String fileName, File file, CannedAccessControlList accessControl, S3ResultListener s3ResultListener) {
		executor.submit(() -> {
			S3UploadResult result = uploadFileToS3Bucket(fileName, file, accessControl);
			s3ResultListener.finished(result);
		});
	}

	public S3UploadResult uploadFileToS3Bucket(String fileName, String filePath, CannedAccessControlList accessControl) {
		return uploadFileToS3Bucket(fileName, new File(filePath), accessControl);
	}

	public S3UploadResult uploadFileToS3Bucket(String fileName, File file, CannedAccessControlList accessControl) {
		PutObjectResult putObjectResult = s3Client.getClient()
				.putObject(new PutObjectRequest(s3Settings.getBucketName(), fileName, file)
						.withCannedAcl(accessControl));
		return S3UploadResult.builder()
				.eTag(putObjectResult.getETag())
				.fileName(fileName)
				.url(prepareURL(fileName)).build();
	}

	private String prepareURL(String fileName) {
		return PRE_URL + s3Settings.getRegion().getName() + POSTURL + s3Settings.getBucketName() + "/" + fileName;
	}
}
