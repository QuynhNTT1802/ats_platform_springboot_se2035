package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.dao.JobSkillDao;
import org.ats.dto.JobRequest;
import org.ats.entities.*;
import org.ats.exceptions.JobNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {
    private final JobDao jobDao;
    private final JobSkillDao jobSkillDao;

    @Override
    public Job createJob(JobRequest jobRequest) {
        // Validate


        return jobDao.createJob(toEntity(jobRequest));
    }

    @Override
    public List<Job> findByTitle(String title) {
        return List.of();
    }

    @Override
    public List<Job> getAll(String keyword) {
        if (keyword == null) {
            return jobDao.findAll();
        }

        return jobDao.findAll("%" + keyword + "%");
    }

    @Override
    public void delete(Long id) {
        jobDao.delete(id);
    }

    @Override
    public JobRequest getJobById(Long id) {
        Job job = jobDao.findById(id);

        if (job == null) {
            throw new JobNotFoundException("Job not found");
        }

        return toDto(job);
    }

    @Override
    public Job getJobByTitle(String title) {
        return null;
    }

    private JobRequest toDto(Job job) {
        JobRequest jobRequest = JobRequest.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .build();

        if (job.getDeadline() != null) {
            jobRequest.setDeadline(job.getDeadline().toLocalDate());
        }

        if (job.getDepartment() != null) {
            jobRequest.setDepartmentId(job.getDepartment().getId());
        }

        List<JobSkill> jobSkills = jobSkillDao.findByJobId(job.getId());

        List<Long> jobSkillIds = jobSkills.stream().map((jobSkill -> {
            return jobSkill.getSkill().getId();
        })).collect(Collectors.toList());

        jobRequest.setSkillIds(jobSkillIds);

        return jobRequest;
    }

    private Job toEntity(JobRequest jobRequest) {
        Set<JobSkill> jobSkills = jobRequest.getSkillIds().stream().map((skillId) -> {
            JobSkill jobSkill = new JobSkill();
            jobSkill.setSkill(Skill.builder().id(skillId).build()); // 1, 3

            return jobSkill;
        }).collect(Collectors.toSet());


        Job job = Job.builder()
                .id(jobRequest.getId())
                .title(jobRequest.getTitle())
                .deadline(OffsetDateTime.of(jobRequest.getDeadline(), LocalTime.now(), ZoneOffset.ofHours(7)))
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .maxSalary(jobRequest.getMaxSalary())
                .minSalary(jobRequest.getMinSalary())
                .status(JobStatus.DRAFT.toString())
                .build();

        if (jobRequest.getDepartmentId() != null) {
            job.setDepartment(Department.builder().id(jobRequest.getDepartmentId()).build());
        }

        for (JobSkill jobSkill : jobSkills) {
            jobSkill.setJob(job);
        }

        job.setSkills(jobSkills);

        return job;
    }
}
