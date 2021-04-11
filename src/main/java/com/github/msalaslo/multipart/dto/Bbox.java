
package com.github.msalaslo.multipart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bbox {

    private Integer y;
    private Integer x;
    private Integer w;
    private Integer h;

}
