package com.semihunaldi.amazon.awss3fileuploader;

import com.amazonaws.regions.Regions;
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
public class S3Settings {

	private Regions region;
	private String accessKey;
	private String secretKey;
	private String bucketName;
}
