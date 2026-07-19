package org.ats.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.PersistenceCreator;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor(onConstructor_ = @PersistenceCreator)
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
