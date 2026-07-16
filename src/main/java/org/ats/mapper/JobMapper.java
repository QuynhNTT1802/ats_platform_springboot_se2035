package org.ats.mapper;

import org.ats.dto.JobCriteria;
import org.ats.dto.JobResponse;
import org.ats.entities.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toEntity(JobCriteria criteria);
    JobResponse toDTO (Job job);
}