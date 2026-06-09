package org.ats.services;

import org.ats.entities.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);

    List<Job> findByTitle(String title);

    List<Job> getAll(String keyword);
}
