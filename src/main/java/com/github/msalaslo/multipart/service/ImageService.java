package com.github.msalaslo.multipart.service;

import org.springframework.web.multipart.MultipartFile;

import com.github.msalaslo.multipart.dto.ImageAnalyticsResponse;
import com.github.msalaslo.multipart.dto.ProcessOptions;
import com.github.msalaslo.multipart.exception.ImageProcessingException;

public interface ImageService {
	
	public ImageAnalyticsResponse processImages(String id, MultipartFile files[], ProcessOptions processOptions) throws ImageProcessingException;

}
