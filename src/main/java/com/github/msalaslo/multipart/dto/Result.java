
package com.github.msalaslo.multipart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Result {

    private Double score;
    private String type;
    private Bbox bbox;

}
