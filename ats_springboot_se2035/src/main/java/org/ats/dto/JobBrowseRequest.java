package org.ats.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobBrowseRequest {
    String keyword;
    String location;
    String jobType;
    BigDecimal priceRange;
    String experience;
    String qualification;

    Integer pageNumber = 0;
    Integer pageSize = 6;
}
