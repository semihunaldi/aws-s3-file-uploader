package com.semihunaldi.amazon.awss3fileuploader;

/**
 * Created by semihunaldi on 17.04.2019
 */
public interface S3ResultListener {

	void finished(S3UploadResult result);
}
