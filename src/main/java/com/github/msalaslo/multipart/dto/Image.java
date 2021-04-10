
package com.github.msalaslo.multipart.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Image {

    private String id;
    private List<Result> results;

}