package com.semihunaldi.amazon.awss3fileuploader;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Created by semihunaldi on 17.04.2019
 */
public class S3Client {

	private AmazonS3 client;

	private S3Settings s3Settings;

	public S3Client(S3Settings s3Settings) {
		this.s3Settings = s3Settings;
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(s3Settings.getAccessKey(), s3Settings.getSecretKey());
		this.client = AmazonS3ClientBuilder
				.standard()
				.withRegion(s3Settings.getRegion() != null ? s3Settings.getRegion() :Regions.DEFAULT_REGION)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();
	}

	public AmazonS3 getClient() {
		return client;
	}

	public S3Settings getS3Settings() {
		return s3Settings;
	}

	public void setS3Settings(S3Settings s3Settings) {
		this.s3Settings = s3Settings;
	}
}
