package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.JobDao;
import org.ats.entities.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobDao jobDao;

    @Override
    public Job createJob(Job job) {
        return null;
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
}
