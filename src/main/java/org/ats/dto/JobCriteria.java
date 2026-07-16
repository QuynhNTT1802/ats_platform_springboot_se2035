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
    BigDecimal priceRange;
    Integer pageNumber;
    Integer pageSize;
}
