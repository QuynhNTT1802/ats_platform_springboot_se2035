package org.ats.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@ToString
public class JobCriteria {
    String keyword;
    String location;
    String experience;
    String jobType;
    String qualification;
    BigDecimal priceRange;
    Integer pageNumber;
    Integer pageSize;
}
