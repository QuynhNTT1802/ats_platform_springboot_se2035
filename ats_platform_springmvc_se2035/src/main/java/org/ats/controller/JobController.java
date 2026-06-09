package org.ats.controller;

import lombok.RequiredArgsConstructor;
import org.ats.entities.Job;
import org.ats.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @RequestMapping(method = RequestMethod.POST)
    public String createJob(@ModelAttribute Job job) {

        return null;
    }

    @GetMapping
    public String listAll(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        String message = (String) model.asMap().get("message");
        System.out.println(keyword);

        // Call Job Service
        List<Job> jobs = jobService.getAll(keyword);
        model.addAttribute("jobs", jobs);

        System.out.println(jobs);

        return "jobs/general_dashboard";
    }

    @GetMapping(path = "/{id}")
    public String getById() {
        return null;
    }
}
