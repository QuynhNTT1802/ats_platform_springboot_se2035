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
    String experience;
    String jobType;
    String qualification;
    BigDecimal priceRange;
    Integer pageNumber;
    Integer pageSize;

}
