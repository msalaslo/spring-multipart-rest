package com.github.msalaslo.multipart.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.msalaslo.multipart.dto.Bbox;
import com.github.msalaslo.multipart.dto.Image;
import com.github.msalaslo.multipart.dto.ImageAnalyticsResponse;
import com.github.msalaslo.multipart.dto.ProcessOptions;
import com.github.msalaslo.multipart.dto.Result;
import com.github.msalaslo.multipart.exception.ImageProcessingException;
import com.github.msalaslo.multipart.service.ImageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{
	
	public ImageAnalyticsResponse processImages(String id, MultipartFile multipartFiles[], ProcessOptions processOptions) throws ImageProcessingException{
		ImageAnalyticsResponse response = ImageAnalyticsResponse.builder().id(id).build();
		List<Image> images = new ArrayList<Image>();
		for (int i = 0; i < multipartFiles.length; i++) {			
			String name = multipartFiles[i].getOriginalFilename();
			String contentType = multipartFiles[i].getContentType();
			log.info("File for set id {}, file index {}, file name: {}, content-type: {}", id, i, name, contentType);
			Bbox bbox = Bbox.builder().x(10).y(20).w(200).h(100).build();
			Result result1 = Result.builder().score(Double.valueOf(0.999988888)).type("person").bbox(bbox).build();
			Result result2 = Result.builder().score(Double.valueOf(0.984900000)).type("pet").bbox(bbox).build();
			List<Result> results = new ArrayList<Result>();
			results.add(result1);
			results.add(result2);
			Image image = Image.builder().
					id(name).
					results(results).
					build();
			images.add(image);
			try {
				log.debug("File for set id {}, file index {}: {}, file value {}", id, i, multipartFiles[i].getBytes());
			} catch(IOException e) {
				log.error("Error processing image {}", name, e);
				throw new ImageProcessingException(e);
			}
		}
		response.setImages(images);
		return response;
	}


}
