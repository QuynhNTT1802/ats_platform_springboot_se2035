package org.ats.mapper;

import org.ats.dto.JobBrowseRequest;
import org.ats.dto.JobResponse;
import org.ats.entities.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toEntity(JobBrowseRequest jobBrowseRequest);
    JobResponse toDTO (Job job);
}
