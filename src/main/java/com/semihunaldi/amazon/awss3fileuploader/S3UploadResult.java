package com.semihunaldi.amazon.awss3fileuploader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by semihunaldi on 17.04.2019
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class S3UploadResult {

	private String url;
	private String fileName;
	private String eTag;
}
