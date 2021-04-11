package com.github.msalaslo.multipart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.msalaslo.multipart.dto.ErrorDto;
import com.github.msalaslo.multipart.dto.ImageAnalyticsResponse;
import com.github.msalaslo.multipart.dto.ProcessOptions;
import com.github.msalaslo.multipart.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImageController {

	@Autowired
	ImageService imageService;

	@Operation(summary = "Receives a set of images and return analytics about them")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Images processed succesfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageAnalyticsResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request supplied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
			@ApiResponse(responseCode = "415", description = "Unsupported Media Type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))) })
	@PostMapping(value = "/images/set", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ImageAnalyticsResponse> analyzeImagesFinal(
			@RequestPart(value = "id", required = true) @Parameter(description = "The id of the image set") final String id,
			@RequestPart(value = "files", required = true) @Parameter(description = "Array of image files in binary format") final MultipartFile files[]) {
		log.debug("Receiving images with id {}", id);
		return new ResponseEntity<>(imageService.processImages(id, files, null), HttpStatus.OK);

	}

	@Operation(summary = "Receives a set of images and return analytics about them")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Images processed succesfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageAnalyticsResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request supplied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
			@ApiResponse(responseCode = "415", description = "Unsupported Media Type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
			@ApiResponse(responseCode = "500", description = "Internal error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))) })
	@PostMapping(value = "/images/set/options", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ImageAnalyticsResponse> analyzeImages(
			@RequestPart(value = "id", required = true) @Parameter(description = "The id of the image set") final String id,
			@RequestPart(value = "files", required = true) @Parameter(description = "Array of image files in binary format") final MultipartFile files[],
			@RequestPart(value = "processOptions", required = false) @Parameter(description = "ProcessOptions: Processing options", schema = @Schema(name = "processOptions", implementation = ProcessOptions.class), content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) final ProcessOptions processOptions){
		log.debug("Receiving images with id {}", id);
		return new ResponseEntity<>(imageService.processImages(id, files, processOptions), HttpStatus.OK);

	}
}
