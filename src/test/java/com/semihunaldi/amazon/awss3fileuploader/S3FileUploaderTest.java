package com.semihunaldi.amazon.awss3fileuploader;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import java.io.File;

/**
 * Created by semihunaldi on 17.04.2019
 */
public class S3FileUploaderTest {

	private static S3FileUploader s3FileUploader = new S3FileUploader(prepareSettings());

	private static void testAsyncUploadFileToS3Bucket() {
		s3FileUploader.asyncUploadFileToS3Bucket("testName", new File("FILE_PATH"), CannedAccessControlList.PublicRead, new S3ResultListener() {
			@Override
			public void finished(S3UploadResult result) {
				System.out.println(result);
			}
		});
	}

	private static void testAsyncUploadFileToS3Bucket2() {
		s3FileUploader.asyncUploadFileToS3Bucket("testName", "FILE_PATH", CannedAccessControlList.PublicRead, new S3ResultListener() {
			@Override
			public void finished(S3UploadResult result) {
				System.out.println(result);
			}
		});
	}

	private static void testUploadFileToS3Bucket() {
		S3UploadResult result = s3FileUploader.uploadFileToS3Bucket("testName", new File("FILE_PATH"), CannedAccessControlList.PublicRead);
		System.out.println(result);
	}

	private static void testUploadFileToS3Bucket2() {
		S3UploadResult result = s3FileUploader.uploadFileToS3Bucket("testName", "FILE_PATH", CannedAccessControlList.PublicRead);
		System.out.println(result);
	}

	private static S3Settings prepareSettings() {
		S3Settings s3Settings = new S3Settings();
		s3Settings.setAccessKey("ACCESS_KEY");
		s3Settings.setSecretKey("SECRET_KEY");
		s3Settings.setBucketName("BUCKET_NAME");
		s3Settings.setRegion(Regions.DEFAULT_REGION);
		return s3Settings;
	}

	public static void main(String[] args) {
		testAsyncUploadFileToS3Bucket();
		testAsyncUploadFileToS3Bucket2();
		testUploadFileToS3Bucket();
		testUploadFileToS3Bucket2();
	}
}
